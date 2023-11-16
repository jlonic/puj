import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.Test;
import static org.junit.Assert.*;

@SuppressWarnings("ALL")
public class TestPublisher {
    MqttClient publisher;
    @Test
    public void testConnection(){

        try
        {
            this.publisher=new MqttClient("tcp://localhost:1883", "topic");

            this.publisher.connect();
            assertTrue(this.publisher.isConnected());

            this.publisher.disconnect();
            assertFalse(this.publisher.isConnected());
        }
        catch (MqttException me)
        {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }
}
