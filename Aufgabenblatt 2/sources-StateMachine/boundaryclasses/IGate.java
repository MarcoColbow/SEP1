package boundaryclasses;
/**
 * Interface for communication to Gate Controller.
 * @author Thomas Lehmann
 * @version 2015-11-18
 */
public interface IGate {
	 /**
	  * Send the message to Gate Control to close the gate.
	  */
	 public void sendCloseGate();
	 /**
	  * Send the message to Gate Control to open the gate.
	  */
	 public void sendOpenGate();
	 /**
	  * Check, whether the message that the gate is fully closed from Gate Control has been 
	  * received. In case it has been received, the method returns true and the following up 
	  * call of the method returns only true, iff a further message of that type has been received
	  * in the meantime. 
	  * @return whether the message has been received or not.
	  */
	 public boolean receivedGateClosed();
	 /**
	  * Check, whether the message that the gate is fully open from Gate Control has been 
	  * received. In case it has been received, the method returns true and the following up 
	  * call of the method returns only true, iff a further message of that type has been received
	  * in the meantime. 
	  * @return whether the message has been received or not.
	  */
	 public boolean receivedGateOpen();
 }
