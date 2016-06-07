package fsm2;

import test.GateStub;
import test.HumidifierStub;
import test.HumiditySensorStub;
import test.ManualControlStub;
import test.OpticalSignalsStub;
import test.PumpStub;

public class Main {
	
	private static PumpStub pumpA;
	private static PumpStub pumpB;
	private static GateStub gate;
	private static OpticalSignalsStub signals;
	private static HumiditySensorStub sensor;
	private static HumidifierStub humidifier;
	private static ManualControlStub operatorPanel;
	
    public static void main(String[] args) {
		pumpA = new PumpStub();
		pumpB = new PumpStub();
		gate = new GateStub();
		signals = new OpticalSignalsStub();
		sensor = new HumiditySensorStub();
		humidifier = new HumidifierStub();
    	
    	
        Steuerung steuerung = new Steuerung(pumpA, pumpB, gate, signals, humidifier, sensor, operatorPanel);
        
        while (true)
        	steuerung.getCurrent().doAction(steuerung);
        
    }
}
