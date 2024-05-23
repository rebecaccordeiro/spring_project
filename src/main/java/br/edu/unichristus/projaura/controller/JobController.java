package br.edu.unichristus.projaura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unichristus.projaura.data.dto.JobDTO;
import br.edu.unichristus.projaura.data.dto.JobLowDTO;
import br.edu.unichristus.projaura.data.model.Job;
import br.edu.unichristus.projaura.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/job")
public class JobController {
	
	@Autowired
	private JobService service;
	
	@Operation(summary = "Cadastrar Trabalho Voluntário | role: [ADMIN]",
			tags = "Job", description = "Possibilita "
			+ "cadastrar um trabalho voluntário a "
			+ "partir dos dados de entrada")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = 
					"Job retornado com sucesso"),
			@ApiResponse(responseCode = "400", description =
					"Limite de caracteres excedido!"),
			@ApiResponse(responseCode = "409", description =
					"O Job informado já existe!"),
			@ApiResponse(responseCode = "500", description =
					"Erro interno no servidor.")
	})
	
	@PostMapping
	public JobLowDTO save(@RequestBody JobDTO job) {
		return service.save(job);
	}
	
	@PutMapping("/{id}")
	public JobLowDTO update(@PathVariable("id") Long id, @RequestBody JobDTO job) {
		return service.save(job);
	}
	
	@GetMapping("/all")
	public List<JobLowDTO> listAll() {
		return service.listAll();
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}
	
	@GetMapping
	public Job findById(@RequestParam(required = true) Long id) {
		return service.findById(id);
	}
	
}