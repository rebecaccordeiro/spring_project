package br.edu.unichristus.projaura.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NgoDTO {
	
	private Long id;
	
	private String name;
	private String email;
	private String cnpj;
	private String city;
	private String password;
}