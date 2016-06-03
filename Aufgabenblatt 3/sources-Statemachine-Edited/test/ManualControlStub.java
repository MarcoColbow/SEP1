package test;

import boundaryclasses.IManualControl;

public class ManualControlStub implements IManualControl {

	@Override
	public boolean receivedAcknowledgement() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
