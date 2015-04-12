package Model;

/**
 * common information about all the data, mine or enemies
 * @author Vlad Herescu
 *
 */
public abstract class Bot {

	/**
	 * the coordinate x of the bot
	 */
	int m_position_x;
	
	/**
	 * the coordinate y of the bot
	 */
	int m_position_y;
	
	/**
	 * the energy of the bot
	 * used to know if it was hit
	 */
	double m_energy;
	
}
