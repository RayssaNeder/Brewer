package br.com.virtualPet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.virtualPet.modelo.Especie;

@Repository
public interface Especies extends JpaRepository<Especie, Long> {
	
	public Optional<Especie> findByNomeIgnoreCase(String nome);
}
