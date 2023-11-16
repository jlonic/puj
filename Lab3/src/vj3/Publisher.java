package vj3;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

//@SuppressWarnings("SpellCheckingInspection")
@SuppressWarnings("ALL")

public class Publisher {
    private MqttClient mqttClient;

    Publisher(String broker, String clientId)
    {
        try
        {
            mqttClient=new MqttClient(broker, clientId);
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

    void publish(Sensor message)  {
        MqttMessage mqttMessage=new MqttMessage(message.dataToString().getBytes());
        String[] topics = {"temperatura", "tlak", "potrosnja_dan", "potrosnja_godina"};

        try{
            this.mqttClient.connect();
            for (String topic: topics) {
                if (message.getTopic().equals(topic)){
                    this.mqttClient.publish(topic, mqttMessage);
                }
            }
            this.mqttClient.disconnect();
        }
        catch (MqttException me){
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }
}