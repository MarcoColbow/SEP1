package implementation;

import boundaryclasses.IGate;
import boundaryclasses.IHumidifier;
import boundaryclasses.IHumiditySensor;
import boundaryclasses.IManualControl;
import boundaryclasses.IOpticalSignals;
import boundaryclasses.IPump;
import fsm.IFSM;
import test.GateStub;
import test.HumidifierStub;
import test.HumiditySensorStub;
import test.ManualControlStub;
import test.OpticalSignalsStub;
import test.PumpStub;

public class FSMImplementation implements IFSM {
	private FSMState state;
	private IPump pumpA;
	private IPump pumpB;
	private IGate gate;
	private IOpticalSignals signals;
	private IHumiditySensor sensor;
	private IHumidifier humidifier;
	private IManualControl operatorPanel;
	private final double upperBound;
	private final double lowerBound;

	public FSMImplementation(IPump pumpA, IPump pumpB, IGate gate, IOpticalSignals signals, IHumidifier humidifier,
			IHumiditySensor sensor, IManualControl operatorPanel) {
		this.state = FSMState.HumidityOkay;
		this.pumpA = pumpA;
		this.pumpB = pumpB;
		this.gate = gate;
		this.signals = signals;
		this.sensor = sensor;
		this.humidifier = humidifier;
		this.operatorPanel = operatorPanel;
		upperBound = 60;
		lowerBound = 20;
	}

	/**
	 * This method definite the states of the machine
	 * 
	 * @param humidity
	 * @return
	 */
	private FSMState state(double humidity) {
		if (humidity > this.upperBound) {
			return FSMState.HumidityHigh;
		}
		if (humidity < this.lowerBound) {
			return FSMState.HumidityLow;
		}
		return FSMState.HumidityOkay;
	}

	@Override
	public void evaluate() {
		boolean errorflag = false;
		// TODO Auto-generated method stub
		while (true) {
			double aktuellHumid = this.sensor.getHumidity();
			switch (state(aktuellHumid)) {
			case HumidityOkay:
				System.out.println("Humidity OK");
				break;
			case HumidityLow:
				System.out.println("Humidity too low");
				this.humidifier.sendSprayOn();
				this.signals.switchLampAOn();
				while (aktuellHumid < this.lowerBound) {
					aktuellHumid = aktuellHumid + 5;
					System.out.println("Aktuell Humid is: " + aktuellHumid);
				}
				this.humidifier.sendSprayOff();
				this.signals.switchLampAOff();
				break;
			case HumidityHigh:
				System.out.println("Humidity too high");
				this.gate.sendCloseGate();
				this.signals.switchLampBOn();
				if (this.gate.receivedGateClosed()) {
					this.pumpA.sendActivate();
					this.pumpB.sendActivate();
				}
				//Timer here, 5s
				if (pumpA.receivedActivated() && pumpB.receivedActivated()) {
					while (aktuellHumid > this.upperBound) {
						aktuellHumid = aktuellHumid - 5;
						System.out.println("Aktuell Humid is: " + aktuellHumid);
					}
				} else {
					System.out.println("ERROR");
					errorflag = true;
				}
				this.pumpA.sendDeactivate();
				this.pumpB.sendDeactivate();
				this.gate.sendOpenGate();
				this.signals.switchLampBOff();
				if (errorflag) {
					while (!this.operatorPanel.receivedAcknowledgement()) {
						// Warte auf System Best�tigung}
					}
				}
				break;
			}

		}
	}

	public static void main(String[] args) {
		FSMImplementation test = new FSMImplementation(new PumpStub(), new PumpStub(), new GateStub(),
				new OpticalSignalsStub(), new HumidifierStub(), new HumiditySensorStub(), new ManualControlStub());
		test.evaluate();
	}
}
