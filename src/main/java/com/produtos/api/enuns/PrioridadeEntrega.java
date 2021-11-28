package com.produtos.api.enuns;

public enum PrioridadeEntrega {
	
	BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(2, "ALTA");

	private Integer cod;
	private String descricao;

	private PrioridadeEntrega(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static PrioridadeEntrega toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (PrioridadeEntrega x : PrioridadeEntrega.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Prioridade inválida" + cod);
	}
}