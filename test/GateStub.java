package test;

import boundaryclasses.IGate;
import boundaryclasses.IOpticalSignals;

public class GateStub implements IGate {
	boolean gateClosed = false;

	@Override
	public void sendCloseGate() {
		// TODO Auto-generated method stub
		System.out.println("Gate closed");
		gateClosed = true;
	}

	@Override
	public void sendOpenGate() {
		// TODO Auto-generated method stub
		System.out.println("Gate opened");
		gateClosed = false;
	}

	@Override
	public boolean receivedGateClosed() {
		// TODO Auto-generated method stub
		return gateClosed;
	}

	@Override
	public boolean receivedGateOpen() {
		// TODO Auto-generated method stub
		return !gateClosed;
	}

}
