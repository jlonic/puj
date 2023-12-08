package com.example.lab4.controller;

import com.example.lab4.model.Client;
import com.example.lab4.service.ClientService;
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
    @GetMapping
    public Client getClient(@PathVariable int id){return clientService.getClient(id);}
    @GetMapping
    public List<Client> getAll(){return clientService.getAll();}
}
