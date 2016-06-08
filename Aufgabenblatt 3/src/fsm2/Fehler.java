package fsm2;

public class Fehler extends State {
	
    @Override
	public void doAction(Steuerung steuerung) {
    	if (steuerung.getOperatorPanel().receivedAcknowledgement())
    	{
    		steuerung.changeState(States.UEBERWACHE);
    	}
	}
}
