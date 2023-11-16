package vj2;

import java.util.Random;
@SuppressWarnings("SpellCheckingInspection")
public class Sensor {
    private final String data;
    private final String dataType;
    private final Integer factor;
    private final Double lowerLimit;
    private final Double upperLimit;
    private final String unitOfMeasurement;
    private Double currentValue;
    private final String topic;

    public Sensor (String data,  String dataType, Integer factor, Double lowerLimit, Double upperLimit, String unitOfMeasurement, String topic)
    {
        this.data=data;
        this.dataType=dataType;
        this.factor=factor;
        this.lowerLimit=lowerLimit;
        this.upperLimit=upperLimit;
        this.unitOfMeasurement=unitOfMeasurement;
        this.topic=topic;
    }

    public void Random()
    {
        Random num=new Random();
        this.currentValue=this.lowerLimit+(this.upperLimit-this.lowerLimit)*num.nextDouble();
    }

    public String dataToString()
    {
        return this.data + "\n " +
                "   Tip podatka: " + this.dataType +
                " \n    Faktor: " + this.factor +
                " \n    Raspon: " + this.lowerLimit +
                " do " + this.upperLimit +
                " \n    Jedinica: " + this.unitOfMeasurement +
                " \n    Trenutna vrijednost: " + this.currentValue +"\n";
    }

    public String getTopic(){return this.topic;}
    public Double getCurrentValue(){return this.currentValue;}
}