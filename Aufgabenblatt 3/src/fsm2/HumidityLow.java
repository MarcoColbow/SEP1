package fsm2;

public class HumidityLow extends State {
    private final int SPRAY_INTENSITY = 5;
    @Override
    public void doAction(Steuerung steuerung){
        
    	steuerung.getSignals().switchLampAOn();
    	steuerung.getHumidifier().sendSprayOn();
    	
    	double value = steuerung.getSensor().getHumidity();
    	
    	while (value < steuerung.getLowerBound())
    		value += SPRAY_INTENSITY;
    	
    	steuerung.getSignals().switchLampAOff();
    	steuerung.getHumidifier().sendSprayOff();
    	
    	steuerung.changeState(States.UEBERWACHE);
    	
    }
}
