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
public class JobLowDTO {
	
	private Long id;
	
	@JsonProperty("trabalho voluntário")
	private String title;
	private String ngoName;

}
