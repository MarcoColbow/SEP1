package test;
import fsm.IFSM;
import implementation.FSMImplementation;
import org.junit.Before;
import org.junit.Test;

public class FSMImplementationTest {
	private PumpStub pumpA;
	private PumpStub pumpB;
	private GateStub gate;
	private OpticalSignalsStub signals;
	private HumiditySensorStub sensor;
	private HumidifierStub humidifier;
	private ManualControlStub operatorPanel;
	private IFSM uut;

	@Before
	public void testSetup(){
		pumpA = new PumpStub();
		pumpB = new PumpStub();
		gate = new GateStub();
		signals = new OpticalSignalsStub();
		sensor = new HumiditySensorStub();
		humidifier = new HumidifierStub();
		operatorPanel = new ManualControlStub();
		uut = new FSMImplementation(  pumpA,  pumpB,  gate,  signals,
				humidifier,  sensor,  operatorPanel) ;
	}
	
	@Test
	public void testLongestPath() {
		pumpA.neverGoOnline();
		HumiditySensorStub.forceHumidity(100);

		uut.evaluate();
	}

}
