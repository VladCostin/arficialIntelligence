package Model;

import robocode.ScannedRobotEvent;

/**
 * data about one of my enemy
 * <br>extends the class Bot, common data about all bots
 * @author Vlad Herescu
 *
 */
public class EnemyBot extends Bot{

	/**
	 * the name of the bot, to identify him
	 */

	String m_name;

	/**
	 * @param _name : the name of the bot
	 */
	public EnemyBot(String _name) {
		
		m_name = _name;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	/**
	 * resets the data about the bot, for example his name
	 * <br> in case the main enemy has died
	 */
	public void reset() {
		
		m_name =  null;
		
	}

	/**
	 * @return : true if it has no name : it means the main enemy has died or hasn't been selected
	 * <br> false if there is a main enemy to attack
	 */
	public boolean none() {
		if(m_name == null)
			return true;
		return false;
	}

	/**
	 * @param e : the data about the robot detected with the radar
	 */
	public void update(ScannedRobotEvent e) {
		
		
		m_name = e.getName();
		
	}
	
}
