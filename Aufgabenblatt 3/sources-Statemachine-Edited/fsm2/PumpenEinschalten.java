package fsm2;

import boundaryclasses.ITimer;
import test.TimerStub;

public class PumpenEinschalten extends State {
private ITimer timer = new TimerStub();


    @Override
    public void doAction(Steuerung sensor){
        sensor.getPumpA().sendActivate();
        sensor.getPumpB().sendActivate();
        
        timer.startTime(5);
        
        while (!timer.isTimerExpired())
	        if (sensor.getPumpA().receivedActivated() && sensor.getPumpB().receivedActivated())
	        {
	            sensor.changeState("TorZuPumpeAn");
	            return;
	        }
	        sensor.changeState("FehlerFestgestellt");
    }
}
