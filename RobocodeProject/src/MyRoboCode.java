
import java.util.HashMap;

import Model.EnemyBot;
import Model.MyBot;
import robocode.AdvancedRobot;
import robocode.GunTurnCompleteCondition;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;



/**
 * main class that deals with events, calls 
 * decision algorithms, etc
 * @author Vlad Herescu
 *
 */
public class MyRoboCode extends AdvancedRobot{
	
	/**
	 * the data about my bot : heading, speed, radar position
	 */
	MyBot m_bot;
	
	/**
	 * the data about my enemies
	 */
	HashMap<String,EnemyBot> m_enemies;
	
	/**
	 * the current enemy we are tracking
	 */
	EnemyBot m_currentEnemy;
	
	public void run() {
		
	//	initData();
		
		
	//	turnRadarRight(Double.POSITIVE_INFINITY);
		while (true) {
			turnRadarRight(Double.POSITIVE_INFINITY);
		}
		
	}
	
	
	/**
	 * initialize data about the BOTS and the state of my own BOT
	 */
	public void initData()
	{
		m_enemies = new HashMap<String,EnemyBot>();
		
		setAdjustRadarForRobotTurn(true);
	//	setAdjustGunForRobotTurn(true);
	//	setAdjustRadarForGunTurn(true);
	}
	/*
	public void onScannedRobot(ScannedRobotEvent e) {
		
		// Lock on to our target (this time for sure)
		//System.out.println("Values : " + e.getBearing() + " " + getHeading() + " " +  getRadarHeading());
		//setTurnRadarRight( 2*(getHeading() - getRadarHeading() + e.getBearing()));
		
		//checkNewBotAdded(e);
	//	updateMainEnemy(e);
		
		
		
	}
	*/
	public void onScannedRobot(ScannedRobotEvent e) {
	   
	  
		receiveRadarData(e);
		detectStateFromHistory();
	    takeDecision(e);
	}



	/**
	 * receiving data about the state of the game
	 * @param e : the data about the robot detected
	 */
	public void receiveRadarData(ScannedRobotEvent e) 
	{
		
		
	}
	private void detectStateFromHistory() 
	{
		// TODO Auto-generated method stub
		
	}


	private void takeDecision(ScannedRobotEvent e) 
	{
		 double radarTurn =
		    		getHeadingRadians() + e.getBearingRadians()
		    		- getRadarHeadingRadians();
		 
		 double gunTurn = getHeadingRadians() + e.getBearingRadians() +
				 	- getGunHeadingRadians();
		 
		    
		 setTurnRadarRightRadians(2 * Utils.normalRelativeAngle(radarTurn));
	
		 
		 setTurnGunRightRadians( Utils.normalRelativeAngle(gunTurn));
		 fire(4);
	//	 setTurnGunRight(360);
	}


	private void updateMainEnemy(ScannedRobotEvent e) {
		if (m_currentEnemy.none() || e.getName().equals(m_currentEnemy.getM_name())) {
			m_currentEnemy.update(e);
		}
		
	}


	/**
	 * @param e : event regarding an enemy scanned
	 */
	public void checkNewBotAdded(ScannedRobotEvent e)
	{
		EnemyBot bot = new EnemyBot(e.getName());
		
		
		if(m_enemies.containsKey(e.getName()) == false)
		{	
			m_enemies.put(e.getName(), bot);
		}
	}
	
	
	public void onRobotDeath(RobotDeathEvent e) {
		if (e.getName().equals(m_currentEnemy.getM_name())) {
			m_currentEnemy.reset();
		}
	}

	
	
	
		
	
	
