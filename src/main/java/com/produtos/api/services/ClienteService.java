package com.produtos.api.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produtos.api.domain.Cliente;
import com.produtos.api.domain.Pessoa;
import com.produtos.api.dtos.ClienteDTO;
import com.produtos.api.exceptionsServices.DataIntegratyViolationException;
import com.produtos.api.exceptionsServices.ObjectNotFoundException;
import com.produtos.api.repositories.ClienteRepository;
import com.produtos.api.repositories.PessoaRepository;

@Service // anotação de serviço podendo injetar ela em diferentes parte do nosso codigo
public class ClienteService {

	@Autowired // delegando ao spring de que ele quem vai gerenciar
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoarespository;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id); // faz uma busca pelo ID
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ! Id:" + id + ", Tipo: " + Cliente.class.getName())); // pode ou não encontrar
																								// o ID
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) { // instanciamos o objeto que queremos e que salve no banco
		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		return repository.save(
				new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone(), objDTO.getEndereco()));
	}

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		Cliente oldObj = findById(id);

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
	// deleta um cliente pelo id 
	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Cliente possui entregas para ser terminada, não pode ser deletado!");
		}
		repository.deleteById(id);
	}
	//  busca pelo cliente com o cpf 
	private Pessoa findByCPF(ClienteDTO objDTO) {
		Pessoa obj = pessoarespository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}

}
