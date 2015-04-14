package Core;

import java.util.HashMap;

import Model.DataPoint;
import Model.WaveBullet;

/**
 * static values used in all actions : radar scanning, movement, targeting 
 * @author Vlad Herescu
 *
 */
public class Core {

	/**
	 * the max speed of the BOT
	 */
	public static double m_MaxSpeed;
	
	/**
	 * the max heading of the BOT relative to us
	 */
	public static double m_Maxheading;
	
	/**
	 * the width of the battlefield, the enemy cannot be farther away than this
	 */
	public static double m_MaxWidth;
	
	/**
	 * the height of the battlefield, the enemy cannot be farther away than this
	 */
	public static double m_MaxHeight;
	
	/**
	 * the wave bullet instance to calculate when the wave has broken
	 * and the state of the enemy associated
	 */
	public HashMap<WaveBullet,DataPoint> m_waves;
	
	
	/**
	 * the state of the enemy when fired
	 * the collection history, but also the guessFactors associated, where we should have shot
	 */
	public HashMap<DataPoint,Double> m_guessFactors;
	
	/**
	 * 
	 */
	public Core()
	{
		m_waves = new HashMap<WaveBullet,DataPoint>();
		m_guessFactors = new HashMap<DataPoint, Double>();
	}
	
	
}
