package br.com.virtualPet.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.virtualPet.modelo.Especie;
import br.com.virtualPet.repository.Especies;
import br.com.virtualPet.service.exception.NomeJaCadastradoException;

@Service
public class CadastroEspecieService {
	
	@Autowired
	private Especies especies;
	
	
	@Transactional
	public Especie salvar(Especie especie) {
		Optional<Especie> especieOptional = especies.findByNomeIgnoreCase(especie.getNome());
		if(especieOptional.isPresent()) {
			throw new NomeJaCadastradoException("Nome de espécie já cadastrado");
		}
		
		return especies.saveAndFlush(especie);
	}

}
