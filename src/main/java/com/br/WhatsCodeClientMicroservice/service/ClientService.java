package com.br.WhatsCodeClientMicroservice.service;

import com.br.WhatsCodeClientMicroservice.models.Client;
import com.br.WhatsCodeClientMicroservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;
    public Client getByCpf(String cpf){
        List<Client> clients = repository.findAll();
        Client target = null;
        for(Client client : clients){
            if(client.getCpf().equals(cpf)){
                target = client;
                break;
            }
        }
        return target;
    }

}
