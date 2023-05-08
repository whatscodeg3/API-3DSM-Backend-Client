package com.br.WhatsCodeClientMicroservice.models;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Employee {
	
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    
    @Column
	private String name;
    
    @Column
	private String email;
    
    @Column
	private String cpf;
    
    @Column
	private String role;
    
    @Column
	private String password;
}