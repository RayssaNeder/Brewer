package com.algaworks.brewer.session;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.model.ItemVenda;

@SessionScope
@Component
public class TabelasItensSession {
	private Set<TabelaItensVenda> tabelas = new HashSet<>();

	public void adicionarItem(String uuid, Cerveja cerveja, int i) {
		TabelaItensVenda tabela = buscrTabelaPorUuid(uuid);
		tabela.adicionarItem(cerveja, i);
		tabelas.add(tabela);
	}


	public void alteraQuantidadeItens(String uuid, Cerveja cerveja, Integer quantidade) {
		TabelaItensVenda tabela = buscrTabelaPorUuid(uuid);
		tabela.alteraQuantidadeItens(cerveja, quantidade);
		
	}

	public void excluir(String uuid, Cerveja cerveja) {
		TabelaItensVenda tabela = buscrTabelaPorUuid(uuid);
		tabela.excluir(cerveja);
		
	}

	public List<ItemVenda> getItens(String uuid) {
		return buscrTabelaPorUuid(uuid).getItens();
	}
	
	private TabelaItensVenda buscrTabelaPorUuid(String uuid) {
		TabelaItensVenda tabela =  tabelas.stream()
									.filter(t -> t.getUuid().equals(uuid))
									.findAny()
									.orElse(new TabelaItensVenda(uuid));
		return tabela;
	}
}
