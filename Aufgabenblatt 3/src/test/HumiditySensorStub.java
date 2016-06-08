package test;

import boundaryclasses.IHumiditySensor;

public class HumiditySensorStub implements IHumiditySensor {
	static double _forceHumidity = 0;

	@Override
	public double getHumidity() {
		if (_forceHumidity == 0)
			return Math.random() * 100;
		else
			return _forceHumidity;
	}

	public static void forceHumidity(double forceValue)
	{
		_forceHumidity = forceValue;
	}

}
