package br.com.virtualPet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.virtualPet.modelo.Raca;

@Repository
public interface Racas extends JpaRepository<Raca, Long> {

}
