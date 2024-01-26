package com.example.lab6_2.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private Address address;
    private Device device;
}