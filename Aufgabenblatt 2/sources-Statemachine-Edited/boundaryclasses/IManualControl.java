package boundaryclasses;
/**
 * Interface for communication to Operator Panel.
 * @author Thomas Lehmann
 * @version 2015-11-18
 */
public interface IManualControl {
	/**
	 * Check, whether the acknowledge message operator panel has been received. 
	 * In case it has been received, the method returns true and the following up 
	 * call of the method returns only true, iff a further message of that type has been received
	 * in the meantime. 
	 * @return whether the message has been received or not.
	 */
	public boolean receivedAcknowledgement();
}
