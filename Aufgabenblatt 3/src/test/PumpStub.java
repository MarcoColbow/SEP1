package test;

import boundaryclasses.IPump;

public class PumpStub implements IPump {
	private boolean _pumpactivated = false;
	private boolean _neverGoOnline = false;
	private boolean _forceActivation = false;



	public void setNeverGoOnline(boolean neverGoOnline)
	{
		_neverGoOnline = neverGoOnline;
	}

	@Override
	public void sendActivate() {
		System.out.println("Try Pump activate");
		if (!_neverGoOnline)
		{
			if(Math.random() > 0.5 || _forceActivation )
			{
				_pumpactivated = true;
			}
			else
			{
				_pumpactivated = false;
			}
		}
		else
		{
			System.out.println("Pump activation impossible");
		}


	}

	/**
	 * Needed for White-Box testing
	 * @param _forceActivation
	 */
	public void set_forceActivation(boolean _forceActivation) {
		this._forceActivation = _forceActivation;
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
}
