package br.edu.unichristus.projaura.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.edu.unichristus.projaura.data.dto.JobDTO;
import br.edu.unichristus.projaura.data.dto.JobLowDTO;
import br.edu.unichristus.projaura.data.model.Job;
import br.edu.unichristus.projaura.dozer.DozerConverter;
import br.edu.unichristus.projaura.exception.CommonsException;
import br.edu.unichristus.projaura.repository.JobRepository;

@Service
public class JobService {
	
	@Autowired
	private JobRepository repository;
	
	public JobLowDTO save(JobDTO jobDTO) {
		var jobModel = DozerConverter.parseObject(jobDTO, Job.class);
		if(jobModel.getTitle().length() > 150) {
			throw new CommonsException(
					HttpStatus.BAD_REQUEST,
					"unichristus.backend.service.job.badrequest.exception",
					"Limite de caracteres excedido!"
					);
		}
		
		var jobSaved = repository.save(jobModel);
		var jobLowDTO = DozerConverter.parseObject(jobSaved, JobLowDTO.class);
		
		return jobLowDTO;
	}
	
//	public List<JobLowDTO> listAll() {
//		var listJobLow = repository.findAll();
//		var listConverted = DozerConverter.parseListObjects(listJobLow, JobLowDTO.class);
//		
//		return listConverted;
//	}

    public List<JobLowDTO> listAll() {
        return repository.findAll().stream()
                .map(job -> JobLowDTO.builder()
                        .id(job.getId())
                        .title(job.getTitle())
                        .ngoName(job.getNgo().getName())
                        .build())
                .collect(Collectors.toList());
    }
	
	
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public Job findById(Long id) {
		var job = repository.findById(id);
		if(job.isEmpty()) {
			throw new CommonsException(
					HttpStatus.NOT_FOUND,
					"unichristus.backend.service.job.notfound.exception",
					"Job com a ID informada não foi encontrado."
					);
		}
		return job.get();
	}
	
}
