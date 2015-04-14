
import java.util.ArrayList;

import Core.CoreData;
import Model.DataPoint;
import robocode.AdvancedRobot;


import robocode.RoundEndedEvent;
import robocode.Rules;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;
import Model.WaveBullet;
import Targeting.KNN;

/**
 * main class that deals with events, calls 
 * decision algorithms, etc
 * @author Vlad Herescu
 *
 */
public class MyRoboCode extends AdvancedRobot{
	
	/**
	 * contains public static values used in algorithms, etc
	 */
	CoreData m_core;
	
	/**
	 * the algorithm to detect the most similar situations
	 */
	KNN m_kNearestNeigh;
	
	
	/**
	 * there are 31 segments where the guess factor angle can be categorized
	 */
	//int[][] stats = new int[5][31]; // onScannedRobot can scan up to 1200px, so there are only 13.
	static int[] stats = new int[31]; // 31 is the number of unique GuessFactors we're using
	  // Note: this must be odd number so we can get
	  // GuessFactor 0 at middle.
	int m_direction = 1;
	
	public void run() {
		
		initData();
		initConstantsAboutGame();
		System.out.println("intra inca o data aici");
		
		while (true) {
			turnRadarRight(Double.POSITIVE_INFINITY);
		}
		
	}
	
	
	/**
	 * determine the constants about the game
	 * <br> used to normalize the data gotten from scanning
	 */
	public void initConstantsAboutGame() 
	{
		CoreData.m_Maxheading = (2 * Math.PI);
		CoreData.m_MaxWidth = getBattleFieldWidth();
		CoreData.m_MaxHeight = getBattleFieldHeight();
		CoreData.m_MaxSpeed = Rules.MAX_VELOCITY;
		
	}


	/**
	 * initialize data about the BOTS and the state of my own BOT
	 */
	public void initData()
	{	
		setAdjustRadarForRobotTurn(true);
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		
		if(CoreData.m_value == null)
			m_core = new CoreData();
		else
			System.out.println("NU ESTE NULL  null");
		

		m_kNearestNeigh = new KNN();
	}

	public void onScannedRobot(ScannedRobotEvent e) {
	   
		
		waveCode(e);
		robotRadarDecision(e);

	}



	/**
	 * takes a decision for the radar, so it will have a lock on the robot
	 * @param e : data the robot detected
	 */
	public void robotRadarDecision(ScannedRobotEvent e) {
		
		double radarTurn;
		
		 radarTurn = getHeadingRadians() + e.getBearingRadians()
		    		- getRadarHeadingRadians();
		 setTurnRadarRightRadians(2* Utils.normalRelativeAngle(radarTurn));
		
		
	}
	
	/**
	 * checks if the waves created previously have hit the target
	 * 
	 * @param e : data about the robot detected
	 */
	public void waveCode(ScannedRobotEvent e)
	{
		double absBearing = getHeadingRadians() + e.getBearingRadians();
		 
		// find our enemy's location:
		double ex = getX() + Math.sin(absBearing) * e.getDistance();
		double ey = getY() + Math.cos(absBearing) * e.getDistance();
		ArrayList<WaveBullet> deleteWaves = new ArrayList<WaveBullet>();
		
		DataPoint currentSituation = new DataPoint
				(Math.sin(absBearing) * e.getDistance(),
				 Math.cos(absBearing) * e.getDistance(),
				 e.getVelocity(), 
				 Utils.normalAbsoluteAngle(e.getHeadingRadians() - absBearing)
				 );
		
		// take each wave created
		// if the wave has over passed the enemy => it has not hit him
		// => we can remove him
		
	//	System.out.println("Numarul de valuri : " + m_waves.keySet().size());
		
		// not sure if I have implemented correctly the removing of the waves that over passed the enemy
		for (WaveBullet wave : CoreData.m_waves.keySet())
		{
			Double guessFactor = wave.checkHit(ex, ey, getTime());
		
			if(guessFactor != null)
			{
				CoreData.m_guessFactors.put(CoreData.m_waves.get(wave), guessFactor); 
 				//System.out.println("GuesFactor : " + guessFactor);
				deleteWaves.add(wave);
				//m_waves.remove(wave);
			}	
		}
	//	System.out.println("Numarul de valuri de sters : " + deleteWaves.size());
		for(int i = 0; i < deleteWaves.size(); i++)
			CoreData.m_waves.remove(deleteWaves.get(i));
		
		
		double power = Math.min(400 / e.getDistance(), 3);
		// don't try to figure out the direction they're moving , meaning detect the sens, not the heading
		// if they're not moving, just use the direction we had before
		if (e.getVelocity() != 0)
		{
			if (Math.sin(e.getHeadingRadians()-absBearing)*e.getVelocity() < 0)
				m_direction = -1;
			else
				m_direction = 1;
		}
		
		WaveBullet newWave = new WaveBullet(getX(), getY(), absBearing, power,
                m_direction, getTime());
		
		CoreData.m_waves.put(newWave, currentSituation);
		
		DataPoint point =  m_kNearestNeigh.getMostSimilarDataPoints(currentSituation);
		
		if(point != null)
		{
		
			double guessFactor = CoreData.m_guessFactors.get(point);
		//	System.out.println("FireGuesFactor" + guessFactor);
			
			double angleOffset = m_direction * guessFactor * newWave.maxEscapeAngle();
            double gunAdjust = Utils.normalRelativeAngle(
                    absBearing - getGunHeadingRadians() + angleOffset);
            setTurnGunRightRadians(gunAdjust);
            if (getGunHeat() == 0 && gunAdjust < Math.atan2(9, e.getDistance())) 
            {
         	   fire(power);
            }
		}
		
	
	}

	public void onRoundEnded(RoundEndedEvent event)
	{
		System.out.println("Size-ul este" + CoreData.m_guessFactors.size());
		
		ArrayList<DataPoint> deleteWaves = new ArrayList<DataPoint>();
		int i;
		if(m_kNearestNeigh.getM_MaxSize() < CoreData.m_guessFactors.size() )
		{
			i = 0;
			for(DataPoint p : CoreData.m_guessFactors.keySet())
			{
				i++;
				deleteWaves.add(p);
				if(i > 2000)
					break;
			}
			
			for( i = 0; i < deleteWaves.size(); i++)
				CoreData.m_guessFactors.remove(deleteWaves.get(i));
		}
	}



}
