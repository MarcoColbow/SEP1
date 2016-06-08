package fsm2;

public class TorSchliessen extends State {
    
    @Override
    public void doAction(Steuerung steuerung){
        steuerung.getGate().sendCloseGate();
        steuerung.getSignals().switchLampBOn();
        while(!steuerung.getGate().receivedGateClosed()){}
        steuerung.getSignals().switchLampBOff();
        steuerung.changeState(States.PUMPEN_EINSCHALTEN);
    }
}
