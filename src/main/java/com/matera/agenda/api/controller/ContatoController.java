package com.matera.agenda.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.matera.agenda.domain.exception.UsuarioNaoEncontradoException;
import com.matera.agenda.domain.model.Contato;
import com.matera.agenda.domain.repository.ContatoRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/contatos")
public class ContatoController {

	@Autowired
	private ContatoRepository contatoRepository;

	// Read
	@GetMapping
	public List<Contato> listar() {
		return contatoRepository.findAll();
	}

	// Created
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Contato adicionar(@Valid @RequestBody Contato contato) {
		return contatoRepository.save(contato);
	}
	
	//buscar por ID
	@GetMapping("/{contatoId}")
	public ResponseEntity<Contato> buscar(@PathVariable Long contatoId) {
		return contatoRepository.findById(contatoId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());	
	}
	
	//Alterar por ID
	@PutMapping("/{contatoId}")
	public ResponseEntity<Contato> atualizar(@PathVariable Long contatoId, 
			@Valid @RequestBody Contato contato) {
		if(!contatoRepository.existsById(contatoId)) {
			return ResponseEntity.notFound().build();
		}
		
		contato.setId(contatoId);
		contato = contatoRepository.save(contato); 
				
		return ResponseEntity.ok(contato);
	}

	//Delete
	@DeleteMapping("/{contatoId}")
	public ResponseEntity<Void> remover(@PathVariable Long contatoId) {
		if (!contatoRepository.existsById(contatoId)) {
			//return ResponseEntity.notFound().build();
			throw new UsuarioNaoEncontradoException(contatoId);
		}

		contatoRepository.deleteById(contatoId);

		return ResponseEntity.noContent().build();
	}

}
