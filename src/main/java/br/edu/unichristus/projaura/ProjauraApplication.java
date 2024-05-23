package br.edu.unichristus.projaura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
	    info = @Info(
	        title = "Projeto Aura App",
	        version = "1.0",
	        description = "API para o projeto Aura",
	        contact = @Contact(
	        	    name = "Rebeca Cordeiro",
	        	    email = "rebecaccordeiro92@gmail.com",
	        	     url = "https://www.rebequice.com")
	      
	        
	    )
	)

public class ProjauraApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjauraApplication.class, args);
	}

}
