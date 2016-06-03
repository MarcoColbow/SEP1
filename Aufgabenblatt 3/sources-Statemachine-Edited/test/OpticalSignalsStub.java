package test;

import boundaryclasses.IOpticalSignals;

public class OpticalSignalsStub implements IOpticalSignals {

	@Override
	public void switchLampAOn() {
		// TODO Auto-generated method stub
		System.out.println("Lampe A on");

	}

	@Override
	public void switchLampAOff() {
		// TODO Auto-generated method stub
		System.out.println("Lampe A off");
	}

	@Override
	public void switchLampBOn() {
		// TODO Auto-generated method stub
		System.out.println("Lampe B on");
	}

	@Override
	public void switchLampBOff() {
		// TODO Auto-generated method stub
		System.out.println("Lampe B off");
	}

}
