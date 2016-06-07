package test;

import boundaryclasses.IManualControl;

public class ManualControlStub implements IManualControl {
	private boolean _buttonPressed = false;
	
	@Override
	public boolean receivedAcknowledgement() 
	{
		if(_buttonPressed)
		{
			_buttonPressed = false;
			return true;
		}
		return false;
	}

	@Override
	public void pressButton() {
		_buttonPressed = true;
	}

}
