package com.algaworks.brewer.controller.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public class PageWrapper<T> {
	
	private Page<T> page;
	private UriComponentsBuilder urlBuilder;

	public PageWrapper(Page<T> page, HttpServletRequest httpServletRequest) {
		this.page = page;
		this.urlBuilder = ServletUriComponentsBuilder.fromRequest(httpServletRequest);
	}
	
	
	public List<T> getConteudo(){
		return page.getContent();
	}
	
	public int getAtual() {
		return page.getNumber();
	}
	
	public int getTotal() {
		return page.getTotalPages();
	}
	
	public boolean isPrimeira() {
		return page.isFirst();
	}
	
	public boolean isUltima() {
		return page.isLast();
	}
	
	public boolean isVazia() {
		return page.getContent().isEmpty();
	}
	
	public String urlParaPagina(int pagina) {
		return urlBuilder.replaceQueryParam("page", pagina).build(true).encode().toUriString();
	}

}
