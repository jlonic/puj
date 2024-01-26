package com.example.lab6.service;

import com.example.lab6.exception.CustomException;
import com.example.lab6.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
        throw new CustomException(HttpStatus.BAD_REQUEST, "Client with ID="+id+" does not exist");
    }
    public List<Client> getAll(){return clientList;}

    public Page<Client> getPaginatedClients(PageRequest pageRequest){
        int pageSize = pageRequest.getPageSize();
        int currentPage = pageRequest.getPageNumber();
        int start = currentPage * pageSize;
        List<Client> paginatedClients;

        if(clientList.size() < start)
            paginatedClients = Collections.emptyList();
        else {
            int toIndex = Math.min(start + pageSize, clientList.size());
            paginatedClients = clientList.subList(start, toIndex);
        }
        if (paginatedClients.isEmpty())
            throw new CustomException(HttpStatus.BAD_REQUEST, "No clients found on this page");
        return new PageImpl<>(paginatedClients, pageRequest, clientList.size());
    }
}
