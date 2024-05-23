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
public class JobDTO {
	
	private Long id;
	
	private String title;
	private String description;
	private String category;
	private boolean remote;
	private String ngoName;

}
