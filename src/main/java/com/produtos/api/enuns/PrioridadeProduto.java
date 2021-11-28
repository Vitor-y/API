package com.produtos.api.enuns;

public enum PrioridadeProduto {

	BAIXA_DEMANDA(0, "BAIXA_DEMANDA"), MEDIA_DEMANDA(1, "MEDIA_DEMANDA"), ALTA_DEMANDA(2, "ALTA_DEMANDA");

	private Integer cod;
	private String descricao;

	private PrioridadeProduto(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static PrioridadeProduto toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (PrioridadeProduto x : PrioridadeProduto.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Prioridade inválida" + cod);
	}
}
