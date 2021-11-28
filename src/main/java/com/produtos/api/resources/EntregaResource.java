package com.produtos.api.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.produtos.api.dtos.EntregaDTO;
import com.produtos.api.services.EntregaService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/entrega")
public class EntregaResource {

	@Autowired // spring quem vai deletar, salvar, etc ..
	private EntregaService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EntregaDTO> findById(@PathVariable Integer id) {
		EntregaDTO obj = new EntregaDTO(service.findById(id));
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping // traz uma lista de entregas 
	public ResponseEntity<List<EntregaDTO>> findAll() {
		List<EntregaDTO> list = service.findAll().stream().map(obj -> new EntregaDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<EntregaDTO> create(@Valid @RequestBody EntregaDTO obj) {
		obj = new EntregaDTO(service.create(obj));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping
	public ResponseEntity<EntregaDTO> update(@Valid @RequestBody EntregaDTO obj) {
		obj = new EntregaDTO(service.update(obj));
		return ResponseEntity.ok().body(obj);
	}
}
