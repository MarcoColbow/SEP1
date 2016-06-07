package test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import boundaryclasses.IGate;
import boundaryclasses.IHumidifier;
import boundaryclasses.IHumiditySensor;
import boundaryclasses.IManualControl;
import boundaryclasses.IOpticalSignals;
import boundaryclasses.IPump;
import fsm2.*;

public class FSMImplementationTest {
	private final int VALUE_OVER_HUMIDITYMAX = 61;
	private final int VALUE_LOWER_HUMIDITYMIN = 19;
	private final int VALUE_BETWEEN_HUMIDITYLIMITS = 40;
	private Steuerung sensor;
	private IPump pumpA;
	private IPump pumpB;
	private IGate gate;
	private IOpticalSignals signals;
	private IHumidifier humidifier;
	private IHumiditySensor humiditySensor;
	private IManualControl operatorPanel;

	@Before
	public void testSetup(){
		pumpA = new PumpStub();
		pumpB = new PumpStub();
		gate = new GateStub();
		signals = new OpticalSignalsStub();
		humidifier = new HumidifierStub();
		humiditySensor = new HumiditySensorStub();
		operatorPanel = new ManualControlStub();
		sensor = new Steuerung(pumpA,  pumpB,  gate,  signals, humidifier,  humiditySensor,  operatorPanel) ;
	}
	
	/**
	 * Transition from Ueberwache to HumidityLow
	 */
	@Test
	public void testHumidityLowerMin() {
		HumiditySensorStub.forceHumidity(VALUE_LOWER_HUMIDITYMIN);
		sensor.changeState("Ueberwache");
		sensor.getCurrent().doAction(sensor);
		assertTrue(sensor.getCurrent() instanceof HumidityLow);
	}
	
	/**
	 * Transition from HumidityLow to Ueberwache
	 */
	@Test
	public void testHumidityLowToUeberwache(){
		HumiditySensorStub.forceHumidity(VALUE_LOWER_HUMIDITYMIN);
		sensor.changeState("HumidityLow");
		sensor.getCurrent().doAction(sensor);
		assertTrue(sensor.getCurrent() instanceof Ueberwache);
	}
	
	/**
	 * Transition from Ueberwache to TorSchliessen
	 */
	@Test
	public void testUeberwacheToTorSchliessen(){
		HumiditySensorStub.forceHumidity(VALUE_OVER_HUMIDITYMAX);
		sensor.changeState("Ueberwache");
		sensor.getCurrent().doAction(sensor);
		assertTrue(sensor.getCurrent() instanceof TorSchliessen);
	}
	
	/**
	 * Transition from TorSchliessen to PumpenEinschalten
	 */
	@Test
	public void testTorSchliessenToPumpenEinschalten(){
		HumiditySensorStub.forceHumidity(VALUE_OVER_HUMIDITYMAX);
		sensor.changeState("TorSchliessen");
		sensor.getCurrent().doAction(sensor);
		assertTrue(sensor.getCurrent() instanceof PumpenEinschalten);
	}
	
	/**
	 * Transition from PumpenEinschalten to FehlerFestgestellt
	 */
	@Test
	public void testPumpenEinschaltenToFehlerFestgestellt(){
		HumiditySensorStub.forceHumidity(VALUE_OVER_HUMIDITYMAX);
		((PumpStub) pumpA).setNeverGoOnline(true);
		((PumpStub) pumpB).setNeverGoOnline(true);
		sensor.changeState("PumpenEinschalten");
		sensor.getCurrent().doAction(sensor);
		assertTrue(sensor.getCurrent() instanceof FehlerFestgestellt);
	}
	
	/**
	 * Transition from PumpenEinschalten to TorZuPumpeAn
	 */
	@Test
	public void testPumpenEinschaltenToTorZuPumpeAn(){
		HumiditySensorStub.forceHumidity(VALUE_OVER_HUMIDITYMAX);
		((PumpStub) pumpA).setNeverGoOnline(false);
		((PumpStub) pumpB).setNeverGoOnline(false);
		sensor.changeState("PumpenEinschalten");
		sensor.getCurrent().doAction(sensor);
		assertTrue(sensor.getCurrent() instanceof TorZuPumpeAn);
	}
	
	/**
	 * Transition from TorZuPumpeAn to TorOeffnenPumpenAbschalten
	 */
	@Test
	public void testFromTorZuPumpeAnToTorOeffnenPumpenAbschalten(){
		HumiditySensorStub.forceHumidity(VALUE_OVER_HUMIDITYMAX);
		((PumpStub) pumpA).setNeverGoOnline(false);
		((PumpStub) pumpB).setNeverGoOnline(false);
		sensor.changeState("TorZuPumpeAn");
		sensor.getCurrent().doAction(sensor);
		assertTrue(sensor.getCurrent() instanceof TorOeffnenPumpenAbschalten);
	}
	
	/**
	 * Transition from FehlerFestgestellt to TorOeffnenPumpenAbschalten
	 */
	@Test
	public void testFromFehlerFestgestelltToTorOeffnenPumpenAbschalten(){
		HumiditySensorStub.forceHumidity(VALUE_OVER_HUMIDITYMAX);
		((PumpStub) pumpA).setNeverGoOnline(true);
		((PumpStub) pumpB).setNeverGoOnline(true);
		sensor.changeState("FehlerFestgestellt");
		sensor.getCurrent().doAction(sensor);
		assertTrue(sensor.getCurrent() instanceof TorOeffnenPumpenAbschalten);
	}
	
	/**
	 * Transition from TorOeffnenPumpenAbschalten to Ueberwache
	 */
	@Test
	public void testFromTorOeffnenPumpenAbschaltenToUeberwache(){
		HumiditySensorStub.forceHumidity(VALUE_BETWEEN_HUMIDITYLIMITS);
		sensor.changeState("TorOeffnenPumpenAbschalten");
		sensor.getCurrent().doAction(sensor);
		assertTrue(sensor.getCurrent() instanceof Ueberwache);
	}
	
	/**
	 * Transition from TorOeffnenPumpenAbschalten to Fehler
	 */
	@Test
	public void testFromTorOeffnenPumpenAbschaltenToFehler(){
		HumiditySensorStub.forceHumidity(VALUE_OVER_HUMIDITYMAX);
		sensor.changeState("TorOeffnenPumpenAbschalten");
		sensor.setErrorFlag(true);
		sensor.getCurrent().doAction(sensor);
		assertTrue(sensor.getCurrent() instanceof Fehler);
	}
	
	/**
	 * Transition from Fehler to Fehler no possible test?
	 */
	@Test
	public void testFromFehlerToFehler(){
		sensor.changeState("Fehler");
		sensor.getCurrent().doAction(sensor);
		assertTrue(sensor.getCurrent() instanceof Fehler);
	}
	
	
	/**
	 * Transition from Fehler to Ueberwache
	 */
	@Test
	public void testFromFehlerToUeberwache(){
		sensor.changeState("Fehler");
		sensor.getOperatorPanel().pressButton();
		sensor.getCurrent().doAction(sensor);
		assertTrue(sensor.getCurrent() instanceof Ueberwache);
	}
	
	/**
	 * Just to get covered default doAction
	 */
	@Test
	public void testOfError(){
		sensor.changeState("Dummy");
		sensor.getOperatorPanel().pressButton();
		sensor.getCurrent().doAction(sensor);
		assertTrue(sensor.getCurrent() instanceof Dummy);
	}
}
