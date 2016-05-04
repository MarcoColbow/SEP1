package test;

import boundaryclasses.IGate;

public class GateStub implements IGate {

	@Override
	public void sendCloseGate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendOpenGate() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean receivedGateClosed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean receivedGateOpen() {
		// TODO Auto-generated method stub
		return false;
	}

}
