package com.produtos.api.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import com.produtos.api.domain.Entrega;

public class EntregaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private LocalDateTime dataAbertura;
	private LocalDateTime dataFechamento;
	
	private Integer prioridadeEntrega;
	
	@NotEmpty(message = "O campo OBSERVAÇÕES é requerido!")
	private String observacoes;
	private Integer statusEntrega;
	
	@NotEmpty(message = "O campo ENTREGADOR é requerido!")
	private Integer entregador;
	
	@NotEmpty(message = "O campo CLIENTE é requerido!")
	private Integer cliente;
	
	@NotEmpty(message = "O campo PRODUTO é requerido!")
	private Integer produto;

	public EntregaDTO() {
		super();

	}

	public EntregaDTO(Entrega obj) {
		super();
		this.id = obj.getId();
		this.dataAbertura = obj.getDataAbertura();
		this.dataFechamento = obj.getDataFechamento();
		this.prioridadeEntrega = obj.getPrioridadeEntrega().getCod();
		this.observacoes = obj.getObservacoes();
		this.statusEntrega = obj.getStatusEntrega().getCod();
		this.entregador = obj.getEntregador().getId();
		this.cliente = obj.getCliente().getId();
		this.produto = obj.getProduto().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Integer getPrioridadeEntrega() {
		return prioridadeEntrega;
	}

	public void setPrioridadeEntrega(Integer prioridadeEntrega) {
		this.prioridadeEntrega = prioridadeEntrega;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Integer getStatusEntrega() {
		return statusEntrega;
	}

	public void setStatusEntrega(Integer statusEntrega) {
		this.statusEntrega = statusEntrega;
	}

	public Integer getEntregador() {
		return entregador;
	}

	public void setEntregador(Integer entregador) {
		this.entregador = entregador;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public Integer getProduto() {
		return produto;
	}

	public void setProduto(Integer produto) {
		this.produto = produto;
	}

}
