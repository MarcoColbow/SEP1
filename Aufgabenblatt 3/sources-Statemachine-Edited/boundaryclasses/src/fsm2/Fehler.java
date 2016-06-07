package fsm2;

public class Fehler extends State {
	
    @Override
	public void doAction(Steuerung sensor) {
    	if (sensor.getOperatorPanel().receivedAcknowledgement())
    	{
    		sensor.changeState("Ueberwache");
    	}
	}
}
