package com.algaworks.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.brewer.model.Grupo;

@Repository
public interface Grupos extends JpaRepository<Grupo, Long> {

}
