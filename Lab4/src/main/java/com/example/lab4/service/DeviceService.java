package com.example.lab4.service;

import com.example.lab4.model.Client;
import com.example.lab4.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DeviceService {
    private final List<Device> deviceList;
    @Autowired
    private ClientService clientService;


    public DeviceService(){
        this.deviceList=new ArrayList<>();
    }
    public void addDevice(Device device){
        Random random = new Random();
        device.setId(deviceList.size());

        for(int i=0;i<5;i++){
            device.getConsumption().add(random.nextDouble()*100);
        }
        this.deviceList.add(device);

        Client client = clientService.getClient(deviceList.size());
        client.setDevice(device);
    }

    public List<Device> getAll(){
        return deviceList;
    }
}
