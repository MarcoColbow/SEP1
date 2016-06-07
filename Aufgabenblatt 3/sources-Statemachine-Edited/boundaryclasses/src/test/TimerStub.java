package test;

import boundaryclasses.ITimer;

public class TimerStub implements ITimer {

	private long _timeStart;
	private double _secs;
	
	@Override
	public void startTime(double seconds) {
		_timeStart = System.currentTimeMillis();
		_secs = seconds * 1000;
	}

	@Override
	public boolean isTimerExpired() {
		return (System.currentTimeMillis() - _timeStart >= _secs) ? true : false;
		
	}

}
