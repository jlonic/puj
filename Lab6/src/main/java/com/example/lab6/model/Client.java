package com.example.lab6.model;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private Address address;
    private Device device;

    public void setAddress(Address address) {
        if (address.getClient() != null && !address.getClient().equals(this)) {
            throw new IllegalArgumentException("Address already in use.");
        }
        this.address = address;
        address.setClient(this);
    }
    public void setDevice(Device device) {
        if (device.getClient() != null && !device.getClient().equals(this)) {
            throw new IllegalArgumentException("Device already in use.");
        }
        this.device = device;
        device.setClient(this);
    }
}