package fsm2;

import boundaryclasses.IHumiditySensor;

public class Ueberwache extends State {
    private IHumiditySensor humiditySensor;
    
    public Ueberwache() {
        this.humiditySensor = humiditySensor;
    }
    
    @Override
    public void doAction(Steuerung sensor){
        if(humiditySensor.getHumidity() > sensor.getUpperBound()){
            sensor.changeState("TorSchliessen");
        }
        else if (humiditySensor.getHumidity() < sensor.getLowerBound()){
            sensor.changeState("HumidityLow");
        }
    }
}
