import org.junit.Test;
import vj3.Sensor;
import vj3.WaterFlowMeter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
@SuppressWarnings("ALL")

public class TestWaterFlowMeter {
    @Test
    public void testDeserialization(){
        WaterFlowMeter wfm = new WaterFlowMeter();
        String path = "C:\\Users\\Josip\\Desktop\\java-lab-jlonic\\Lab3\\tests\\test.json";
        wfm.deserialization(path);

        assertEquals("broker", wfm.getBroker());
        assertEquals("clientid", wfm.getClientId());

        Sensor sensor = new Sensor("testSensor", "int16", 1, -10.0,10.0,"unit", "topic");
        String expected = sensor.dataToString();
        String wfmSensor = wfm.getLista().getFirst().dataToString();
        assertEquals(expected, wfmSensor);
    }

    @Test
    public void testSerialization(){
        Sensor sensor = new Sensor("testSensor", "int16", 1, -10.0,10.0,"unit", "topic");
        List<Sensor> lista=new ArrayList<>();
        lista.add(sensor);
        WaterFlowMeter wfm = new WaterFlowMeter();
        wfm.setLista(lista);
        wfm.setBroker("broker");
        wfm.setClientId("clientid");

        String path = "C:\\Users\\Josip\\Desktop\\java-lab-jlonic\\Lab3\\tests\\test.json";
        wfm.serialization(path);

        String expected = "{" + System.lineSeparator()
                + "  \"broker\" : \"broker\"," + System.lineSeparator()
                + "  \"clientId\" : \"clientid\"," + System.lineSeparator()
                + "  \"lista\" : [ {" + System.lineSeparator()
                + "    \"data\" : \"testSensor\"," + System.lineSeparator()
                + "    \"dataType\" : \"int16\"," + System.lineSeparator()
                + "    \"factor\" : 1," + System.lineSeparator()
                + "    \"lowerLimit\" : -10.0," + System.lineSeparator()
                + "    \"upperLimit\" : 10.0," + System.lineSeparator()
                + "    \"unitOfMeasurement\" : \"unit\"," + System.lineSeparator()
                + "    \"topic\" : \"topic\"" + System.lineSeparator()
                + "  } ]" + System.lineSeparator()
                + "}";
        try{
            String output = Files.readString(Path.of(path));
            assertEquals(expected, output);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
