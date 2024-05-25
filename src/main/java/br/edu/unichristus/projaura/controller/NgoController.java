package br.edu.unichristus.projaura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unichristus.projaura.data.dto.JobLowDTO;
import br.edu.unichristus.projaura.data.dto.NgoDTO;
import br.edu.unichristus.projaura.data.dto.NgoLowDTO;
import br.edu.unichristus.projaura.data.model.Job;
import br.edu.unichristus.projaura.data.model.Ngo;
import br.edu.unichristus.projaura.service.NgoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/ngo")
public class NgoController {

    @Autowired
    private NgoService service;

    @Operation(summary = "Cadastrar ONG | role: [ADMIN]",
            tags = "Ngo", description = "Possibilita "
            + "cadastrar uma ONG a "
            + "partir dos dados de entrada")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = 
                    "ONG retornada com sucesso"),
            @ApiResponse(responseCode = "400", description =
                    "Limite de caracteres excedido!"),
            @ApiResponse(responseCode = "409", description =
                    "O Login informado já existe!"),
            @ApiResponse(responseCode = "500", description =
                    "Erro interno no servidor.")
    })
    
    @PostMapping
    public NgoLowDTO save(@RequestBody NgoDTO ngo) {
        return service.save(ngo);
    }

    @Operation(summary = "Editar ONG | role: [ADMIN]",
            tags = "Ngo", description = "Possibilita "
            + "editar uma ONG a "
            + "partir dos dados de entrada")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = 
                    "ONG retornada com sucesso"),
            @ApiResponse(responseCode = "400", description =
                    "Limite de caracteres excedido!"),
            @ApiResponse(responseCode = "409", description =
                    "O Login informado já existe!"),
            @ApiResponse(responseCode = "500", description =
                    "Erro interno no servidor.")
    })
    @PutMapping
    public NgoLowDTO update(@RequestBody NgoDTO ngo) {
        return service.save(ngo);
    }

    @GetMapping("/all")
    public List<NgoLowDTO> listAll() {
        return service.listAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

    @GetMapping
    public Ngo findById(@RequestParam(required = true) Long id) {
        return service.findById(id);
    }
    
    @PostMapping("/createjob")
    public ResponseEntity<Job> createJob(@RequestParam(required = true) Long id, @RequestBody Job job) {
        Job createdJob = service.createJob(id, job);
        return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/jobs")
    public ResponseEntity<List<JobLowDTO>> listAllJobs(@PathVariable Long id) {
        List<JobLowDTO> jobs = service.listAllJobs(id);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }
}
