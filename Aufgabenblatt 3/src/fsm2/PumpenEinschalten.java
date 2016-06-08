package fsm2;

import boundaryclasses.ITimer;
import test.TimerStub;

public class PumpenEinschalten extends State {
private ITimer timer = new TimerStub();


    @Override
    public void doAction(Steuerung steuerung){
        steuerung.getPumpA().sendActivate();
        steuerung.getPumpB().sendActivate();
        
        timer.startTime(5);
        
        while (!timer.isTimerExpired())
        {
	        
        }
        
        if (steuerung.getPumpA().receivedActivated() && steuerung.getPumpB().receivedActivated())
        {
            steuerung.changeState(States.TOR_ZU_PUMPEN_AN);
        }
        else
        {
        	steuerung.changeState(States.FEHLER_FESTGESTELLT);
        }
    }
}
