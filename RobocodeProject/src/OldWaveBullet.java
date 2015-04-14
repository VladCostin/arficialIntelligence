

import java.awt.geom.*;

import robocode.Rules;
import robocode.util.Utils;
 
/**
 * 
 * Wave : technique used to find the bearing that they should have shot at
 * <br>A wave consists of a source location (of the firing bot),
 *  a velocity (generally based on firing bot's bullet power),
 *  the time the wave was created, and the bearing to the target at fire time.
 * @author Vlad Herescu
 *
 */
/**
 * @author admin
 *
 */
/*
public class WaveBullet
{
	
	
	/**
	 * the location we are firing from
	 */
/*	private double startX, startY;
	/**
	 * where GuessFactor 0 is (i.e. - the direction to our target at fire time)
	 * GuessFactor 0 = the location  shooting directly where we see our target
	 * startBearing is the relative angle
	 */
/*	private double startBearing;
	/**
	 * the power (really the speed) of the bullet
	 */
/*	private double power;
	/**
	 * the time we fired
	 */
/*	private long   fireTime;
	/**
	 * what clock direction our opponent is moving in relative to us 
	 * (1 for clockwise, -1 for counterclockwise)
	 */
/*	private int    direction;
	
	/**
	 * where to return the "answer" to.
	 */
/*	private int[]  returnSegment;
 
	public WaveBullet(double x, double y, double bearing, double power,
			int direction, long time, int[] segment)
	{
		startX         = x;
		startY         = y;
		startBearing   = bearing;
		this.power     = power;
		this.direction = direction;
		fireTime       = time;
		returnSegment  = segment;
	}
	
	/**
	 * I don't know yet what is with the values 20 and 3
	 * @return : returning the speed of the bullet, depending on the power
	 * <br> it would mean that for power 1 => 17 pixels / turn
	 * 4 => 8 pixels / turn
	 */
/*	public double getBulletSpeed()
	{
		return 20 - power * 3;
	}
 */
	/**
	 * @return : the maximum angle considering that the enemy has the maximum velocity
	 * <br> equal with asin( EnemyVelocity / BulletSpeed)
	 * <br> where the maximim Enemy velocity is 8
	 */
/*	public double maxEscapeAngle()
	{
		return Math.asin(Rules.MAX_VELOCITY / getBulletSpeed());
	}
*/	
	/**
	 * this method will check if the wave has hit the enemy ( NOT THE BULLET)
	 * <br> it will figure out what GuessFactor the enemy is at, 
	 * find the appropriate index into the returnSegment and increment it
	 * @param enemyX : the x coordinate of the enemy's current position 
	 * @param enemyY
	 * @param currentTime
	 * @return : true or false if the "WAVE" has over passed the enemy
	 * false : it has not yet passed
	 */
/*	public boolean checkHit(double enemyX, double enemyY, long currentTime)
	{
		// if the distance from the wave origin to our enemy has passed
		// the distance the bullet would have traveled...
		
		// in other words, if the radius, range ( from the last position) is bigger then 
		// the most further point the enemy could have reached
		if (Point2D.distance(startX, startY, enemyX, enemyY) <= 
				(currentTime - fireTime) * getBulletSpeed())
		{
			// conversion from rectangle coordinate to polar coordinates
			// of the 
			double desiredDirection = Math.atan2(enemyX - startX, enemyY - startY);
			// ???
			double angleOffset = Utils.normalRelativeAngle(desiredDirection - startBearing);
			// parses the angle offset to guess Factor : -1 to 1
			double guessFactor =
				Math.max(-1, Math.min(1, angleOffset / maxEscapeAngle())) * direction;
			int index = (int) Math.round((returnSegment.length - 1) /2 * (guessFactor + 1));
			// this guesFactor has beenused before
			// we check in which segment this wave has to be added
			returnSegment[index]++;
			return true;
		}
		return false;
	}
}*/
	
	
