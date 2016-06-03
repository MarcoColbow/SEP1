package test;

import boundaryclasses.IHumidifier;

public class HumidifierStub implements IHumidifier {

	@Override
	public void sendSprayOn() {
		// TODO Auto-generated method stub
		System.out.println("Spray on");
		

	}

	@Override
	public void sendSprayOff() {
		// TODO Auto-generated method stub
		System.out.println("Spray off");
		
	}

}
