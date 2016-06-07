package fsm2;

public class HumidityLow extends State {
    
    @Override
    public void doAction(Steuerung sensor){
        
    	sensor.getSignals().switchLampAOn();
    	sensor.getHumidifier().sendSprayOn();
    	
    	double value = sensor.getSensor().getHumidity();
    	
    	while (value >= sensor.getLowerBound())
    		value += 5;
    	
    	sensor.getSignals().switchLampAOff();
    	sensor.getHumidifier().sendSprayOff();
    	
    	sensor.changeState("Ueberwache");
    	
    }
}
