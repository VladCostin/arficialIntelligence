package Model;

import java.util.ArrayList;

/**
 * 
 * the state of the game, consisting of the data 
 * <br> about the our BOT and the enemies BOTS
 * @author Vlad Herescu
 *
 */
public class StateGame {

	
	/**
	 * the data about my bot : heading, speed, radar position
	 */
	MyBot m_bot;
	
	/**
	 * the data about my enemies
	 */
	ArrayList<EnemyBot> m_enemies;
	
	/**
	 * the current enemy we are tracking
	 */
	EnemyBot m_currentEnemy;
	
}
