package Model;

import java.util.HashMap;

public class Action {

	/*
	int m_ahead;
	
	int m_rotateGun;
	
	int m_rotateRadar;
	
	int m_rotate;
	*/
	
	
	HashMap<ActionEnum, Float> m_actionMap;
	
	public Action() {
		m_actionMap = new HashMap<ActionEnum,Float>();
	}
	
	public Action(int _ahead, int _rotateGun, int _rotateRadar, int _rotate)
	{
		m_actionMap = new HashMap<ActionEnum,Float>();
	}
}
