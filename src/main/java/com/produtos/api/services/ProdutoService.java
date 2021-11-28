package com.produtos.api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produtos.api.domain.Produto;
import com.produtos.api.dtos.ProdutoDTO;
import com.produtos.api.enuns.PrioridadeProduto;
import com.produtos.api.enuns.StatusProduto;
import com.produtos.api.exceptionsServices.DataIntegratyViolationException;
import com.produtos.api.exceptionsServices.ObjectNotFoundException;
import com.produtos.api.repositories.ProdutoRepository;

@Service // para poder injetar as instancias em partes diferentes do codigo
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	public Produto findById(Integer id) {
		Optional<Produto> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	public List<Produto> findAll() { // busca todos os produtos na base de dados
		return repository.findAll();
	}

	public Produto create(@Valid ProdutoDTO obj) {
		return fromDTO(obj);
	}

	public Produto update(@Valid ProdutoDTO obj) {
		findById(obj.getId());
		return fromDTO(obj); // recebe o objeto de transferencia e instancia uma nova ordem e passa para a
								// nova ordem (atualiza)
	}

	private Produto fromDTO(ProdutoDTO obj) {
		Produto newObj = new Produto();
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridadeProduto(PrioridadeProduto.toEnum(obj.getPrioridadeProduto()));
		newObj.setStatusProduto(StatusProduto.toEnum(obj.getStatusProduto()));
		newObj.setNome(obj.getNome());
		newObj.setMarca(obj.getMarca());
		newObj.setValor(obj.getValor());

		if (newObj.getStatusProduto().getCod().equals(2)) { // se o produto receber um status 2 ele dirá que o produto
															// está em falta
			newObj.setDataEmfalta(LocalDateTime.now());
		}

		return repository.save(newObj); // verifica que é o msm id e age como atualização
	}

	// deleta um cliente pelo id
	public void delete(Integer id) {
		Produto obj = findById(id);
		if (obj.getList().size() > 0) {
			throw new DataIntegratyViolationException(
					"Produto possui entregas para ser terminada, não pode ser deletado!");
		}
		repository.deleteById(id);
	}

}
