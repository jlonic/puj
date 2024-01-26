package com.example.lab5.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

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
