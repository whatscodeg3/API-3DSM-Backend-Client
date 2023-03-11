package com.br.WhatsCodeClientMicroservice.controller;

import com.br.WhatsCodeClientMicroservice.entity.Client;
import com.br.WhatsCodeClientMicroservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    private ClientRepository repository;

    @PostMapping("/create")
    public void cadastrar(@RequestBody Client newClient) {
        newClient.setDateRegister(new Date());
        repository.save(newClient);
    }

}
