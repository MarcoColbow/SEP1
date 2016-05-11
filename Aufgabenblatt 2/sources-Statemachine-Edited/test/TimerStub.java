package test;

import boundaryclasses.ITimer;

public class TimerStub implements ITimer {

	private long _timeStart;
	private double _secs;
	
	@Override
	public void startTime(double seconds) {
		_timeStart = System.currentTimeMillis();
		_secs = seconds;
	}

	@Override
	public boolean isTimerExpired() {
		if (_timeStart != 0 || _secs != 0)
		{
			if (System.currentTimeMillis() - _timeStart >= _secs)
				return true;
			else
				return true;
		}
		else
			return false;
	}

}