	/*
	 * it just rotates, the advantage is that is sees all his enemies
	 * I don't understand why it is in a do while, since it rotates without stopping 
	 * (non-Javadoc)
	 * @see robocode.Robot#run()
	 */
	/*
	public void run() {
		
		
		// this while doesn't make sense
		// it spins like forever
		while(true) {
			System.out.println("before turn");
	        turnRadarRightRadians(Double.POSITIVE_INFINITY);
	        System.out.println("after turn");
	    }
	   
	}
	*/
	 
	
	/*
	 * 
	 * (non-Javadoc)
	 * @see robocode.Robot#run()
	 */
/*	public void run() {
	    // one rotate is needed to find the enemy
		// after that, the movement is executed in onScannedRobot
	    turnRadarRightRadians(Double.POSITIVE_INFINITY);
	}
*/	 
	/*
	public void onScannedRobot(ScannedRobotEvent e) {
	    //
	    // - the code below is in a loop since the radar locks the 
	    // tank enemy
	    // - once the enemy is out of the lock, on ScannedRobot is not executed anymore
	    // when the enemy is out of the lock,  setTurnRadarLeftRadians makes sure the 
	    // radar still moves
	    //
		double remaningRadius = getRadarTurnRemainingRadians();
	    setTurnRadarLeftRadians(getRadarTurnRemainingRadians());
	    System.out.println("numarul de remaing radius este : " + remaningRadius);
	}
	
	*/
	
	/*
	public void run() {
	 
		//
		// again full rotation searching is enough to
		// find the enemy 
		 //
	    turnRadarRightRadians(Double.POSITIVE_INFINITY);
	    do {
	        // Check for new targets.
	        // Only necessary for Narrow Lock because sometimes our radar is already
	        // pointed at the enemy and our onScannedRobot code doesn't end up telling
	        // it to turn, so the system doesn't automatically call scan() for us
	        // [see the javadocs for scan()].
	        scan();
	    } while (true);
	    
	}
	*/
	/*
	public void onScannedRobot(ScannedRobotEvent e) {
	    double radarTurn =
	    		// Absolute bearing to target
	    		// getHeadingRadians() : the direction of our heading relative to 0.0
	    		// e.getBearingRadians() : the orientation of the enemy comparing to ours
	    		// getRadarHeadingRadians() : our radar heading relative to 0.0
	    		getHeadingRadians() + e.getBearingRadians()
	    		// Subtract current radar heading to get turn required
	    		- getRadarHeadingRadians();
	 
	    System.out.println("Valoarea cu care se roteste" + Utils.normalRelativeAngle(radarTurn));
	    
	    // the value of Utils.normalRelativeAngle(radarTurn) might be 0.0
	    // thus, it will stop calling setTurnRadarRadians
	    // and it will need to call scan() again to work
	    
	    // "scan for enemies if its radar is turning, which might not happen if you are 
	    	//using a Narrow Lock and your enemy decides to stay still for 2 turns"
	    // what happens if the enemy stays still for 2 turns ? => the value is 0 
	    setTurnRadarRightRadians(2 * Utils.normalRelativeAngle(radarTurn));
	  
	}
	*/
	
	
	/*
	 * something similar to narrow lock
	public void run() {
	    // ...
	 
	    do {
	        // ...
	        // Turn the radar if we have no more turn, starts it if it stops and at the start of round
	        if ( getRadarTurnRemaining() == 0.0 )
	            setTurnRadarRightRadians( Double.POSITIVE_INFINITY );
	        execute();
	    } while ( true );
	 
	    // ...
	}
	 
	public void onScannedRobot(ScannedRobotEvent e) {
	    // ...
	 
	    // Absolute angle towards target
	    double angleToEnemy = getHeadingRadians() + e.getBearingRadians();
	 
	    // Subtract current radar heading to get the turn required to face the enemy, be sure it is normalized
	    double radarTurn = Utils.normalRelativeAngle( angleToEnemy - getRadarHeadingRadians() );
	 
	    // Distance we want to scan from middle of enemy to either side
	    // The 36.0 is how many units from the center of the enemy robot it scans.
	    double extraTurn = Math.min( Math.atan( 36.0 / e.getDistance() ), Rules.RADAR_TURN_RATE_RADIANS );
	 
	    // Adjust the radar turn so it goes that much further in the direction it is going to turn
	    // Basically if we were going to turn it left, turn it even more left, if right, turn more right.
	    // This allows us to overshoot our enemy so that we get a good sweep that will not slip.
	    radarTurn += (radarTurn < 0 ? -extraTurn : extraTurn);
	 
	    //Turn the radar
	    setTurnRadarRightRadians(radarTurn);
	 
	    // ...
	}
	*/
}
