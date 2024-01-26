package com.example.lab6_2.controller;

import com.example.lab6_2.model.Client;
import com.example.lab6_2.model.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;


@Controller
public class WebApp {
    private final String apiUrl = "http://localhost:8080";

    @GetMapping("/devices")
    public String showPage(Model model){
        RestTemplate restTemplate = new RestTemplate();
        Client[] clients = restTemplate.getForObject(apiUrl + "/clients/all", Client[].class );

        model.addAttribute("clients", clients);

        return "clients";
    }

    @GetMapping("/addDevice")
    public String addDeviceForm(Model model){
        model.addAttribute("device", new Device());
        return "addDevice";
    }

    @PostMapping("/addDevice")
    public String addDevice(@ModelAttribute Device device){
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.postForObject(apiUrl + "/devices/addDevice", device, Device.class);
        return "redirect:/devices";
    }
}