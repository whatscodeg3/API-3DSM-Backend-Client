package com.br.WhatsCodeClientMicroservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Address {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String cep;

    @Column
    private String publicPlace;
    
    @Column
    private Number number;

    @Column
    private String neighborhood;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String complement;

}
