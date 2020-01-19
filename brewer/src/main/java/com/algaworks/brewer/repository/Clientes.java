package com.algaworks.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.brewer.model.Cliente;
import com.algaworks.brewer.repository.helper.cerveja.ClientesQueries;


@Repository
public interface Clientes extends JpaRepository<Cliente, Long> , ClientesQueries{

	Optional<Cliente> findByCpfOuCnpj(String cpfOuCnpj);

}
