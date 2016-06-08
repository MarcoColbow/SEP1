package test;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import boundaryclasses.IGate;
import boundaryclasses.IHumidifier;
import boundaryclasses.IHumiditySensor;
import boundaryclasses.IManualControl;
import boundaryclasses.IOpticalSignals;
import boundaryclasses.IPump;
import fsm2.Dummy;
import fsm2.Fehler;
import fsm2.FehlerFestgestellt;
import fsm2.HumidityLow;
import fsm2.PumpenEinschalten;
import fsm2.States;
import fsm2.Steuerung;
import fsm2.TorOeffnenPumpenAbschalten;
import fsm2.TorSchliessen;
import fsm2.TorZuPumpeAn;
import fsm2.Ueberwache;

public class FSMImplementationTest {
	private final int VALUE_OVER_HUMIDITYMAX = 61;
	private final int VALUE_LOWER_HUMIDITYMIN = 19;
	private final int VALUE_BETWEEN_HUMIDITYLIMITS = 40;
	private Steuerung steuerung;
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
		steuerung = new Steuerung(pumpA,  pumpB,  gate,  signals, humidifier,  humiditySensor,  operatorPanel);
		HumiditySensorStub.forceHumidity(0);
	}
	
	
	/**
	 * -----------------------------WHITEBOX - KONFORMANZ-----------------------------
	 */
	
	/**
	 * Transition from Ueberwache to HumidityLow
	 */
	@Test
	public void testHumidityLowerMinWhite() {
		HumiditySensorStub.forceHumidity(VALUE_LOWER_HUMIDITYMIN);
		steuerung.changeState(States.UEBERWACHE);
		steuerung.getCurrent().doAction(steuerung);
		assertTrue(steuerung.getCurrent() instanceof HumidityLow);
	}
	
	/**
	 * Transition from HumidityLow to Ueberwache
	 */
	@Test
	public void testHumidityLowToUeberwacheWhite(){
		HumiditySensorStub.forceHumidity(VALUE_LOWER_HUMIDITYMIN);
		steuerung.changeState(States.HUMIDITY_LOW);
		steuerung.getCurrent().doAction(steuerung);
		assertTrue(steuerung.getCurrent() instanceof Ueberwache);
	}
	
	/**
	 * Transition from Ueberwache to TorSchliessen
	 */
	@Test
	public void testUeberwacheToTorSchliessenWhite(){
		HumiditySensorStub.forceHumidity(VALUE_OVER_HUMIDITYMAX);
		steuerung.changeState(States.UEBERWACHE);
		steuerung.getCurrent().doAction(steuerung);
		assertTrue(steuerung.getCurrent() instanceof TorSchliessen);
	}
	
	/**
	 * Transition from TorSchliessen to PumpenEinschalten
	 */
	@Test
	public void testTorSchliessenToPumpenEinschaltenWhite(){
		HumiditySensorStub.forceHumidity(VALUE_OVER_HUMIDITYMAX);
		steuerung.changeState(States.TOR_SCHLIESSEN);
		steuerung.getCurrent().doAction(steuerung);
		assertTrue(steuerung.getCurrent() instanceof PumpenEinschalten);
	}
	
	/**
	 * Transition from PumpenEinschalten to FehlerFestgestellt
	 */
	@Test
	public void testPumpenEinschaltenToFehlerFestgestelltWhite(){
		HumiditySensorStub.forceHumidity(VALUE_OVER_HUMIDITYMAX);
		((PumpStub) pumpA).setNeverGoOnline(true);
		((PumpStub) pumpB).setNeverGoOnline(true);
		steuerung.changeState(States.PUMPEN_EINSCHALTEN);
		steuerung.getCurrent().doAction(steuerung);
		assertTrue(steuerung.getCurrent() instanceof FehlerFestgestellt);
	}
	
	/**
	 * Transition from PumpenEinschalten to TorZuPumpeAn
	 */
	@Test
	public void testPumpenEinschaltenToTorZuPumpeAnWhite(){
		HumiditySensorStub.forceHumidity(VALUE_OVER_HUMIDITYMAX);
		((PumpStub) pumpA).setNeverGoOnline(false);
		((PumpStub) pumpB).setNeverGoOnline(false);
		((PumpStub) pumpA).set_forceActivation(true);
		((PumpStub) pumpB).set_forceActivation(true);
		steuerung.changeState(States.PUMPEN_EINSCHALTEN);
		steuerung.getCurrent().doAction(steuerung);
		assertTrue(steuerung.getCurrent() instanceof TorZuPumpeAn);
	}
	
	/**
	 * Transition from TorZuPumpeAn to TorOeffnenPumpenAbschalten
	 */
	@Test
	public void testFromTorZuPumpeAnToTorOeffnenPumpenAbschaltenWhite(){
		HumiditySensorStub.forceHumidity(VALUE_OVER_HUMIDITYMAX);
		steuerung.changeState(States.TOR_ZU_PUMPEN_AN);
		steuerung.getCurrent().doAction(steuerung);
		assertTrue(steuerung.getCurrent() instanceof TorOeffnenPumpenAbschalten);
	}
	
	/**
	 * Transition from FehlerFestgestellt to TorOeffnenPumpenAbschalten
	 */
	@Test
	public void testFromFehlerFestgestelltToTorOeffnenPumpenAbschaltenWhite(){
		HumiditySensorStub.forceHumidity(VALUE_OVER_HUMIDITYMAX);
		steuerung.changeState(States.FEHLER_FESTGESTELLT);
		steuerung.getCurrent().doAction(steuerung);
		assertTrue(steuerung.getCurrent() instanceof TorOeffnenPumpenAbschalten);
	}
	
	/**
	 * Transition from TorOeffnenPumpenAbschalten to Ueberwache
	 */
	@Test
	public void testFromTorOeffnenPumpenAbschaltenToUeberwacheWhite(){
		HumiditySensorStub.forceHumidity(VALUE_BETWEEN_HUMIDITYLIMITS);
		steuerung.changeState(States.TOR_OEFFNEN_PUMPEN_ABSCHALTEN);
		steuerung.getCurrent().doAction(steuerung);
		assertTrue(steuerung.getCurrent() instanceof Ueberwache);
	}
	
	/**
	 * Transition from TorOeffnenPumpenAbschalten to Fehler
	 */
	@Test
	public void testFromTorOeffnenPumpenAbschaltenToFehlerWhite(){
		steuerung.changeState(States.TOR_OEFFNEN_PUMPEN_ABSCHALTEN);
		steuerung.setErrorFlag(true);
		steuerung.getCurrent().doAction(steuerung);
		assertTrue(steuerung.getCurrent() instanceof Fehler);
	}
	
	/**
	 * Transition from Fehler to Fehler
	 */
	@Test
	public void testFromFehlerToFehlerWhite(){
		steuerung.changeState(States.FEHLER);
		steuerung.getCurrent().doAction(steuerung);
		assertTrue(steuerung.getCurrent() instanceof Fehler);
	}
	
	
	/**
	 * Transition from Fehler to Ueberwache
	 */
	@Test
	public void testFromFehlerToUeberwacheWhite(){
		steuerung.changeState(States.FEHLER);
		steuerung.getOperatorPanel().pressButton();
		steuerung.getCurrent().doAction(steuerung);
		assertTrue(steuerung.getCurrent() instanceof Ueberwache);
	}
	
	/**
	 * -----------------------------WHITEBOX - ROBUSTHEIT-----------------------------
	 */
	
	/**
	 * Just to get covered default doAction
	 */
	@Test
	public void testOfErrorWhite(){
		steuerung.changeState(States.DUMMY);
		steuerung.getCurrent().doAction(steuerung);
		assertTrue(steuerung.getCurrent() instanceof Dummy);
	}
	
	/**
	 * -----------------------------BLACKBOX - KONFORMANZ-----------------------------
	 */
	
	/**
	 * Transition possibilities from Ueberwache
	 */
	@Test
	public void testHumidityLowerMinBlack() {
		steuerung.changeState(States.UEBERWACHE);
		steuerung.getCurrent().doAction(steuerung);
		assertTrue(steuerung.getCurrent() instanceof HumidityLow   || 
				   steuerung.getCurrent() instanceof TorSchliessen ||
				   steuerung.getCurrent() instanceof Ueberwache);
	}
	
	/**
	 * Transition possibilities from HumidityLow
	 */
	@Test
	public void testHumidityLowToUeberwacheBlack(){
		steuerung.changeState(States.HUMIDITY_LOW);
		steuerung.getCurrent().doAction(steuerung);
		assertTrue(steuerung.getCurrent() instanceof Ueberwache);
	}
	
	/**
	 * Transition possibilities from TorSchliessen
	 */
	@Test
	public void testTorSchliessenToPumpenEinschaltenBlack(){
		steuerung.changeState(States.TOR_SCHLIESSEN);
		steuerung.getCurrent().doAction(steuerung);
		assertTrue(steuerung.getCurrent() instanceof PumpenEinschalten);
	}
	
	/**
	 * Transition possibilities from PumpenEinschalten
	 */
	@Test
	public void testPumpenEinschaltenToFehlerFestgestelltBlack(){
		steuerung.changeState(States.PUMPEN_EINSCHALTEN);
		steuerung.getCurrent().doAction(steuerung);
		assertTrue(steuerung.getCurrent() instanceof FehlerFestgestellt ||
				   steuerung.getCurrent() instanceof TorZuPumpeAn);
	}
	
	/**
	 * Transition possibilities from TorZuPumpeAn
	 */
	@Test
	public void testFromTorZuPumpeAnToTorOeffnenPumpenAbschaltenBlack(){
		steuerung.changeState(States.TOR_ZU_PUMPEN_AN);
		steuerung.getCurrent().doAction(steuerung);
		assertTrue(steuerung.getCurrent() instanceof TorOeffnenPumpenAbschalten);
	}
	
	/**
	 * Transition possibilities from FehlerFestgestellt
	 */
	@Test
	public void testFromFehlerFestgestelltToTorOeffnenPumpenAbschaltenBlack(){
		steuerung.changeState(States.FEHLER_FESTGESTELLT);
		steuerung.getCurrent().doAction(steuerung);
		assertTrue(steuerung.getCurrent() instanceof TorOeffnenPumpenAbschalten);
	}
	
	/**
	 * Transition possibilities from TorOeffnenPumpenAbschalten
	 */
	@Test
	public void testFromTorOeffnenPumpenAbschaltenToUeberwacheBlack(){
		steuerung.changeState(States.TOR_OEFFNEN_PUMPEN_ABSCHALTEN);
	    steuerung.setErrorFlag(getRandomBoolean());
		steuerung.getCurrent().doAction(steuerung);
		assertTrue(steuerung.getCurrent() instanceof Ueberwache ||
				   steuerung.getCurrent() instanceof Fehler);
	}
	
	/**
	 * Transition possibilities from Fehler
	 */
	@Test
	public void testFromFehlerToFehlerBlack(){
		steuerung.changeState(States.FEHLER);
		if(getRandomBoolean())
		{
			operatorPanel.pressButton();
		}
		steuerung.getCurrent().doAction(steuerung);
		assertTrue(steuerung.getCurrent() instanceof Fehler ||
				   steuerung.getCurrent() instanceof Ueberwache);
	}
	
	/**
	 * -----------------------------BLACKBOXBOX - ROBUSTHEIT-----------------------------
	 */
	/**
	 * Just to get covered default doAction(actually same as above...)
	 */
	@Test
	public void testOfErrorBlack(){
		steuerung.changeState(States.DUMMY);
		steuerung.getCurrent().doAction(steuerung);
		assertTrue(steuerung.getCurrent() instanceof Dummy);
	}
	
	
	public boolean getRandomBoolean()
	{
		return Math.random() < 0.5 ? true : false;
	}
}
