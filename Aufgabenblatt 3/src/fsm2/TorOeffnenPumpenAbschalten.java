package fsm2;

public class TorOeffnenPumpenAbschalten extends State {

	@Override
	public void doAction(Steuerung steuerung) {
		steuerung.getPumpA().sendDeactivate();
		steuerung.getPumpB().sendDeactivate();
		steuerung.getGate().sendOpenGate();
		steuerung.getSignals().switchLampBOn();
		while(!steuerung.getGate().receivedGateOpen()){}
		steuerung.getSignals().switchLampBOff();
		if (steuerung.getErrorFlag())
		{
			steuerung.changeState(States.FEHLER);
		}
		else
		{
			steuerung.changeState(States.UEBERWACHE);
		}
	}
}
