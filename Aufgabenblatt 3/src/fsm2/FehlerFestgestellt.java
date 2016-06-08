package fsm2;

public class FehlerFestgestellt extends State {
    
    @Override
    public void doAction(Steuerung steuerung){
        steuerung.setErrorFlag(true);
        steuerung.changeState(States.TOR_OEFFNEN_PUMPEN_ABSCHALTEN);
    }
}
