package fsm2;

public class Fehler extends State {
	
    @Override
	public void doAction(Sensor sensor) {
		while (!sensor.getOperatorPanel().receivedAcknowledgement()){}
		sensor.changeState("Ueberwache");
	}
}
