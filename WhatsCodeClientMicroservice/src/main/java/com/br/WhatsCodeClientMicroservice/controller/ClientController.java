package com.br.WhatsCodeClientMicroservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @GetMapping("/")
    String testeConexao(){
        return "testando";
    }

}
