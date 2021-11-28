package com.produtos.api.enuns;

public enum StatusEntrega {

	ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");

	private Integer cod;
	private String descricao;

	private StatusEntrega(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static StatusEntrega toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (StatusEntrega x : StatusEntrega.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Status inválido" + cod);
	}
}
