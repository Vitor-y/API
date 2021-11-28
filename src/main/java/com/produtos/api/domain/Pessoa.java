package com.produtos.api.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.br.CPF;

@Entity // esta informando ao JPA que esta classe é uma entidade, e que seja criado uma
		// tabela na base de dados
public abstract class Pessoa implements Serializable{ // não é possivel criar instancias nessa classe (abstract)
	private static final long serialVersionUID = 1L;
	
	@Id // para informar uma chave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) // esta informando que a geração dessa chave primaria vai ficar
														// por conta da base de dados
	private Integer id;
	private String nome;

	@CPF // para saber se o CPF é valido ou não
	private String cpf;
	private String telefone;
	private String endereco;

	public Pessoa() {
		super(); // contrutor sem nenhum parametro
	}

	public Pessoa(Integer id, String nome, String cpf, String telefone, String endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id) && Objects.equals(cpf, other.cpf);
	}

}
