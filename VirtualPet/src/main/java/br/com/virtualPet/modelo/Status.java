package br.com.virtualPet.modelo;

public enum Status {
	
	ATIVO("Ativo"),
	BLOQUEADO("Bloqueado"),
	INATIVO("Inativo");
	
	private String status;
	
	
	Status(String status){
		this.status = status;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
