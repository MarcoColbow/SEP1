package fsm2;

public class TorSchliessen extends State {
    
    @Override
    public void doAction(Steuerung sensor){
        sensor.getGate().sendCloseGate();
        while(!sensor.getGate().receivedGateClosed()){}
        sensor.getSignals().switchLampBOn();
        sensor.changeState("PumpenEinschalten");
    }
}
