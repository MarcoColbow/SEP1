package test;

import boundaryclasses.IGate;

public class GateStub implements IGate {
	boolean gateClosed = false;

	@Override
	public void sendCloseGate() {
		System.out.println("Gate closed");
		gateClosed = true;
	}

	@Override
	public void sendOpenGate() {
		System.out.println("Gate opened");
		gateClosed = false;
	}

	@Override
	public boolean receivedGateClosed() {
		return gateClosed;
	}

	@Override
	public boolean receivedGateOpen() {
		return !gateClosed;
	}

}
