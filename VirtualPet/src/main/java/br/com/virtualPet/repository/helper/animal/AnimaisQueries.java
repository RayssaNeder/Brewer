package br.com.virtualPet.repository.helper.animal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.virtualPet.modelo.Animal;
import br.com.virtualPet.repository.filter.AnimalFilter;

public interface AnimaisQueries {
	public Page<Animal> filtrar(AnimalFilter animalFilter, Pageable pageable);
}
