package boundaryclasses;
/**
 * Interface for communication to Humidifier Controller.
 * @author Thomas Lehmann
 * @version 2015-11-18
 */
public interface IHumidifier {
	/**
	 * Send the message to the Humidifier Control to start humidifying.
	 */
	public void sendSprayOn();
	/**
	 * Send the message to the Humidifier Control to stop humidifying.
	 */
	public void sendSprayOff();
}
