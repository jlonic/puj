package com.example.lab6.model;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;

@Setter @Getter
public class Records {
    private Date date;
    private Double consumption;
    @JsonIgnore private Device device;
}