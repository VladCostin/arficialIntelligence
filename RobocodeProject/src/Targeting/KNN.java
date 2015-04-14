package Targeting;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Core.CoreData;
import Model.DataPoint;

/**
 * implementing KNN
 * @author Vlad Herescu
 *
 */
public class KNN {
	
	int m_similarSituations;
	
	/**
	 * how many old data shall we use
	 */
	int m_MaxSize;
	
	/**
	 * initializing data to use the KNN
	 */
	public KNN()
	{
		m_similarSituations = 50;
		m_MaxSize = 5000;
	}

	/**
	 * @param _currentPoint : the current situation of the enemy regarding our state
	 */
	public DataPoint getMostSimilarDataPoints(DataPoint _currentPoint)
	{
		if(CoreData.m_guessFactors.keySet().size() == 0)
			return null;
		
		ArrayList<DataPoint> m_mostNearest = new ArrayList<DataPoint>();
		ArrayList<Integer> m_indexAlreadyAdded = new ArrayList<Integer>();
	//	Set<DataPoint> m_historyPoints = CoreData.m_guessFactors.keySet();
	//	String[] GPXFILES1 = myset.toArray(new String[myset.size()]);
		
		DataPoint[] m_historyPoints = CoreData.m_guessFactors.keySet().toArray( new DataPoint[CoreData.m_guessFactors.keySet().size()]  );
		
		Integer indexAdded, index;
		double minDistance;
		int nrSimilarSituation = Math.min(m_similarSituations,m_historyPoints.length); //m_historyPoints.size());
		System.out.println("Size of the history :  " + CoreData.m_guessFactors.size() + " " + CoreData.m_waves.size() + " " + nrSimilarSituation);
		
		for(int i = 0; i < nrSimilarSituation; i++)
		{
			indexAdded = 0;
			minDistance = 100;
			index = 0;
			for(DataPoint point : m_historyPoints)
			{
				if(m_indexAlreadyAdded.contains(indexAdded))
					break;
				
				double distance = calcualateDistance(_currentPoint, point);
				if(distance < minDistance)
				{
					minDistance = distance;
					indexAdded = index;
				}
				
				
				
				index++;
			}
			System.out.println("Distanta : " + minDistance);
			m_indexAlreadyAdded.add(indexAdded);
			m_mostNearest.add(m_historyPoints[indexAdded]);
		}
		
		
		return m_mostNearest.get(0);
		
		
		/*
		define nearestPoints, collection of size <numNeighbors>
		// umplu colectia cu primele puncte din allPoints
		// le consider pe acestea cele mai apropiate de starea curenta 
	    fill nearestPoints with the first <numNeighbors> points from allPoints
	   // nu inteleg de ce este double array
	// inteleg ca este un array de distante 
	// unde se vor salva distantele euclidiene
	    define nearestDistancesSq, double array of size <numNeighbors>
	// iau distanta cea mai apropiata distanta dintre 
	// punctul curent si primul punct din nearest points ( sau all points in cazul asta) 
	    set nearestDistancesSq[0] to distanceSquared(currentPoint, nearestPoints[0])

	// consider distanta cea mai scurta si cea mai lunga distanta
	    define longestDistanceSq, initialize to nearestDistanceSq[0]
	    define longestIndex, initialize to 0

	// initializez si restul de vectorului de distante cu distantele de la punctual curent la celelalte puncte nearest points ( cele mai apropiate puncte)
	    for x = 1 to numNeighbors-1
	        nearestDistancesSq[x] = distanceSquared(currentPoint, nearestPoints[x]);
	        if (nearestDistancesSq[x] > longestDistanceSq)
	            longestDistanceSq = nearestDistancesSq[x]
	            longestIndex = x

	    for x = numNeighbors to allPoints.length-1
	        thisDistanceSq = distanceSquared(currentPoint, allPoints[x]);
	        if (thisDistanceSq < longestDistanceSq)
	            nearestPoints[longestIndex] = allPoints[x]
	            nearestDistanceSq[longestIndex] = thisDistanceSq
	            // reapelez primul for, ca sa aleg longestDistandeSq si index
			find the new maximum value in nearestDistancesSq
	                set longestDistanceSq to the maximum value
	                set longestIndex to the array index of the maximum value

	    return nearestPoints
	    		*/
	}

	/**
	 * @param _currentPoint : the current point data of the game
	 * @param _point : a point from the enemy's history points
	 * @return
	 */
	public double calcualateDistance(DataPoint _currentPoint, DataPoint _point) {
		
	/*	System.out.println("X value " + _currentPoint.getM_coordX() + " "  +point.getM_coordX());
		System.out.println("Y value " + _currentPoint.getM_coordY() + " "  +point.getM_coordY());
		System.out.println("Velocity value " + _currentPoint.getM_speed() + " "  +point.getM_speed());
		System.out.println("Heading value " + _currentPoint.getM_heading() + " "  +point.getM_heading());
		*/
		
		double a = _currentPoint.getM_coordX() - _point.getM_coordX();
		double b =  _currentPoint.getM_coordY()  - _point.getM_coordY();
		double c = _currentPoint.getM_speed() - _point.getM_speed();
		double d = _currentPoint.getM_heading()  - _point.getM_heading();

		
		return a * a + b * b + c * c + d * d;
	}

	/**
	 * @return the m_similarSituations
	 */
	public int getM_similarSituations() {
		return m_similarSituations;
	}

	/**
	 * @param m_similarSituations the m_similarSituations to set
	 */
	public void setM_similarSituations(int m_similarSituations) {
		this.m_similarSituations = m_similarSituations;
	}

	/**
	 * @return the m_MaxSize
	 */
	public int getM_MaxSize() {
		return m_MaxSize;
	}

	/**
	 * @param m_MaxSize the m_MaxSize to set
	 */
	public void setM_MaxSize(int m_MaxSize) {
		this.m_MaxSize = m_MaxSize;
	}
	
	
	
}
