package fsm2;

public class Ueberwache extends State {
	
    @Override
    public void doAction(Steuerung steuerung){
        if(steuerung.getSensor().getHumidity() > steuerung.getUpperBound()){
            steuerung.changeState(States.TOR_SCHLIESSEN);
        }
        else if (steuerung.getSensor().getHumidity() < steuerung.getLowerBound()){
            steuerung.changeState(States.HUMIDITY_LOW);
        }
    }
}
