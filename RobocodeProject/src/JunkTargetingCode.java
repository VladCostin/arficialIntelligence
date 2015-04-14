import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;
import Model.EnemyBot;





/**
 * receiving data about the state of the game
 * @param e : the data about the robot detected
 */
/*public void receiveRadarData(ScannedRobotEvent e) 
{
	
	
}
private void detectStateFromHistory() 
{
	// TODO Auto-generated method stub
	
}


private void takeDecision(ScannedRobotEvent e) 
{
	double radarTurn, gunTurn,  absoluteBearing;
	
	

	 
	// double gunTurn = getHeadingRadians() + e.getBearingRadians() +
	//		 	- getGunHeadingRadians();
	 
	    
	
	 
	 if(m_lastHeading != null)
	 {
		// updateDataCircularMovement(e);
	 }
	 
	 
	// actions taken after evaluationg the state	
	 m_lastHeading = e.getHeading();
	 radarTurn = getHeadingRadians() + e.getBearingRadians()
	    		- getRadarHeadingRadians();
	 setTurnRadarRightRadians(2* Utils.normalRelativeAngle(radarTurn));
	 
	 
	
	 //To avoid premature shooting, call the getGunTurnRemaining() 
	 //method to see how far away your gun is from the target and don't fire until you're close.
	// setTurnGunRightRadians( Utils.normalRelativeAngle(gunTurn));
	// if (getGunHeat() == 0 && Math.abs(getGunTurnRemaining()) < 10)
	//		setFire(1);
	 
	/* 
	 double absoluteBearing = getHeadingRadians() + e.getBearingRadians();
	 setTurnGunRightRadians(Utils.normalRelativeAngle(absoluteBearing - 
	     getGunHeadingRadians() + (e.getVelocity() * Math.sin(e.getHeadingRadians() - 
	     absoluteBearing) / 13.0)));
	 setFire(2.0);
	
}
*/





/*
public class JunkTargetingCode {

}
/**
 * @param e : the event about the enemy when he is using circular movement
 */
/*public void updateDataCircularMovement(ScannedRobotEvent e) {
	
	double turnRate = e.getHeading() / m_lastHeading;
	double xRelative;
	double yRelative;
	double xT, yT;
	double absBearing = e.getBearingRadians() + getHeadingRadians();
	
	xRelative = e.getDistance() * Math.sin(absBearing);
	yRelative = e.getDistance() * Math.cos(absBearing);
	
	xT = xRelative - e.getVelocity() 
	
	
	
}
 */
/*
private void updateMainEnemy(ScannedRobotEvent e) {
	
	double xForce = 0, yForce = 0;
	
	if(m_currentEnemy.none())
		m_currentEnemy.setM_name(e.getName());
	
	if (e.getName().equals(m_currentEnemy.getM_name())) {
		
		// pretend that we rotate our bot and the enemy so our heading is 0 degrees
		// absolute bearing is the angle between our heading to this point, rotateing to left
		double absBearing = e.getBearingRadians() + getHeadingRadians();
		double x = getX()+e.getDistance()*Math.sin(absBearing);
		double y = getY()+e.getDistance()*Math.cos(absBearing);
		

//		System.out.println("Punct: " + x + " " + y);
//		double distance=new Point2D.Double(x, y).distance(getX(),getY());
		
//		System.out.println(distance + " " + e.getDistance());
	
		
		 xForce -= Math.sin(absBearing) / (e.getDistance() * e.getDistance());
		 yForce -= Math.cos(absBearing) / (e.getDistance() * e.getDistance());
		 
		 double angle = Math.atan2(xForce, yForce);
		 System.out.println("ungiul este" + angle);

		 if (xForce == 0 && yForce == 0) {
			    // If no force, do nothing
			} else if(Math.abs(angle-getHeadingRadians())<Math.PI/2){
			    setTurnRightRadians(Utils.normalRelativeAngle(angle-getHeadingRadians()));
			    setAhead(Double.POSITIVE_INFINITY);
			} else {
			    setTurnRightRadians(Utils.normalRelativeAngle(angle+Math.PI-getHeadingRadians()));
			    setAhead(Double.NEGATIVE_INFINITY);
			}
		 
	}
	
}
*/

/**
 * @param e : event regarding an enemy scanned
 */
/*
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
}*/