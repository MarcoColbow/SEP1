package fsm2;

public class Fehler extends State {
	
    @Override
	public void doAction(Steuerung sensor) {
		while (!sensor.getOperatorPanel().receivedAcknowledgement()){}
		sensor.changeState("Ueberwache");
	}
}
