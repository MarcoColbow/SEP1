package fsm;
/**
 * Interface for state machine evaluation.
 * @author Thomas Lehmann
 * @version 2015-11-18
 */

public interface IFSM {
	/** Evaluate the pending input signals depending on current state and perform
	 *  output actions.
	 */
	public void evaluate();
}
