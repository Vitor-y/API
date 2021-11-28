package com.produtos.api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produtos.api.domain.Cliente;
import com.produtos.api.domain.Entrega;
import com.produtos.api.domain.Entregador;
import com.produtos.api.domain.Produto;
import com.produtos.api.dtos.EntregaDTO;
import com.produtos.api.enuns.PrioridadeEntrega;
import com.produtos.api.enuns.StatusEntrega;
import com.produtos.api.exceptionsServices.ObjectNotFoundException;
import com.produtos.api.repositories.EntregaRepository;

@Service // injetar partes das entregas em outros codigos
public class EntregaService {

	@Autowired
	private EntregaRepository repository;

	@Autowired
	private EntregadorService entregadorService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ProdutoService produtoService;

	public Entrega findById(Integer id) {
		Optional<Entrega> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Entrega.class.getName()));
	}

	public List<Entrega> findAll() {
		return repository.findAll();
	}

	public Entrega create(@Valid EntregaDTO obj) {
		return fromDTO(obj);
	}

	public Entrega update(@Valid EntregaDTO obj) {
		findById(obj.getId());
		return fromDTO(obj);
	}

	private Entrega fromDTO(EntregaDTO obj) {
		Entrega newObj = new Entrega();
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridadeEntrega(PrioridadeEntrega.toEnum(obj.getPrioridadeEntrega()));
		newObj.setStatusEntrega(StatusEntrega.toEnum(obj.getStatusEntrega()));

		Entregador ent = entregadorService.findById(obj.getEntregador());
		Cliente cli = clienteService.findById(obj.getCliente());
		Produto pro = produtoService.findById(obj.getProduto());

		newObj.setEntregador(ent);
		newObj.setCliente(cli);
		newObj.setProduto(pro);
		
		if(newObj.getStatusEntrega().getCod().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
		}
		
		return repository.save(newObj);
	}

}
