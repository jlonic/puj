package vj3;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.util.Random;
@SuppressWarnings("SpellCheckingInspection")
@Getter
public class Sensor {
    final String data;
    private final String dataType;
    private final Integer factor;
    private final Double lowerLimit;
    private final Double upperLimit;
    private final String unitOfMeasurement;
    @JsonIgnore Double currentValue;
    private final String topic;

    @JsonCreator
    public Sensor (@JsonProperty("data")String data,
                   @JsonProperty("dataType")String dataType,
                   @JsonProperty("factor")Integer factor,
                   @JsonProperty("lowerLimit")Double lowerLimit,
                   @JsonProperty("upperLimit")Double upperLimit,
                   @JsonProperty("unitOfMeasurement")String unitOfMeasurement,
                   @JsonProperty("topic")String topic)
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
}