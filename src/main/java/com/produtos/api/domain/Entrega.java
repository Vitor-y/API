package com.produtos.api.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.produtos.api.enuns.PrioridadeEntrega;
import com.produtos.api.enuns.StatusEntrega;

@Entity
public class Entrega {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyy HH:mm") // vai passar um formato para nossa data
	private LocalDateTime dataAbertura;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataFechamento;

	private Integer prioridadeEntrega;
	private String observacoes;
	private Integer statusEntrega;

	@ManyToOne // muitos para um
	@JoinColumn(name = "entregador_id") // criar uma coluna na base de dados do entregador
	private Entregador entregador;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne(cascade = {CascadeType.ALL} )
	@JoinColumn(name = "produto_id")
	private Produto produto;

	public Entrega() { // quando uma entrega for criada, ira receber uma data e hora local, uma
						// prioridade e um status
		super();
		this.setDataAbertura(LocalDateTime.now());
		this.setPrioridadeEntrega(PrioridadeEntrega.BAIXA);
		this.setStatusEntrega(StatusEntrega.ABERTO);

	}

	public Entrega(Integer id, PrioridadeEntrega prioridadeEntrega, String observacoes, StatusEntrega statusEntrega,
			Entregador entregador, Cliente cliente, Produto produto) {
		super();
		this.id = id;
		this.setDataAbertura(LocalDateTime.now());
		this.prioridadeEntrega = (prioridadeEntrega == null) ? 0 : prioridadeEntrega.getCod(); // se o valor for igual a nulo, não foi passado um valor para a entrega, caso o contrario pega o codigo que foi passado para ele
		this.observacoes = observacoes;
		this.statusEntrega = (statusEntrega == null) ? 0 : statusEntrega.getCod();
		this.entregador = entregador;
		this.cliente = cliente;
		this.produto = produto;
	}

	// metodos acessores(get), ira retornar o valor do atributo, da classe
	// metodos moficadores(set), ira modificar os valores do atributo
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

	public PrioridadeEntrega getPrioridadeEntrega() { // retorna o valor estatico, ira verificar se existe o valor que foi informado
		return PrioridadeEntrega.toEnum(this.prioridadeEntrega);
	}

	public void setPrioridadeEntrega(PrioridadeEntrega prioridadeEntrega) { // modifica o valor do seu atributo e assim recebendo o valor que sera passado pelo parametro
		this.prioridadeEntrega = prioridadeEntrega.getCod();
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public StatusEntrega getStatusEntrega() {
		return StatusEntrega.toEnum(this.statusEntrega);
	}

	public void setStatusEntrega(StatusEntrega statusEntrega) {
		this.statusEntrega = statusEntrega.getCod();
	}

	public Entregador getEntregador() {
		return entregador;
	}

	public void setEntregador(Entregador entregador) {
		this.entregador = entregador;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrega other = (Entrega) obj;
		return Objects.equals(id, other.id);
	}

}