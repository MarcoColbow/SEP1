package test;

import java.util.Random;

import boundaryclasses.IGate;
import boundaryclasses.IPump;

public class PumpStub implements IPump {
	boolean pumpactivated = false;

	@Override
	public void sendActivate() {
		System.out.println("Try Pump activate");
		
		new Thread() {
		    public void run() {
		        waitPositive();
		    }
		    
		}.start();


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
	
	
	private void waitPositive()
	{
		try { Thread.sleep((long)((Math.random()*6000)+1000)); } 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		pumpactivated = true;
	}

}
