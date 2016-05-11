package test;
/**
 * Test Framework for testing the FSM from Practice 3
 * @author Thomas Lehmann
 * @version 2015-11-18
 */
import static org.junit.Assert.*;
import fsm.IFSM;
import implementation.FSMImplementation;
import implementation.FSMState;

import org.junit.Before;
import org.junit.Test;

import boundaryclasses.IGate;
import boundaryclasses.IHumidifier;
import boundaryclasses.IHumiditySensor;
import boundaryclasses.IManualControl;
import boundaryclasses.IOpticalSignals;
import boundaryclasses.IPump;

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
	public void testPath() {

	}

}
