package com.algaworks.brewer.controller;

import java.util.UUID;

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
import com.algaworks.brewer.session.TabelasItensSession;

@Controller
@RequestMapping("/vendas")
public class VendasController {
	
	@Autowired
	Cervejas cervejas;
	
	@Autowired
	private TabelasItensSession tabelaItens;
	
	@RequestMapping("/nova")
	public ModelAndView cadastrar() {
		ModelAndView mv = new ModelAndView("venda/CadastroVenda");
		mv.addObject("uuid",UUID.randomUUID().toString());
		return mv;
	}
	
	@PostMapping("/item")
	public ModelAndView adicionarItem(Long codigoCerveja, String uuid) {
		Cerveja cerveja = cervejas.findOne(codigoCerveja);
		tabelaItens.adicionarItem(uuid,cerveja, 1);
		return mvTabelaItensVenda(uuid);
	}
	
	
	//obtendo cerveja de forma tradicional
	
	@PutMapping("/item/{codigoCerveja}")
	public ModelAndView alteraQuantidadeItem(@PathVariable Long codigoCerveja, Integer quantidade, String uuid) {
		Cerveja cerveja = cervejas.findOne(codigoCerveja); //
		tabelaItens.alteraQuantidadeItens(uuid, cerveja, quantidade);
		return mvTabelaItensVenda(uuid);
	}
	
	//obtendo cerveja usando o poder do jpa de findOne automático. Não precisa do trecho Cerveja cerveja = cervejas.findOne(codigoCerveja); //
	
	@DeleteMapping("item/{uuid}/{codigoCerveja}")
	public ModelAndView excluirCerveja(@PathVariable("codigoCerveja") Cerveja cerveja, @PathVariable String uuid) {
		tabelaItens.excluir(uuid,cerveja);
		return mvTabelaItensVenda(uuid);
		
	}

	private ModelAndView mvTabelaItensVenda(String uuid) {
		ModelAndView mv = new ModelAndView("venda/TabelaItensVenda");
		mv.addObject("itens", tabelaItens.getItens(uuid));
		mv.addObject("valorTotal",tabelaItens.getValorTota(uuid));
		return mv;
	}

}
