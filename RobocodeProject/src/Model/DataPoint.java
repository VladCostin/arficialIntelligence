package Model;

import Core.CoreData;

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
		m_heading = _heading / CoreData.m_Maxheading; 
		m_coordX = _coordX / CoreData.m_MaxWidth;
		m_coordY = _coordY / CoreData.m_MaxHeight;
		m_speed = _speed / CoreData.m_MaxSpeed;
	}
	/**
	 * @return the m_heading
	 */
	public double getM_heading() {
		return m_heading;
	}
	/**
	 * @param m_heading the m_heading to set
	 */
	public void setM_heading(double m_heading) {
		this.m_heading = m_heading;
	}
	/**
	 * @return the m_coordX
	 */
	public double getM_coordX() {
		return m_coordX;
	}
	/**
	 * @param m_coordX the m_coordX to set
	 */
	public void setM_coordX(double m_coordX) {
		this.m_coordX = m_coordX;
	}
	/**
	 * @return the m_coordY
	 */
	public double getM_coordY() {
		return m_coordY;
	}
	/**
	 * @param m_coordY the m_coordY to set
	 */
	public void setM_coordY(double m_coordY) {
		this.m_coordY = m_coordY;
	}
	/**
	 * @return the m_speed
	 */
	public double getM_speed() {
		return m_speed;
	}
	/**
	 * @param m_speed the m_speed to set
	 */
	public void setM_speed(double m_speed) {
		this.m_speed = m_speed;
	}
	
}
