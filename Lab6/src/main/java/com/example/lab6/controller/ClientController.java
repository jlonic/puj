package com.example.lab6.controller;

import com.example.lab6.model.Client;
import com.example.lab6.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


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
    @GetMapping
    public Page<Client> getPaginatedClients(@RequestParam (defaultValue = "0") int page,
                                            @RequestParam (defaultValue = "1") int size,
                                            @RequestParam (defaultValue = "id") String sortField){
        Sort sort = Sort.by(sortField);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return clientService.getPaginatedClients(pageRequest);
    }
}
