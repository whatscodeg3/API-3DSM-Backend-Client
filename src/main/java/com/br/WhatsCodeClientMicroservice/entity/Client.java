package com.br.WhatsCodeClientMicroservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column
    private String fullName;
    @Column
    private String cpf;

    @Column
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
