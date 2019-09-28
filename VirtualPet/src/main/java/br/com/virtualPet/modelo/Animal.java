package br.com.virtualPet.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "animal")
public class Animal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	@NotBlank
	public String nome;
	@NotBlank
	public String apelido;
	//public Date dataNascimento;
	@NotNull(message = "O sexo é obrigatório")
	@Enumerated(EnumType.STRING)
	public Sexo sexo;
	@NotNull(message = "Espécie é obrigatória")
	@ManyToOne
	@JoinColumn(name = "codigo_especie")
	public Especie especie;
	public Raca raca;
	public String pelagem;
	public String porte;
	public String peso;
	public String pai;
	public String mae;
	@NotNull(message = "O status é obrigatório")
	@Enumerated(EnumType.STRING)
	public Status statu;
	public String cor;
	public Date nascimento;
	
	@Size(min = 1, max = 50, message = "O tamanho da descrição deve estar entre 1 e 50")
	private String observacao;
	
	
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
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
	
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public Especie getEspecie() {
		return especie;
	}
	public void setEspecie(Especie especie) {
		this.especie = especie;
	}
	public void setStatus(Status statu) {
		this.statu = statu;
	}
	
	public Status getStatu() {
		return statu;
	}
	public void setStatu(Status statu) {
		this.statu = statu;
	}
		
	public void setRaca(Raca raca) {
		this.raca = raca;
	}
	public String getPelagem() {
		return pelagem;
	}
	public void setPelagem(String pelagem) {
		this.pelagem = pelagem;
	}
	public String getPorte() {
		return porte;
	}
	public void setPorte(String porte) {
		this.porte = porte;
	}
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	
	public Raca getRaca() {
		return raca;
	}
	public String getPai() {
		return pai;
	}
	public void setPai(String pai) {
		this.pai = pai;
	}
	public String getMae() {
		return mae;
	}
	public void setMae(String mae) {
		this.mae = mae;
	}
	
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	
	
	
	
	
}
