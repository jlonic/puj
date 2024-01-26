package com.example.lab6_2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter @Getter
public class Device {
    private int id;
    private List<Records> records = new ArrayList<>();
    @JsonIgnore private Client client;
}
