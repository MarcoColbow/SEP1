package boundaryclasses;
/**
 * Interface for Timer to measure durations.
 * @author Thomas Lehmann
 * @version 2015-11-18
 */

public interface ITimer {
	/**
	 * Start the timer. An already running timer is reset to the new duration. 
	 * @param seconds Set-point for the timer
	 */
	public void startTime( double seconds );
	/**
	 * If the timer is expired or has never been started, the method returns true.
	 * @return whether the timer is expired or not.
	 */
	public boolean isTimerExpired();
}
