package fsm2;

public class TorSchliessen extends State {
    
    @Override
    public void doAction(Steuerung sensor){
        sensor.getGate().sendCloseGate();
        sensor.getSignals().switchLampBOn();
        while(!sensor.getGate().receivedGateClosed()){}
        sensor.getSignals().switchLampBOff();
        sensor.changeState("PumpenEinschalten");
    }
}
