package com.example.lab6;

import com.example.lab6.model.Client;
import com.example.lab6.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class MyRunner implements CommandLineRunner {
    private final ClientService clientService;
    private final ObjectMapper objectMapper;

    @Autowired
    public MyRunner(ClientService clientService, ObjectMapper objectMapper){
        this.clientService=clientService;
        this.objectMapper=objectMapper;
    }

    @Override
    public void run(String[] args) {
        File file = new File ("C:\\Users\\Josip\\Desktop\\java-lab-jlonic\\Lab4\\src\\main\\resources\\clients.json");
        try{

            Client[] clients = objectMapper.readValue(file, Client[].class);
            for (Client client : clients){
                clientService.getAll().add(client);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
