package br.edu.unichristus.projaura.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.edu.unichristus.projaura.data.dto.JobLowDTO;
import br.edu.unichristus.projaura.data.dto.NgoDTO;
import br.edu.unichristus.projaura.data.dto.NgoLowDTO;
import br.edu.unichristus.projaura.data.model.Job;
import br.edu.unichristus.projaura.data.model.Ngo;
import br.edu.unichristus.projaura.dozer.DozerConverter;
import br.edu.unichristus.projaura.exception.CommonsException;
import br.edu.unichristus.projaura.repository.JobRepository;
import br.edu.unichristus.projaura.repository.NgoRepository;

@Service
public class NgoService {

    @Autowired
    private NgoRepository repository;

    @Autowired
    private JobRepository jobRepository;

    public NgoLowDTO save(NgoDTO ngoDTO) {
        var ngoModel = DozerConverter.parseObject(ngoDTO, Ngo.class);
        if (ngoModel.getName().length() > 150) {
            throw new CommonsException(
                    HttpStatus.BAD_REQUEST,
                    "unichristus.backend.service.ngo.badrequest.exception",
                    "Limite de caracteres excedido!"
            );
        }
        var ngoFind = repository.findByEmail(ngoModel.getEmail());
        if (!ngoFind.isEmpty()) {
            throw new CommonsException(
                    HttpStatus.CONFLICT,
                    "unichristus.backend.service.ngo.conflict.exception",
                    "O email já está cadastrado!"
            );
        }

        var ngoSaved = repository.save(ngoModel);
        var ngoLowDTO = DozerConverter.parseObject(ngoSaved, NgoLowDTO.class);

        return ngoLowDTO;
    }

    public List<NgoLowDTO> listAll() {
        var listNgoLow = repository.findAll();
        var listConverted = DozerConverter.parseListObjects(listNgoLow, NgoLowDTO.class);

        return listConverted;
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    public Ngo findById(Long id) {
        var ngo = repository.findById(id);
        if (ngo.isEmpty()) {
            throw new CommonsException(
                    HttpStatus.NOT_FOUND,
                    "unichristus.backend.service.ngo.notfound.exception",
                    "ONG com a ID informada não foi encontrada."
            );
        }
        return ngo.get();
    }

    public Job createJob(Long id, Job job) {
        var ngo = findById(id);
        job.setNgo(ngo);
        return jobRepository.save(job);
    }

    public List<JobLowDTO> listAllJobs(Long id) {
        var ngo = findById(id);
        return jobRepository.findByNgoId(id).stream()
                .map(job -> JobLowDTO.builder()
                        .id(job.getId())
                        .title(job.getTitle())
                        .ngoName(job.getNgo().getName())
                        .build())
                .collect(Collectors.toList());
    }
}
