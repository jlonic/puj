package vj3;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Getter;
import lombok.Setter;
import java.io.File;
import java.io.IOException;
import java.util.List;
@SuppressWarnings("ALL")
@Getter @Setter
public class WaterFlowMeter {
    String broker;
    String clientId;
    List<Sensor> lista;

    @JsonCreator
    public WaterFlowMeter(@JsonProperty("broker")String broker,
                          @JsonProperty("clientId")String clientId,
                          @JsonProperty("lista")List<Sensor> lista){
        this.lista=lista;
        this.clientId=clientId;
        this.broker=broker;
    }
    public WaterFlowMeter (){}

    public void publishAllData(){
        Publisher publisher = new Publisher(broker, clientId);

        for (Sensor message : lista){
            message.Random();
            publisher.publish(message);
        }
    }
    public void deserialization(String path){
        try{
            WaterFlowMeter wfm = new ObjectMapper().readValue(new File(path), WaterFlowMeter.class);
            this.broker=wfm.getBroker();
            this.clientId=wfm.getClientId();
            this.lista=wfm.getLista();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void serialization(String path){

        ObjectWriter mapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try{
            mapper.writeValue(new File(path), this);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
