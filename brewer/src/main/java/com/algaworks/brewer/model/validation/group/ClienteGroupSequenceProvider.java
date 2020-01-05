package com.algaworks.brewer.model.validation.group;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.algaworks.brewer.model.Cliente;

public class ClienteGroupSequenceProvider implements DefaultGroupSequenceProvider<Cliente> {

	@Override
	public List<Class<?>> getValidationGroups(Cliente cliente) {
		List<Class<?>> groups = new ArrayList<>();
		groups.add(Cliente.class);
		
		if(isPessoaSelecionada(cliente)) {
			groups.add(cliente.getTipoPessoa().getGrupo());
		}
		return groups;
	}

	private boolean isPessoaSelecionada(Cliente cliente) {
		return cliente != null && cliente.getTipoPessoa() != null;
	}

}
