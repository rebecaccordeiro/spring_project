package br.edu.unichristus.projaura.data.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String key;

}