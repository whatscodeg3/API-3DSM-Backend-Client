package com.br.WhatsCodeClientMicroservice.models;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.validator.constraints.br.CPF;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
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
    @Email(message="E-mail inválido")
	private String email;
    
    @Column
    @CPF(message="cpf inválido")
	private String cpf;
    
    @Column
	private String role;
    
    @Column
	private String password;
}
