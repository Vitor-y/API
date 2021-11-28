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

import com.produtos.api.dtos.ProdutoDTO;
import com.produtos.api.services.ProdutoService;

@CrossOrigin
@RestController // controladora rest
@RequestMapping(value = "/produto") // para acessar os valores, a pagina. 
public class ProdutoResource {

	@Autowired
	private ProdutoService service; // responsavel por instanciar, gerenciar e destruir é o spring 
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProdutoDTO> findById(@PathVariable Integer id) {
		ProdutoDTO obj = new ProdutoDTO(service.findById(id));
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping // metodo para mapear 
	public ResponseEntity<List<ProdutoDTO>> findAll() { // vai retorna uma lista de produtos 
		List<ProdutoDTO> list = service.findAll().stream()
				.map(obj -> new ProdutoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping // metodo para inserir 
	public ResponseEntity<ProdutoDTO> create(@Valid @RequestBody ProdutoDTO obj) {
		obj = new ProdutoDTO(service.create(obj)); // no metodo create ele vai retornar um novo produto, retornando transforma em DTO e retorna o acesso a ela 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping // metodo para atualizar 
	public ResponseEntity<ProdutoDTO> update(@Valid @RequestBody ProdutoDTO obj) {
		obj= new ProdutoDTO(service.update(obj));
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
