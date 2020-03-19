package com.algaworks.brewer.venda;



import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;



public class TabelaItensVendaTest {
	
	private TabelaItensVenda tabelaItensVenda;
	
	@Before
	public void setUp() {
		this.tabelaItensVenda = new TabelaItensVenda();
	}
	
	@Test
	public void deveCalcularVaorTotaSemItens() throws Exception {		
		assertEquals(BigDecimal.ZERO, tabelaItensVenda.getValorTotal());
		
	}
}
