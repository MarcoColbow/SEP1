package fsm2;

public class FehlerFestgestellt extends State {
    
    @Override
    public void doAction(Sensor sensor){
        sensor.setErrorFlag(true);
        sensor.changeState("TorOeffnenPumpenAbschalten");
    }
}
