package com.example.lab4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter
public class Device {
    private int id;
    private List<Double> consumption;
    @JsonIgnore private Client client;

    public Device(int id, List<Double> consumption){
        this.id=id;
        this.consumption=consumption;
    }

}
