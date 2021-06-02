package com.generation.lojaDeGames.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.generation.lojaDeGames.model.Produto;
import com.generation.lojaDeGames.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin ("*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;

	//Crie um findAllProduto, um endPoint com a capacidade de trazer todos os produtos
	@GetMapping
	public ResponseEntity<List<Produto>> todosProdutos(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	//Crie um findByIDProduto, um endPoint com a função de trazer uma único produto por id.
	@GetMapping("/{id}")
	public ResponseEntity<Produto> produtoId (@PathVariable long id){
		return repository.findById(id).map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.notFound().build());
	}

	//Crie um findByDescricaoTitulo, um endPoint com a função de trazer um único produto pelo nome.
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> produtoPeloNome(@PathVariable String nome){
		return ResponseEntity.status(HttpStatus.OK).body(repository.findAllByNomeContainingIgnoreCase(nome));
	}

	//Crie um postProduto, um endPoint com a função de gravar um novo produto no banco de dados.
	@PostMapping
	public ResponseEntity<Produto> inserirProduto (@RequestBody Produto produto){
		return ResponseEntity.ok(repository.save(produto));
		//return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}

	//Crie um endPoint com a função de atualizar dados de um produto
	@PutMapping
	public ResponseEntity<Produto> alterarCategoria (@RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.OK).body(produto);
		//return ResponseEntity.ok(repository.save(categoria));				
	}

	//Crie um deleteProduto, um endPoint com a função de apagar um Produto do banco de dados).
	@DeleteMapping("/{id}")
	public void deletarCategoria(@PathVariable long id) {
		repository.deleteById(id);
	}

}
