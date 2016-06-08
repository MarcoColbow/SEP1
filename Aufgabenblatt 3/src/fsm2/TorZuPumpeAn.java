package fsm2;

public class TorZuPumpeAn extends State {
    private final int PUMP_INTENSITY = 5;
    @Override
    public void doAction(Steuerung steuerung){
        double aktuellHumid = steuerung.getSensor().getHumidity();
        while (aktuellHumid > steuerung.getUpperBound()) {
            aktuellHumid = aktuellHumid - PUMP_INTENSITY;
        }
        steuerung.changeState(States.TOR_OEFFNEN_PUMPEN_ABSCHALTEN);
    }
}
