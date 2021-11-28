package com.produtos.api.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.produtos.api.domain.Produto;

public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "O campo NOME é requerido!")
	private String nome;
	
	@NotEmpty(message = "O campo MARCA é requerido!")
	private String marca;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataEstoque;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataEmfalta;
	
	@NotEmpty(message = "O campo VALOR é requerido!")
	private String valor;
	
	@NotEmpty(message = "O campo OBSERVAÇÕES é requerido!")
	private String observacoes;
	
	private Integer prioridadeProduto;
	private Integer statusProduto;

	public ProdutoDTO() {
		super();
	}

	public ProdutoDTO(Produto obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.marca = obj.getMarca();
		this.dataEstoque = obj.getDataEstoque();
		this.dataEmfalta = obj.getDataEmfalta();
		this.valor = obj.getValor();
		this.observacoes = obj.getObservacoes();
		this.prioridadeProduto = obj.getPrioridadeProduto().getCod();
		this.statusProduto = obj.getStatusProduto().getCod();
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

	public Integer getPrioridadeProduto() {
		return prioridadeProduto;
	}

	public void setPrioridadeProduto(Integer prioridadeProduto) {
		this.prioridadeProduto = prioridadeProduto;
	}

	public Integer getStatusProduto() {
		return statusProduto;
	}

	public void setStatusProduto(Integer statusProduto) {
		this.statusProduto = statusProduto;
	}

}
