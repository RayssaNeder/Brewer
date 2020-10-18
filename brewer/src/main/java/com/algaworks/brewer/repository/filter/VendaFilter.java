package com.algaworks.brewer.repository.filter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.algaworks.brewer.model.Status;

public class VendaFilter {
	private Long codigo;
	private Status status;
	private LocalDateTime dtCriacaoDe;
	private LocalDateTime dtCriacaoAte;
	private BigDecimal vlCriacaoDe;
	private BigDecimal vlCriacaoAte;
	private String nomeCliente;
	private String cpfCnpj;
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public LocalDateTime getDtCriacaoDe() {
		return dtCriacaoDe;
	}
	public void setDtCriacaoDe(LocalDateTime dtCriacaoDe) {
		this.dtCriacaoDe = dtCriacaoDe;
	}
	public LocalDateTime getDtCriacaoAte() {
		return dtCriacaoAte;
	}
	public void setDtCriacaoAte(LocalDateTime dtCriacaoAte) {
		this.dtCriacaoAte = dtCriacaoAte;
	}
	public BigDecimal getVlCriacaoDe() {
		return vlCriacaoDe;
	}
	public void setVlCriacaoDe(BigDecimal vlCriacaoDe) {
		this.vlCriacaoDe = vlCriacaoDe;
	}
	public BigDecimal getVlCriacaoAte() {
		return vlCriacaoAte;
	}
	public void setVlCriacaoAte(BigDecimal vlCriacaoAte) {
		this.vlCriacaoAte = vlCriacaoAte;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	
	
	

}
