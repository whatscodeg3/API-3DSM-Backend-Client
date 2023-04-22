package com.br.WhatsCodeClientMicroservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class Client extends RepresentationModel<Client> {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column
    private String fullName;
    @Column
    @CPF(message="cpf inválido")
    private String cpf;

    @Column
    @Email(message="E-mail inválido")
    private String email;

    @Column
    private String telephone;

    @Column
    private LocalDate birthDate;

    @Column
    private Date dateRegister;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

}
