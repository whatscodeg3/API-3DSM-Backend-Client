package com.br.WhatsCodeClientMicroservice.dto;

import lombok.Data;

@Data
public class EmployeeDto {
	
	private String name;
	
	private String email;
	
	private String cpf;
	
	private String role;
	
	private String password;

}