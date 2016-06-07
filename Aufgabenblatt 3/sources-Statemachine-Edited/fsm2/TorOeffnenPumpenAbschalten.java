package fsm2;

public class TorOeffnenPumpenAbschalten extends State {

	@Override
	public void doAction(Steuerung sensor) {
		sensor.getPumpA().sendDeactivate();
		sensor.getPumpB().sendDeactivate();
		sensor.getGate().sendOpenGate();
		
		if (sensor.getErrorFlag())
			sensor.changeState("Fehler");
		else
			sensor.changeState("Ueberwache");
	}
}
