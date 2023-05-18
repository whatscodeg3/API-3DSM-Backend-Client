package com.br.WhatsCodeClientMicroservice.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReplacementEmployeeDto {
	
	private String role;

	private String updatedBy;

	private Date updatedAt;

}
