package com.produtos.api.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.produtos.api.domain.Cliente;
import com.produtos.api.dtos.ClienteDTO;
import com.produtos.api.services.ClienteService;

@CrossOrigin
@RestController
@RequestMapping(value = "/clientes") // end point inicial, para poder acessar
public class ClienteResource { // classe de controller, como uma classe que vai receber as requisições http

	@Autowired
	private ClienteService service;

	@GetMapping(value = "/{id}") // quando indentificar o endpoint ira passar o path no final do id que buscamos
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
		Cliente obj = service.findById(id); // instancia um clientes pelo metodo findbyId
		ClienteDTO objDTO = new ClienteDTO(obj); // depois instanciamos um entragorDTO e fazemos uma conversão 
		return ResponseEntity.ok().body(objDTO);
	}
	
	@GetMapping // quando recebermos um metodo get sobre clientes, e vier nada como parametro ele vai indentificar que queremos esse metodo
	public ResponseEntity<List<ClienteDTO>> findAll() {
		
		List<ClienteDTO> listDTO = service.findAll()
				.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
		}
	
		@PostMapping // requisição http do tipo post 
		public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDTO) { 
			Cliente newObj = service.create(objDTO); 
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest() // como forma de boas práticas toda vez que criamos um novo 
					.path("/{id}").buildAndExpand(newObj.getId()).toUri(); // objeto no banco nos temos que passar a URI de acesso 
			
			return ResponseEntity.created(uri).build(); // para o novo objeto
		}   
		
		// Atualiza o Cliente
		
		@PutMapping(value = "/{id}") // vai receber uma variavel de path
		public ResponseEntity<ClienteDTO> update(@PathVariable Integer id,@Valid @RequestBody ClienteDTO objDTO) { // recebendo as variaveis atualizadas e o id 
			ClienteDTO newObj = new ClienteDTO(service.update(id, objDTO));
			return ResponseEntity.ok().body(newObj);
		}
		
		// Deleta o Cliente
		
		@DeleteMapping(value = "/{id}")
		public ResponseEntity<Void> delete(@PathVariable Integer id) {
			service.delete(id);
			return ResponseEntity.noContent().build();
		}
	}