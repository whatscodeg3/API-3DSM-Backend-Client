package com.br.WhatsCodeClientMicroservice.controller;

import com.br.WhatsCodeClientMicroservice.mapper.AddresMapper;
import com.br.WhatsCodeClientMicroservice.models.Address;
import com.br.WhatsCodeClientMicroservice.models.Client;
import com.br.WhatsCodeClientMicroservice.models.ViaCepAddress;
import com.br.WhatsCodeClientMicroservice.repository.ClientRepository;
import com.google.gson.Gson;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    private ClientRepository repository;


    @Autowired
    private AddresMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody @Valid Client newClient) throws Exception {
        // o objeto Client será passado do front já com o endereço trazido pelo viaCep
        newClient.setDateRegister(new Date());

        String complementAux = newClient.getAddress().getComplement(); //Será retirado quando o front for criado

        newClient.setAddress(requestViaCep(newClient)); //Será retirado quando o front for criado

        newClient.getAddress().setComplement(complementAux); //Será retirado quando o front for criado


        repository.save(newClient);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/query")
    public List<Client> getClients(){
        return repository.findAll();
    }

    public Address requestViaCep(Client client) throws Exception{
        // Esse método será chamado no front quando o usuário passar o cep e será retornado o endereço
        //O complemento retorna null para que o usuário preencha.
        URL url = new URL("https://viacep.com.br/ws/"+client.getAddress().getCep()+"/json/");
        URLConnection connection = url.openConnection();
        InputStream is = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(is, "UTF-8"));

        String cep = "";
        StringBuilder jsonCep = new StringBuilder();

        while ((cep = bufferedReader.readLine()) != null){
            jsonCep.append(cep);
        }

        ViaCepAddress viaCepAddress = new Gson().fromJson(jsonCep.toString(), ViaCepAddress.class);

        Address addressAux = mapper.mapToViaCep(viaCepAddress);

        return addressAux;

    }


}
