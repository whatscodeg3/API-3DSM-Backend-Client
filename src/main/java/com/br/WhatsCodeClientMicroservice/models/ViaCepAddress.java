package com.br.WhatsCodeClientMicroservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ViaCepAddress {


    private String cep;

    private String logradouro;

    private String complemento;
    private String bairro;

    private String localidade;

    private String uf;
}
