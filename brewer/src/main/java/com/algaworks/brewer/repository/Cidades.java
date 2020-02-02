package com.algaworks.brewer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.brewer.model.Cidade;

public interface Cidades extends JpaRepository<Cidade, Long> {
	
	public List<Cidade> findByEstadoCodigo(Long codigoEstado);
	
	public Optional <Cidade> findByNomeIgnoreCaseAndEstadoCodigo(String nome, Long codigoEstado);

}
