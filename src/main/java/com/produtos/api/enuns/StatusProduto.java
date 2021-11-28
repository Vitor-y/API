package com.produtos.api.enuns;

public enum StatusProduto {

	ESTOQUE(0, "ESTOQUE"), PRATELEIRA(1, "PRATELEIRA"), EM_FALTA(2, "EM_FALTA");

	private Integer cod;
	private String descricao;

	private StatusProduto(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static StatusProduto toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (StatusProduto x : StatusProduto.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Status inválido" + cod);
	}
}