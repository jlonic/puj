package com.example.lab6.controller;

import com.example.lab6.model.Device;
import com.example.lab6.model.Records;
import com.example.lab6.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService){this.deviceService=deviceService;}
    @PostMapping("/addDevice")
    public void addDevice(@RequestBody Device device) {deviceService.addDevice(device);}
    @GetMapping("/all")
    public List<Device> getAll(){return deviceService.getAll();}
    @GetMapping("/{id}")
    public Device getDeviceById(@PathVariable int id) { return deviceService.getDeviceById(id); }
    @PostMapping("/{id}")
    public void updateDevice(@PathVariable int id, @RequestBody Device device) { deviceService.updateDevice(id, device); }
    @DeleteMapping("/{id}")
    public void deleteDevice(@PathVariable int id) {deviceService.deleteDevice(id); }
    @GetMapping("/{id}/total-consumption/{year}")
    public Double totalConsumption(@PathVariable int id, @PathVariable int year){ return deviceService.totalConsumption(id, year);}
    @GetMapping("/{id}/consumption-per-month/{year}")
    public List<Records> consumptionPerMonth(@PathVariable int id, @PathVariable int year){ return deviceService.consumptionPerMonth(id, year);}
    @PostMapping("/{id}/add-record")
    public void addRecord(@PathVariable int id, @RequestBody Records record){ deviceService.addRecord(id, record);}
    @GetMapping("/{id}/month-consumption/{year}/{month}")
    public Double getMonthConsumption(@PathVariable int id, @PathVariable int year, @PathVariable int month){return deviceService.getMonthConsumption(id, year, month);}
    @GetMapping
    public Page<Device> getPaginatedDevices(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "1") int size,
                                            @RequestParam(defaultValue = "id") String sortField){
        Sort sort = Sort.by(sortField);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return deviceService.getPaginatedDevices(pageRequest);

    }
}
