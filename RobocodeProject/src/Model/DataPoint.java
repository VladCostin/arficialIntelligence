package Model;

import Core.Core;

/**
 * the model used in dynamic clustering describing a firing situation
 * it will have associated a Bearing point
 * @author admin
 *
 */
public class DataPoint 
{
	
	/**
	 * the relative heading of the enemy at firing time 
	 */
	double m_heading;
	
	/**
	 * the position of the enemy at firing time relative to ours
	 */
	double m_coordX, m_coordY;
	
	/**
	* the speed of the enemy at firing time
	*/
	double m_speed;
	/**
	 * @param _coordX : the value x of the bot's position relative to ours
	 * @param _coordY : the value y of the bot's position relative to ours
	 * @param _speed : the enemy's speed 
	 * @param _heading : the enemy's heading
	 */
	public DataPoint(double _coordX, double _coordY, double _speed, double _heading)
	{
		m_heading = _heading / Core.m_Maxheading; 
		m_coordX = _coordX / Core.m_MaxWidth;
		m_coordY = _coordY / Core.m_MaxHeight;
		m_speed = _speed / Core.m_MaxSpeed;
	}
	
}
