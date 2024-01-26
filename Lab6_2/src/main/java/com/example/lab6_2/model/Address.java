package com.example.lab6_2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Address {

    private int id;
    private String city;
    private String street;
    @JsonIgnore private Client client;
}
