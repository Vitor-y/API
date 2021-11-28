package com.produtos.api.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produtos.api.domain.Entregador;
import com.produtos.api.domain.Pessoa;
import com.produtos.api.dtos.EntregadorDTO;
import com.produtos.api.exceptionsServices.DataIntegratyViolationException;
import com.produtos.api.exceptionsServices.ObjectNotFoundException;
import com.produtos.api.repositories.EntregadorRepository;
import com.produtos.api.repositories.PessoaRepository;

@Service // anotação de serviço podendo injetar ela em diferentes parte do nosso codigo
public class EntregadorService {

	@Autowired // delegando ao spring de que ele quem vai gerenciar
	private EntregadorRepository repository;
	
	@Autowired
	private PessoaRepository pessoarespository;

	public Entregador findById(Integer id) {
		Optional<Entregador> obj = repository.findById(id); // faz uma busca pelo ID
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ! Id:" + id + ", Tipo: " + Entregador.class.getName())); // pode ou não encontrar
																								// o ID
	}

	public List<Entregador> findAll() {
		return repository.findAll();
	}

	public Entregador create(EntregadorDTO objDTO) { // instanciamos o objeto que queremos e que salve no banco
		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		return repository.save(
				new Entregador(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone(), objDTO.getEndereco()));
	}

	public Entregador update(Integer id, @Valid EntregadorDTO objDTO) {
		Entregador oldObj = findById(id);

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) { // se o CPF passado no BD existir no banco
																			// sera passado o entregador com esse CPF,
																			// passando o id, se for igual atualiza, se
																			// for diferente ele notifica
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		oldObj.setNome(objDTO.getNome()); // salvando e atualizando as informações antigas
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		oldObj.setEndereco(objDTO.getEndereco());
		return repository.save(oldObj);
	}
	// deleta um entregador pelo id 
	public void delete(Integer id) {
		Entregador obj = findById(id);
		if(obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Entregador possui entregas para ser terminada, não pode ser deletado!");
		}
		repository.deleteById(id);
	}
	//  busca pelo entregador com o cpf 
	private Pessoa findByCPF(EntregadorDTO objDTO) {
		Pessoa obj = pessoarespository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}

}
