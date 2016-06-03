package fsm2;

import boundaryclasses.IHumiditySensor;

public class Ueberwache extends State {
    private IHumiditySensor humiditySensor;
    
    public Ueberwache() {
        this.humiditySensor = humiditySensor;
    }
    
    @Override
    public void doAction(Sensor sensor){
        if(humiditySensor.getHumidity() > sensor.getUpperBound()){
            sensor.changeState("TorSchlieﬂen");
        }
        else if (humiditySensor.getHumidity() < sensor.getLowerBound()){
            sensor.changeState("HumidityLow");
        }
    }
}
