package com.algaworks.brewer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.brewer.model.Cidade;
import com.algaworks.brewer.repository.helper.cerveja.CidadesQueries;

@Repository
public interface Cidades extends JpaRepository<Cidade, Long>, CidadesQueries {
	

	public List<Cidade> findByEstadoCodigo(Long codigoEstado);
	
	public Optional <Cidade> findByNomeIgnoreCaseAndEstadoCodigo(String nome, Long codigoEstado);

}
