package test;

import boundaryclasses.IPump;

public class PumpStub implements IPump {
	boolean _pumpactivated = false;
	boolean _neverGoOnline = false;



	public void neverGoOnline()
	{
		_neverGoOnline = true;
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
		// TODO Auto-generated method stub
		System.out.println("Pump deactivate");
		_pumpactivated = false;
	}

	@Override
	public boolean receivedActivated() {
		// TODO Auto-generated method stub
		return _pumpactivated;
	}	
	
	
	private void waitPositive()
	{
		try { Thread.sleep((long)((Math.random()*6000)+1000)); } 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		_pumpactivated = true;
	}

}
