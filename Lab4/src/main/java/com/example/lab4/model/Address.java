package com.example.lab4.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Address {
    private int id;
    private String city;
    private String street;
    @JsonIgnore private Client client;

    public Address(int id, String city, String street){
        this.id=id;
        this.city=city;
        this.street=street;
    }
}
