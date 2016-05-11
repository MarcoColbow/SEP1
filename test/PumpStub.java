package test;

import boundaryclasses.IGate;
import boundaryclasses.IPump;

public class PumpStub implements IPump {
	boolean pumpactivated = false;

	@Override
	public void sendActivate() {
		// TODO Auto-generated method stub {
		System.out.println("Pump activate");
		pumpactivated = true;
	}

	@Override
	public void sendDeactivate() {
		// TODO Auto-generated method stub
		System.out.println("Pump deactivate");
		pumpactivated = false;
	}

	@Override
	public boolean receivedActivated() {
		// TODO Auto-generated method stub
		return pumpactivated;
	}

}
