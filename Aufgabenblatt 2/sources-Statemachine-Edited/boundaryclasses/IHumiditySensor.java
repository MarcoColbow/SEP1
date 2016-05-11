package boundaryclasses;
/**
 * Interface for accessing the humidity sensor.
 * @author Thomas Lehmann
 * @version 2015-11-18
 */
public interface IHumiditySensor {
	/**
	 * Access to the measurement sensor for humidity.
	 * @return the actual measured humidity in percent.
	 */
	public double getHumidity(); 
}
