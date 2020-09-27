package com.algaworks.brewer.model;

public enum Status {
	ORCAMENTO("Orçamento"),
	EMITIDA("Emitida"),
	CANCELADA("Cancelada");
	
	private String status;

	private Status(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
