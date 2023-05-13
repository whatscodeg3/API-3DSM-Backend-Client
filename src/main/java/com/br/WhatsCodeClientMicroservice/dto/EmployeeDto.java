package com.br.WhatsCodeClientMicroservice.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeDto {
	
	private String name;
	
	private String email;
	
	private String cpf;
	
	private String role;
	
	private String password;

	private String createdBy;

	private Date createdAt;

	private String updatedBy;

	private Date updatedAt;

}