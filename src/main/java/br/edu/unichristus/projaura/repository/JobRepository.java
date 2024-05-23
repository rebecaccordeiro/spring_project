package br.edu.unichristus.projaura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unichristus.projaura.data.model.Job;
import br.edu.unichristus.projaura.data.model.Ngo;

import java.util.List;
import java.util.Optional;


public interface JobRepository extends JpaRepository<Job, Long> {
	
	List<Job> findByNgoId(Long ngoId);
		
}
