package com.generation.lojadegames.controller;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.lojadegames.model.Produto;
import com.generation.lojadegames.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class ProdutoController {
	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id){
		return produtoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	@GetMapping ("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	@PostMapping
	public ResponseEntity<Produto> post (@Valid @RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(produtoRepository.save(produto));
	}
	@PutMapping
	public ResponseEntity<Produto> put (@Valid @RequestBody Produto produto){
		return produtoRepository.findById(produto.getId())
				.map(existingProduto->{
					existingProduto.setNome(produto.getNome());
					existingProduto.setCategoria(produto.getCategoria());
					existingProduto.setDescricao(produto.getDescricao());
					existingProduto.setValor(produto.getValor());
					Produto updatedProduto = produtoRepository.save(existingProduto);
					return ResponseEntity.ok(updatedProduto);
				})
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
				
				
	}
	@ResponseStatus (HttpStatus.NO_CONTENT)
	@DeleteMapping ("/{id}")
	public void delete(@PathVariable Long id) {
		Optional <Produto> produtoOptional = produtoRepository.findById(id);
		if (!produtoOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		produtoRepository.deleteById(id);
	}
	
}
