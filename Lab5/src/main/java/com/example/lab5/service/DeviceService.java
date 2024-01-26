package com.example.lab5.service;

import com.example.lab5.model.Client;
import com.example.lab5.model.Device;
import com.example.lab5.model.Records;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class DeviceService {
    private final List<Device> deviceList;
    @Autowired
    private ClientService clientService;


    public DeviceService() {
        this.deviceList = new ArrayList<>();
    }

    public void addDevice(Device device) {
        device.setId(deviceList.size());
        this.deviceList.add(device);

        Client client = clientService.getClient(deviceList.size());
        client.setDevice(device);
    }

    public List<Device> getAll() {
        return deviceList;
    }

    public Device getDeviceById(int id) {
        for (Device device : deviceList) {
            if (device.getId() == id)
                return device;
        }
        return null;
    }

    public void updateDevice(int id, Device device) {
        for (Device d : deviceList) {
            if (d.getId() == id) {
                d.setRecords(device.getRecords());
                break;
            }
        }
    }

    public void deleteDevice(int id) {
        deviceList.removeIf(device -> device.getId() == id);
    }

    public Double totalConsumption(int id, int year) {
        double total = 0;

        for (Device device : deviceList) {
            if (device.getId() == id) {
                for (int i = 0; i < device.getRecords().size(); i++) {
                    Calendar recordCalendar = Calendar.getInstance();
                    recordCalendar.setTime(device.getRecords().get(i).getDate());
                    int recordYear=recordCalendar.get(Calendar.YEAR);

                    if(recordYear==year)
                        total = total + device.getRecords().get(i).getConsumption();
                }
            }
        }
        return total;
    }

    public List<Records> consumptionPerMonth(int id, int year) {
        List<Records> consumption = new ArrayList<>();
        for (Device device : deviceList) {
            if (device.getId() == id) {
                for (int i = 0; i < device.getRecords().size(); i++) {
                    Calendar recordCalendar = Calendar.getInstance();
                    recordCalendar.setTime(device.getRecords().get(i).getDate());
                    int recordYear=recordCalendar.get(Calendar.YEAR);

                    if (recordYear==year)
                        consumption.add(device.getRecords().get(i));
                }
            }
        }
        return consumption;
    }

    public void addRecord(int id, Records record) {
        for (Device device : deviceList) {
            if (device.getId() == id) {
                record.setDevice(device);
                device.getRecords().add(record);
            }
        }
    }

    public Double getMonthConsumption(int id, int year, int month) {
        Double consumption = 0.0;
        for (Device device : deviceList) {
            if (device.getId() == id) {
                for (int i = 0; i < device.getRecords().size(); i++) {
                    Calendar recordCalendar = Calendar.getInstance();
                    recordCalendar.setTime(device.getRecords().get(i).getDate());
                    int recordYear=recordCalendar.get(Calendar.YEAR);
                    int recordMonth=recordCalendar.get(Calendar.MONTH)+1; //Calendar.MONTH je 0-11

                    if ((recordYear==year) && (recordMonth==month)) {
                        consumption = device.getRecords().get(i).getConsumption();
                        break;
                    }
                }
            }
        }
        return consumption;
    }
}