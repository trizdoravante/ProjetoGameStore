package com.generation.lojadegames.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.generation.lojadegames.model.Categoria;
import com.generation.lojadegames.repository.CategoriaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping ("/categorias")
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class CatgoriaController {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getAll(){
		return ResponseEntity.ok(categoriaRepository.findAll());
	}
	@GetMapping ("/{id}")
	public ResponseEntity<Categoria> getById (@PathVariable Long id){
		return categoriaRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	@GetMapping("/NomeCategoria/{nome}")
	public ResponseEntity<List<Categoria>> getByTitle(@PathVariable String nome){
		return ResponseEntity.ok(categoriaRepository.findAllByNomeContainingIgnoreCase(nome));
	}

	@PostMapping
	
	public ResponseEntity<Categoria> post (@Valid @RequestBody Categoria cat){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(categoriaRepository.save(cat));
	}
		
	
	@PutMapping
	public ResponseEntity<Categoria> put (@Valid @RequestBody Categoria cat){
		Optional<Categoria> existingCategoria = categoriaRepository.findById(cat.getId());
		if(existingCategoria.isPresent()) {
			Categoria updatedCategoria = existingCategoria.get();
			updatedCategoria.setDescricao(cat.getDescricao());
			updatedCategoria.setNome(cat.getNome());
			categoriaRepository.save(updatedCategoria);
			return ResponseEntity.ok(updatedCategoria);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
			
			
		}
		@ResponseStatus(HttpStatus.NO_CONTENT)
		@DeleteMapping("/{id}")
		public void delete (@PathVariable Long id) {
			Optional<Categoria> cat = categoriaRepository.findById(id);
			if(cat.isEmpty())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			categoriaRepository.deleteById(id);
		}
		{
	
		
			
		}
	

}

