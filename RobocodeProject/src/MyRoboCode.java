
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Core.CoreData;
import Model.DataPoint;
import Model.EnemyBot;
import Model.MyBot;
import robocode.AdvancedRobot;


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
	
	
	//List<WaveBullet> waves = new ArrayList<WaveBullet>();
	
	

	
	
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
		m_core = new CoreData();
		m_kNearestNeigh = new KNN();
	}

	public void onScannedRobot(ScannedRobotEvent e) {
	   
	//	updateMainEnemy(e);
	//	checkNewBotAdded(e);
	  
	//	receiveRadarData(e);
		//detectStateFromHistory();
	//    takeDecision(e);
		
		//updateMainEnemy(e);
		
		
		
		// this is used with guess factor, in case the 
		// bot is moving randomly
		// waveCode(e);
		
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
			System.out.println("FireGuesFactor" + guessFactor);
			
			double angleOffset = m_direction * guessFactor * newWave.maxEscapeAngle();
            double gunAdjust = Utils.normalRelativeAngle(
                    absBearing - getGunHeadingRadians() + angleOffset);
            setTurnGunRightRadians(gunAdjust);
            if (getGunHeat() == 0 && gunAdjust < Math.atan2(9, e.getDistance())) 
            {
         	   fire(power);
            }
		}
		
		//if (getGunHeat() == 0 && gunAdjust < Math.atan2(9, e.getDistance())) 
	    //{
	    //	   m_waves.add(newWave);
	    //	   fire(power);
	     //}
		
	}
	/*public void onRoundEnded(RoundEndedEvent event)
	{
		ArrayList<DataPoint> points = new ArrayList<DataPoint>();
		
		if( CoreData.m_guessFactors.size() > m_kNearestNeigh.getM_MaxSize())
		{
			
			int i= 0;
			for(DataPoint point : CoreData.m_guessFactors.keySet())
			{
				i++;
				if(i > 2000)
					break;
				points.add(point);
			}
			
			//	System.out.println("Numarul de valuri de sters : " + deleteWaves.size());
			for( i = 0; i < points.size(); i++)
				CoreData.m_guessFactors.remove(points.get(i));
			
			
			
		//	CoreData.m_guessFactors.remove(key)
		}
	}
	
	*/

	/**
	 * @param e
	 */
/*	public void waveCode(ScannedRobotEvent e) {
		

		
		// ...
	    // (other onScannedRobot code, might be radar/movement)
		// ...
		 
		// Enemy absolute bearing, you can use your one if you already declare it.
		double absBearing = getHeadingRadians() + e.getBearingRadians();
		 
		// find our enemy's location:
		double ex = getX() + Math.sin(absBearing) * e.getDistance();
		double ey = getY() + Math.cos(absBearing) * e.getDistance();
		 
		// Let's process the waves now:
		for (int i=0; i < m_waves.size(); i++)
		{
			WaveBullet currentWave = (WaveBullet)m_waves.get(i);
			// if the wave has over passed the enemy => it has not hit him
			// we can remove him
			if (currentWave.checkHit(ex, ey, getTime()))
			{
				m_waves.remove(currentWave);
				i--;
			}
		}
		double power = Math.min(400 / e.getDistance(), 3);
		// don't try to figure out the direction they're moving , meaning detect the sens, not the heading
		// if they're not moving, just use the direction we had before
		if (e.getVelocity() != 0)
		{
			if (Math.sin(e.getHeadingRadians()-absBearing)*e.getVelocity() < 0)
				direction = -1;
			else
				direction = 1;
		}
	//	int[] currentStats = stats[(int)(e.getDistance() / 100)]; // It doesn't look silly now!
		int[] currentStats = stats; // This seems silly, but I'm using it to
							    // show something else later
		WaveBullet newWave = new WaveBullet(getX(), getY(), absBearing, power,
		                        direction, getTime(), currentStats);		
		
		// detecting the segment where the enemy has been most of the time
		int bestindex = 15;	// initialize it to be in the middle, guessfactor 0.
		for (int i=0; i<31; i++)
			if (currentStats[bestindex] < currentStats[i])
				bestindex = i;
 
		// this should do the opposite of the math in the WaveBullet:
		double guessfactor = (double)(bestindex - (stats.length - 1) / 2)
                        / ((stats.length - 1) / 2);
		double angleOffset = direction * guessfactor * newWave.maxEscapeAngle();
                double gunAdjust = Utils.normalRelativeAngle(
                        absBearing - getGunHeadingRadians() + angleOffset);
                setTurnGunRightRadians(gunAdjust);
                
       // ???     
       if (getGunHeat() == 0 && gunAdjust < Math.atan2(9, e.getDistance())) 
       {
    	   m_waves.add(newWave);
    	   fire(power);
       }
       
		double radarTurn;
		
		 radarTurn = getHeadingRadians() + e.getBearingRadians()
		    		- getRadarHeadingRadians();
		 setTurnRadarRightRadians(2* Utils.normalRelativeAngle(radarTurn));
	}
*/


}
