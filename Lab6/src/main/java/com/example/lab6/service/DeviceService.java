package com.example.lab6.service;

import com.example.lab6.model.Client;
import com.example.lab6.exception.CustomException;
import com.example.lab6.model.Device;
import com.example.lab6.model.Records;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
        if (device.getId() < 0)
            throw new CustomException(HttpStatus.BAD_REQUEST, "Device ID cannot be negative");

        device.setId(deviceList.size()+1);

        if(device.getId()>clientService.getAll().size()) //test
            throw new CustomException(HttpStatus.BAD_REQUEST, "Cannot add more devices than clients");

        this.deviceList.add(device);

        Client client = clientService.getClient(deviceList.size());
        device.setClient(client);
        client.setDevice(device);
    }

    public List<Device> getAll() {
        if(deviceList.isEmpty())
            throw new CustomException(HttpStatus.BAD_REQUEST, "Device list is empty");

        return deviceList;
    }

    public Device getDeviceById(int id) {
        for (Device device : deviceList) {
            if (device.getId() == id)
                return device;
        }
        throw new CustomException(HttpStatus.BAD_REQUEST, "Device with ID="+id+" does not exist");
    }

    public void updateDevice(int id, Device device) {
        int flag=0;
        for (Device d : deviceList) {
            if (d.getId() == id) {
                d.setRecords(device.getRecords());
                flag=1;
                break;
            }

        }
        if (flag==0)
            throw new CustomException(HttpStatus.BAD_REQUEST, "Device with ID="+id+" does not exist");
    }

    public void deleteDevice(int id) {
        for (Device device : deviceList){
            if (device.getId() == id)
                deviceList.remove(device);
            else throw new CustomException(HttpStatus.BAD_REQUEST, "Device with ID="+id+" does not exist");
        }
        //deviceList.removeIf(device -> device.getId() == id);
    }

    public Double totalConsumption(int id, int year) {
        double total = 0;
        int flag=0;
        for (Device device : deviceList) {
            if (device.getId() == id) {
                flag=1;
                for (int i = 0; i < device.getRecords().size(); i++) {
                    Calendar recordCalendar = Calendar.getInstance();
                    recordCalendar.setTime(device.getRecords().get(i).getDate());
                    int recordYear=recordCalendar.get(Calendar.YEAR);

                    if(recordYear==year)
                        total = total + device.getRecords().get(i).getConsumption();
                }
            }
        }
        if (flag==0)
            throw new CustomException(HttpStatus.BAD_REQUEST, "Device with ID="+id+" does not exist");

        return total;
    }

    public List<Records> consumptionPerMonth(int id, int year) {
        List<Records> consumption = new ArrayList<>();
        int flag=0;
        for (Device device : deviceList) {
            if (device.getId() == id) {
                flag=1;
                for (int i = 0; i < device.getRecords().size(); i++) {
                    Calendar recordCalendar = Calendar.getInstance();
                    recordCalendar.setTime(device.getRecords().get(i).getDate());
                    int recordYear=recordCalendar.get(Calendar.YEAR);

                    if (recordYear==year)
                        consumption.add(device.getRecords().get(i));
                }
            }
        }
        if (flag==0)
            throw new CustomException(HttpStatus.BAD_REQUEST, "Device with ID="+id+" does not exist");

        return consumption;
    }

    public void addRecord(int id, Records record) {
        int flag=0;
        for (Device device : deviceList) {
            if (device.getId() == id) {
                flag=1;
                record.setDevice(device);
                device.getRecords().add(record);
            }
        }
        if(flag==0)
            throw new CustomException(HttpStatus.BAD_REQUEST, "Device with ID="+id+" does not exist");
    }

    public Double getMonthConsumption(int id, int year, int month) {
        Double consumption = 0.0;
        int flag=0;
        for (Device device : deviceList) {
            if (device.getId() == id) {
                flag=1;
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
        if(flag==0)
            throw new CustomException(HttpStatus.BAD_REQUEST, "Device with ID="+id+" does not exist");

        return consumption;
    }

    public Page<Device> getPaginatedDevices(PageRequest pageRequest){
        int pageSize = pageRequest.getPageSize();
        int currentPage = pageRequest.getPageNumber();
        int start = currentPage * pageSize;
        List<Device> paginatedDevices;

        if(deviceList.size() < start)
            paginatedDevices = Collections.emptyList();
        else {
            int toIndex = Math.min(start + pageSize, deviceList.size());
            paginatedDevices = deviceList.subList(start, toIndex);
        }

        if(paginatedDevices.isEmpty())
            throw new CustomException(HttpStatus.BAD_REQUEST, "No devices found on this page");

        return new PageImpl<>(paginatedDevices, pageRequest, deviceList.size());
    }
}