package vj2;

import java.util.List;
@SuppressWarnings("SpellCheckingInspection")
public class WaterFlowMeter {

    private final List<Sensor> lista;
    private final String clientId;
    private final String broker;

    public WaterFlowMeter(List<Sensor> lista, String clientId, String broker){
        this.lista=lista;
        this.clientId=clientId;
        this.broker=broker;
    }
    public void publishAllData(){
        Publisher publisher = new Publisher(broker, clientId);

        for (Sensor message : lista){
            message.Random();
            publisher.publish(message);
        }
    }
}
