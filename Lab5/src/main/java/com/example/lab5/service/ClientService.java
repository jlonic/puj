package com.example.lab5.service;

import com.example.lab5.model.Client;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    private final List<Client> clientList;

    public ClientService(){
        this.clientList = new ArrayList<>();
    }
    public Client getClient(int id) {
        for (Client client : clientList){
            if (id == client.getId()){
                return client;
            }
        }
        return null;
    }
    public List<Client> getAll(){
        return clientList;
    }
}
