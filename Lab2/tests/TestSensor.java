import org.junit.Test;
import static org.junit.Assert.*;
import vj2.Sensor;
@SuppressWarnings("SpellCheckingInspection")

public class TestSensor {
    @Test
    public void testRandom(){
        Sensor sensor = new Sensor("testSensor", "int16", 1, -10.0,10.0,"unit", "topic");
        sensor.Random();
        double value=sensor.getCurrentValue();
        assertEquals(value, 0, 10.0); //provjera da je random value izmedu granica
    }

    @Test
    public void testPrint(){
        Sensor sensor = new Sensor("testSensor", "int16", 1, -10.0,10.0,"unit", "topic");
        sensor.Random();

        String expected="testSensor\n" +
                "    Tip podatka: int16 \n" +
                "    Faktor: 1 \n" +
                "    Raspon: -10.0 do 10.0 \n" +
                "    Jedinica: unit \n" +
                "    Trenutna vrijednost: " + sensor.getCurrentValue() +"\n";

        String output=sensor.dataToString();

        assertEquals(expected, output);
    }
}
