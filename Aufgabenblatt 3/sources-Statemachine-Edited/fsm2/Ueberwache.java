package fsm2;

import boundaryclasses.IHumiditySensor;

public class Ueberwache extends State {

    @Override
    public void doAction(Steuerung sensor){
        if(sensor.getSensor().getHumidity() > sensor.getUpperBound()){
            sensor.changeState("TorSchliessen");
        }
        else if (sensor.getSensor().getHumidity() < sensor.getLowerBound()){
            sensor.changeState("HumidityLow");
        }
    }
}
