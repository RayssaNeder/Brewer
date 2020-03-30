package com.algaworks.brewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.Cervejas;
import com.algaworks.brewer.session.TabelaItensVenda;

@Controller
@RequestMapping("/vendas")
public class VendasController {
	
	@Autowired
	Cervejas cervejas;
	
	@Autowired
	private TabelaItensVenda tabelaItensVenda;
	
	@RequestMapping("/nova")
	public String cadastrar() {
		return "venda/CadastroVenda";
	}
	
	@PostMapping("/item")
	public ModelAndView adicionarItem(Long codigoCerveja) {
		Cerveja cerveja = cervejas.findOne(codigoCerveja);
		tabelaItensVenda.adicionarItem(cerveja, 1);
		return mvTabelaItensVenda();
	}
	
	
	//obtendo cerveja de forma tradicional
	
	@PutMapping("/item/{codigoCerveja}")
	public ModelAndView alteraQuantidadeItem(@PathVariable Long codigoCerveja, Integer quantidade) {
		Cerveja cerveja = cervejas.findOne(codigoCerveja); //
		tabelaItensVenda.alteraQuantidadeItens(cerveja, quantidade);
		return mvTabelaItensVenda();
	}
	
	//obtendo cerveja usando o poder do jpa de findOne automático. Não precisa do trecho Cerveja cerveja = cervejas.findOne(codigoCerveja); //
	
	@DeleteMapping("item/{codigoCerveja}")
	public ModelAndView excluirCerveja(@PathVariable("codigoCerveja") Cerveja cerveja) {
		tabelaItensVenda.excluir(cerveja);
		return mvTabelaItensVenda();
		
	}

	private ModelAndView mvTabelaItensVenda() {
		ModelAndView mv = new ModelAndView("venda/TabelaItensVenda");
		mv.addObject("itens", tabelaItensVenda.getItens());
		return mv;
	}

}
