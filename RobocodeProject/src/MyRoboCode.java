import java.util.Random;

import robocode.AdvancedRobot;
import robocode.Robot;
import robocode.ScannedRobotEvent;



public class MyRoboCode extends AdvancedRobot{
	

	
	public void run()
	{
	
		Random randomGenerator = new Random();
		 do {
		   //     turnGunRightRadians(Double.POSITIVE_INFINITY);
		//	 turnRadarRightRadians(360);
			 turnGunRightRadians(360);
		} while (true);
		/*
		while(true)
		{
			
			//System.out.println("sdfdsfs : " + (randomGenerator.nextInt(100) + 20));
			
			ahead( randomGenerator.nextInt(100) + 20);
			turnGunLeft(360);
			ahead(- (randomGenerator.nextInt(100) + 20));
			
			turnLeft( randomGenerator.nextInt(30) + 20);
			
		}
		*/
	}
	
	
	public void onScannedRobot(ScannedRobotEvent e)
	{
		
		System.out.println(e.getName() + " " + e.getBearing());
		/*
		e.
		System.out.println("onScannedRobot : distance " + e.getDistance() + " " + e.getHeading() + " " + e.getBearing());
		fire(1);
		*/
	}
	
	
}
