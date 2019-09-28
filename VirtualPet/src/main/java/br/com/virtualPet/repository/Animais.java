package br.com.virtualPet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.virtualPet.modelo.Animal;
import br.com.virtualPet.repository.helper.animal.AnimaisQueries;
@Repository
public interface Animais extends JpaRepository<Animal, Long>, AnimaisQueries {

}
