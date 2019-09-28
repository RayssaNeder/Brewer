package br.com.virtualPet.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "especie")
public class Especie implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	
	@NotNull(message="O nome é obrigatório")
	@Size(min = 1, max = 15, message = "O tamanho do nome deve estar entr 1 e 15 caracteres")
	private String nome;
	
	
	
	@OneToMany(mappedBy = "especie")
	private List<Animal> animais;


	@OneToMany(mappedBy = "especie")
	private List<Raca> racas;

	public Long getCodigo() {
		return codigo;
	}



	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public List<Animal> getAnimais() {
		return animais;
	}



	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}


	

	public List<Raca> getRacas() {
		return racas;
	}



	public void setRacas(List<Raca> racas) {
		this.racas = racas;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Especie other = (Especie) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}


	
	


}
