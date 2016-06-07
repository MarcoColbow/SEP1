package fsm2;

import java.util.HashMap;
import java.util.Map;

import boundaryclasses.IGate;
import boundaryclasses.IHumidifier;
import boundaryclasses.IHumiditySensor;
import boundaryclasses.IManualControl;
import boundaryclasses.IOpticalSignals;
import boundaryclasses.IPump;

public class Steuerung {
	private boolean errorFlag;
	private State current;
	private Map<String, State> states;
	private IPump pumpA;
    private IPump pumpB;
    private IGate gate;
    private IOpticalSignals signals;
    private IHumiditySensor sensor;
    private IHumidifier humidifier;
    private IManualControl operatorPanel;
    private final double upperBound;
    private final double lowerBound;
	
	
	public Steuerung(IPump pumpA, IPump pumpB, IGate gate, IOpticalSignals signals, IHumidifier humidifier,
            IHumiditySensor sensor, IManualControl operatorPanel){
		errorFlag = false;
		states = new HashMap<>();
		states.put("Fehler",                      new Fehler());
		states.put("FehlerFestgestellt",          new FehlerFestgestellt());
		states.put("HumidityLow",                 new HumidityLow());
		states.put("PumpenEinschalten",           new PumpenEinschalten());
		states.put("TorOeffnenPumpenAbschalten",  new TorOeffnenPumpenAbschalten());
		states.put("TorSchliessen",                new TorSchliessen());
		states.put("TorZuPumpenAn",               new TorZuPumpeAn());
		states.put("Ueberwache",                  new Ueberwache());
        this.pumpA = pumpA;
        this.pumpB = pumpB;
        this.gate = gate;
        this.signals = signals;
        this.sensor = sensor;
        this.humidifier = humidifier;
        this.operatorPanel = operatorPanel;
        upperBound = 60;
        lowerBound = 20;
        changeState("Ueberwache");
	}
	
	public void changeState(String stateKey){
        System.out.printf("Zustand: %s\n", stateKey);
	    current = states.get(stateKey);
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

    public Map<String, State> getStates() {
        return states;
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
        return sensor;
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
