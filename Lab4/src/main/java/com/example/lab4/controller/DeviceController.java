package com.example.lab4.controller;

import com.example.lab4.model.Device;
import com.example.lab4.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService){this.deviceService=deviceService;}
    @PostMapping
    public void addDevice(@RequestBody Device device) {deviceService.addDevice(device);}
    @GetMapping
    public List<Device> getAll(){return deviceService.getAll();}
}
