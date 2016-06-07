package fsm2;

public class TorOeffnenPumpenAbschalten extends State {

	@Override
	public void doAction(Steuerung sensor) {
		sensor.getPumpA().sendDeactivate();
		sensor.getPumpB().sendDeactivate();
		sensor.getGate().sendOpenGate();
		sensor.getSignals().switchLampBOn();
		while(!sensor.getGate().receivedGateOpen()){}
		sensor.getSignals().switchLampBOff();
		if (sensor.getErrorFlag())
		{
			sensor.changeState("Fehler");
		}
		else
		{
			sensor.changeState("Ueberwache");
		}
	}
}
