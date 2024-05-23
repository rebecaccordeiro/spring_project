package br.edu.unichristus.projaura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unichristus.projaura.data.model.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
	
	public Optional<User> findByEmail(String email);
	
}
