package com.produtos.api.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Cliente extends Pessoa implements Serializable { // import da clase produto
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "cliente")
	private List<Entrega> list = new ArrayList<>();

	public Cliente() {
		super();

	}

	public Cliente(Integer id, String nome, String cpf, String telefone, String endereco) {
		super(id, nome, cpf, telefone, endereco);

	}

	public List<Entrega> getList() {
		return list;
	}

	public void setList(List<Entrega> list) {
		this.list = list;
	}

}
