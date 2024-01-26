package com.example.lab5.controller;

import com.example.lab5.model.Client;
import com.example.lab5.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService=clientService;
    }
    @GetMapping("/{id}")
    public Client getClient(@PathVariable int id){return clientService.getClient(id);}
    @GetMapping("/all")
    public List<Client> getAll(){return clientService.getAll();}
}
