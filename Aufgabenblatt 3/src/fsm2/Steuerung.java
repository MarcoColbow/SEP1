package fsm2;

import boundaryclasses.IGate;
import boundaryclasses.IHumidifier;
import boundaryclasses.IHumiditySensor;
import boundaryclasses.IManualControl;
import boundaryclasses.IOpticalSignals;
import boundaryclasses.IPump;

public class Steuerung {
	private final State states [] = {new Ueberwache(), new HumidityLow(), new TorSchliessen(), new PumpenEinschalten(),
			  new FehlerFestgestellt(), new TorZuPumpeAn(), new TorOeffnenPumpenAbschalten(), new Fehler(),
			  new Dummy()};
    private final double upperBound;
    private final double lowerBound;
	private boolean errorFlag;
	private State current;
	private IPump pumpA;
    private IPump pumpB;
    private IGate gate;
    private IOpticalSignals signals;
    private IHumiditySensor steuerung;
    private IHumidifier humidifier;
    private IManualControl operatorPanel;
	
	public Steuerung(IPump pumpA, IPump pumpB, IGate gate, IOpticalSignals signals, IHumidifier humidifier,
            IHumiditySensor sensor, IManualControl operatorPanel){
		errorFlag = false;
        this.pumpA = pumpA;
        this.pumpB = pumpB;
        this.gate = gate;
        this.signals = signals;
        this.steuerung = sensor;
        this.humidifier = humidifier;
        this.operatorPanel = operatorPanel;
        upperBound = 60;
        lowerBound = 20;
        changeState(States.UEBERWACHE);
	}
	
	public void changeState(States state){
        System.out.printf("Aktueller Zustand: %s\n", state);
	    current = states[state.ordinal()];
	}
	
	public double getUpperBound(){
	    return upperBound;
	}
	
	public double getLowerBound(){
        return lowerBound;
    }

    public State getCurrent() {
        return current;
    }

    public IPump getPumpA() {
        return pumpA;
    }

    public IPump getPumpB() {
        return pumpB;
    }

    public IGate getGate() {
        return gate;
    }

    public IOpticalSignals getSignals() {
        return signals;
    }

    public IHumiditySensor getSensor() {
        return steuerung;
    }

    public IHumidifier getHumidifier() {
        return humidifier;
    }

    public IManualControl getOperatorPanel() {
        return operatorPanel;
    }

    public void setErrorFlag(boolean flag) {
        errorFlag = flag;
    }
    
    public boolean getErrorFlag() {
    	return errorFlag;
    }
	
	
}
