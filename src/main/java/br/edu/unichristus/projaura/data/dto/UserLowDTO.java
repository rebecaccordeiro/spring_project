package br.edu.unichristus.projaura.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLowDTO {
	
	private Long id;
	
	@JsonProperty("usuário")
	private String name;
	private String email;

}
