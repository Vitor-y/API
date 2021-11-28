package com.produtos.api.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produtos.api.domain.Cliente;
import com.produtos.api.domain.Entrega;
import com.produtos.api.domain.Entregador;
import com.produtos.api.domain.Produto;
import com.produtos.api.enuns.PrioridadeEntrega;
import com.produtos.api.enuns.PrioridadeProduto;
import com.produtos.api.enuns.StatusEntrega;
import com.produtos.api.enuns.StatusProduto;
import com.produtos.api.repositories.ClienteRepository;
import com.produtos.api.repositories.EntregaRepository;
import com.produtos.api.repositories.EntregadorRepository;
import com.produtos.api.repositories.ProdutoRepository;

@Service // para injetar uma instancia dessa classe em partes diferentes do codigo, sendo
			// delegado pelo spirng
public class DBService {

	@Autowired
	private EntregadorRepository entregadorRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EntregaRepository entregaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;

	public void instanciaDB() {

		Entregador e1 = new Entregador(null, "Vitor", "079.454.440-16", "(11) 988888888", "oriente");
		Entregador e2 = new Entregador(null, "Ronaldo", "883.494.650-26", "(12) 988888866", "satelite");
		Cliente c1 = new Cliente(null, "Francisco", "026.532.320-73", "(58) 988888887", "parque");
		Produto p1 = new Produto(null, "sabonete", "pantene", "30", "Teste", PrioridadeProduto.BAIXA_DEMANDA,
				StatusProduto.ESTOQUE);
		Entrega en1 = new Entrega(null, PrioridadeEntrega.ALTA, "Teste", StatusEntrega.ANDAMENTO, e1, c1, p1);

		e1.getList().add(en1);
		c1.getList().add(en1);
		p1.getList().add(en1);

		entregadorRepository.saveAll(Arrays.asList(e1));
		entregadorRepository.saveAll(Arrays.asList(e2));
		clienteRepository.saveAll(Arrays.asList(c1));
		entregaRepository.saveAll(Arrays.asList(en1));
		produtoRepository.saveAll(Arrays.asList(p1));
	}

}
