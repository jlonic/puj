package com.example.lab6_2.model;

import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Setter @Getter
public class Records {
    private Date date;
    private Double consumption;
    @JsonIgnore private Device device;

    public void setDate(String dateString) { //fix za "Failed to convert property value of type java.lang.String to required type java.util.Date"
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // Handle the parsing exception as needed
            e.printStackTrace();
        }
    }

}