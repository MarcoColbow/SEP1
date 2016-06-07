package test;

import boundaryclasses.IPump;

public class PumpStub implements IPump {
	private boolean _pumpactivated = false;
	private boolean _neverGoOnline = false;



	public void setNeverGoOnline(boolean neverGoOnline)
	{
		_neverGoOnline = neverGoOnline;
	}

	@Override
	public void sendActivate() {
		System.out.println("Try Pump activate");
		
		if (!_neverGoOnline)
		{
			new Thread() {
			    public void run() {
			        waitPositive();
			    }
			    
			}.start();
		}


	}

	@Override
	public void sendDeactivate() {
		System.out.println("Pump deactivate");
		_pumpactivated = false;
	}

	@Override
	public boolean receivedActivated() {
		return _pumpactivated;
	}	
	
	
	private void waitPositive()
	{
		try { Thread.sleep((long)((Math.random()*4000)+1000)); } 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		_pumpactivated = true;
	}

}
