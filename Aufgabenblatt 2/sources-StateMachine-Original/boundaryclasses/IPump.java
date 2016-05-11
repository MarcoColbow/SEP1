package boundaryclasses;

/**
 * Interface for communication to a Pump Controller.
 * @author Thomas Lehmann
 * @version 2015-11-18
 */

public interface IPump {
	/**
	 * Send the message to start pumping.
	 */
	public void sendActivate();
	/**
	 * Send the message to stop pumping.
	 */
	public void sendDeactivate();
	/**
	  * Check, whether the message for activation confirmation has been received. 
	  * In case it has been received, the method returns true and the following up 
	  * call of the method returns only true, iff a further message of that type has been received
	  * in the meantime. 
	  * @return whether the message has been received or not.
	  */
	public boolean receivedActivated();
}
