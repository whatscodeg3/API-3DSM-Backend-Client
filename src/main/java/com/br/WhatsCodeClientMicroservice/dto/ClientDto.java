package com.br.WhatsCodeClientMicroservice.dto;

import com.br.WhatsCodeClientMicroservice.models.Address;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;


@Data
public class ClientDto {

    private String fullName;

    private String email;

    private String telephone;

    private LocalDate birthDate;

    private Address address;
}
