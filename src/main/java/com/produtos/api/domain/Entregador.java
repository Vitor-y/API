package com.produtos.api.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Entregador extends Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore // para ignorar e não trazer em looping 
	@OneToMany(mappedBy = "entregador") // um para muitos
	private List<Entrega> list = new ArrayList<>(); // lista de entrega

	public Entregador() {
		super();

	}

	public Entregador(Integer id, String nome, String cpf, String telefone, String endereco) {
		super(id, nome, cpf, telefone, endereco);

	}

	public List<Entrega> getList() {
		return list;
	}

	public void setList(List<Entrega> list) {
		this.list = list;
	}

}
