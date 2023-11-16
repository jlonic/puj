import vj3.Sensor;
import vj3.WaterFlowMeter;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class Main {
    public static void main(String[] args){

        String path = "C:\\Users\\Josip\\Desktop\\java-lab-jlonic\\Lab3\\src\\sensors.json";
        WaterFlowMeter wfm = new WaterFlowMeter();
        wfm.deserialization(path);
        wfm.publishAllData();

        //serijalizacija
        Sensor s1=new Sensor("Trenutna temperatura vode", "int16", 10, -3266.8, 3266.8, "C", "temperatura");
        Sensor s2=new Sensor("Trenutni tlak vode", "uint16",1000, 0.0, 65.336, "Bar", "tlak");
        Sensor s3=new Sensor("Potrosnja u zadnjih 1 min, 10 min, 1 sat, 1 dan", "uint16", 0, 0.0, 65336.0, "litra", "potrosnja_dan");
        Sensor s4=new Sensor("Potrosnja u zadnjih 1 tjedan, 1 mjesec, 1 godinu", "test", 10, 0.0, 6533.6, "m3", "potrosnja_godina");
        List<Sensor> lista=new ArrayList<>();
        lista.add(s1);
        lista.add(s2);
        lista.add(s3);
        lista.add(s4);
        String clientId = "123";
        String broker = "tcp://localhost:1883";

        WaterFlowMeter wfm2 = new WaterFlowMeter(broker, clientId, lista);
        wfm2.serialization(path);
    }
}