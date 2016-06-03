package fsm2;

public class TorZuPumpeAn extends State {
    
    @Override
    public void doAction(Sensor sensor){
        double aktuellHumid = sensor.getSensor().getHumidity();
        while (aktuellHumid > sensor.getUpperBound()) {
            aktuellHumid = aktuellHumid - 5;
        }
        sensor.changeState("TorOeffnenPumpenAbschalten");
    }
}