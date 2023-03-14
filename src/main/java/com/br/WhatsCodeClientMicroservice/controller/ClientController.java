package com.br.WhatsCodeClientMicroservice.controller;

import com.br.WhatsCodeClientMicroservice.dto.ClientDto;
import com.br.WhatsCodeClientMicroservice.mapper.AddresMapper;
import com.br.WhatsCodeClientMicroservice.models.Address;
import com.br.WhatsCodeClientMicroservice.models.Client;
import com.br.WhatsCodeClientMicroservice.models.ViaCepAddress;
import com.br.WhatsCodeClientMicroservice.repository.ClientRepository;
import com.google.gson.Gson;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/query/{id}")
    public Client getClientById( @PathVariable("id") long id){
        Optional<Client> client = repository.findById(id);
        return client.get();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable("id") long id, @RequestBody ClientDto clientdto) {
        Optional<Client> clientOptional = repository.findById(id);
        if (!clientOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        var clientModel = new Client();
        BeanUtils.copyProperties(clientdto, clientModel);
        Client getClientOptional = clientOptional.get();
        clientModel.setId(getClientOptional.getId());
        clientModel.setDateRegister(getClientOptional.getDateRegister());
        clientModel.setCpf(getClientOptional.getCpf());
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(clientModel));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClientById( @PathVariable("id") long id){
        repository.deleteById(id);
        return ResponseEntity.ok().build();

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
