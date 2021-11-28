package com.produtos.api.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.produtos.api.enuns.PrioridadeProduto;
import com.produtos.api.enuns.StatusProduto;

@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;
	private String marca;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataEstoque;  
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataEmfalta; 

	private String valor;
	private String observacoes;
	private Integer prioridadeProduto;
	private Integer statusProduto;

	@OneToMany(mappedBy = "produto")
	private List<Entrega> list = new ArrayList<>();
	
	public Produto() {
		super();
		this.setDataEstoque(LocalDateTime.now());
		this.setPrioridadeProduto(PrioridadeProduto.BAIXA_DEMANDA);
		this.setStatusProduto(StatusProduto.ESTOQUE);
		
	}

	public Produto(Integer id, String nome, String marca, String valor, String observacoes,
			PrioridadeProduto prioridadeProduto, StatusProduto statusProduto) {
		super();
		this.id = id;
		this.nome = nome;
		this.marca = marca;
		this.setDataEstoque(LocalDateTime.now());
		this.valor = valor;
		this.observacoes = observacoes;
		this.prioridadeProduto = (prioridadeProduto == null) ? 0 : prioridadeProduto.getCod();
		this.statusProduto = (statusProduto == null) ? 0 : statusProduto.getCod();

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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public LocalDateTime getDataEstoque() {
		return dataEstoque;
	}

	public void setDataEstoque(LocalDateTime dataEstoque) {
		this.dataEstoque = dataEstoque;
	}

	public LocalDateTime getDataEmfalta() {
		return dataEmfalta;
	}

	public void setDataEmfalta(LocalDateTime dataEmfalta) {
		this.dataEmfalta = dataEmfalta;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public PrioridadeProduto getPrioridadeProduto() {
		return PrioridadeProduto.toEnum(this.prioridadeProduto);
	}

	public void setPrioridadeProduto(PrioridadeProduto prioridadeProduto) {
		this.prioridadeProduto = prioridadeProduto.getCod();
	}

	public StatusProduto getStatusProduto() {
		return StatusProduto.toEnum(this.statusProduto);
	}

	public void setStatusProduto(StatusProduto statusProduto) {
		this.statusProduto = statusProduto.getCod();
	}

	public List<Entrega> getList() {
		return list;
	}

	public void setList(List<Entrega> list) {
		this.list = list;
	}

}
