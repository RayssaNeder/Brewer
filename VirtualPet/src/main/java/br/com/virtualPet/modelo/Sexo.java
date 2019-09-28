package br.com.virtualPet.modelo;

public enum Sexo {

	FEMEA("Fêmea"),
	MACHO("Macho");
	
	private String sexo;
	
	

	private Sexo(String sexo) {
		this.sexo = sexo;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	
}
