package com.example.lab4.model;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private Address address;
    private Device device;


    public Client(int id, String firstName, String lastName, Address address, Device device){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        setAddress(address);
        setDevice(device);
    }
    public Client(){}
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