
	/**
	 * @param e
	 */
/*	public void waveCode(ScannedRobotEvent e) {
		

		
		// ...
	    // (other onScannedRobot code, might be radar/movement)
		// ...
		 
		// Enemy absolute bearing, you can use your one if you already declare it.
		double absBearing = getHeadingRadians() + e.getBearingRadians();
		 
		// find our enemy's location:
		double ex = getX() + Math.sin(absBearing) * e.getDistance();
		double ey = getY() + Math.cos(absBearing) * e.getDistance();
		 
		// Let's process the waves now:
		for (int i=0; i < m_waves.size(); i++)
		{
			WaveBullet currentWave = (WaveBullet)m_waves.get(i);
			// if the wave has over passed the enemy => it has not hit him
			// we can remove him
			if (currentWave.checkHit(ex, ey, getTime()))
			{
				m_waves.remove(currentWave);
				i--;
			}
		}
		double power = Math.min(400 / e.getDistance(), 3);
		// don't try to figure out the direction they're moving , meaning detect the sens, not the heading
		// if they're not moving, just use the direction we had before
		if (e.getVelocity() != 0)
		{
			if (Math.sin(e.getHeadingRadians()-absBearing)*e.getVelocity() < 0)
				direction = -1;
			else
				direction = 1;
		}
	//	int[] currentStats = stats[(int)(e.getDistance() / 100)]; // It doesn't look silly now!
		int[] currentStats = stats; // This seems silly, but I'm using it to
							    // show something else later
		WaveBullet newWave = new WaveBullet(getX(), getY(), absBearing, power,
		                        direction, getTime(), currentStats);		
		
		// detecting the segment where the enemy has been most of the time
		int bestindex = 15;	// initialize it to be in the middle, guessfactor 0.
		for (int i=0; i<31; i++)
			if (currentStats[bestindex] < currentStats[i])
				bestindex = i;
 
		// this should do the opposite of the math in the WaveBullet:
		double guessfactor = (double)(bestindex - (stats.length - 1) / 2)
                        / ((stats.length - 1) / 2);
		double angleOffset = direction * guessfactor * newWave.maxEscapeAngle();
                double gunAdjust = Utils.normalRelativeAngle(
                        absBearing - getGunHeadingRadians() + angleOffset);
                setTurnGunRightRadians(gunAdjust);
                
       // ???     
       if (getGunHeat() == 0 && gunAdjust < Math.atan2(9, e.getDistance())) 
       {
    	   m_waves.add(newWave);
    	   fire(power);
       }
       
		double radarTurn;
		
		 radarTurn = getHeadingRadians() + e.getBearingRadians()
		    		- getRadarHeadingRadians();
		 setTurnRadarRightRadians(2* Utils.normalRelativeAngle(radarTurn));
	}
*/