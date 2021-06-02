package com.generation.lojaDeGames.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.generation.lojaDeGames.model.Categoria;
import com.generation.lojaDeGames.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class CategoriaController {
	
	@Autowired
	CategoriaRepository repository;
	
	//Crie um findAllCategoria, um endPoint com a capacidade de trazer todas as categorias.
	@GetMapping
	public ResponseEntity<List<Categoria>> todasCategorias(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	//Crie umfindByIDCategoria, um endPoint com a função de trazer uma única categoria por id.
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Categoria>> categoriaId(@PathVariable long id){
		Optional<Categoria> categoria = repository.findById(id);
		System.out.println(categoria);
	    if(categoria.isPresent() == false) { 
	        return ResponseEntity.notFound().build();
	    }
		return ResponseEntity.status(HttpStatus.OK).body(categoria);
		
		/*return repository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());*/
	}

	//Crie umfindByDescricaoCategoria, um endPoint com a função de trazer uma categoria por descricao.
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Categoria>> categoriaPorDescricao (@PathVariable String descricao){
		return ResponseEntity.ok(repository.findAllByDescricaoContaining(descricao));	
	}

	//Crie umpostCategoria, endPoint com a função de gravar uma nova categoria no banco de dados.
	@PostMapping
	public ResponseEntity<Categoria> inserirCategoria (@RequestBody Categoria categoria){
		return ResponseEntity.ok(repository.save(categoria));
		//return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
	}

	//Crie um putCategoria, um endPoint com a função de atualizar dados de uma categoria.
	@PutMapping
	public ResponseEntity<Categoria> alterarCategoria (@RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.OK).body(categoria);
		//return ResponseEntity.ok(repository.save(categoria));				
	}

	//Crie um deleteCategoria, um endPoint com a função de apagar uma categoria do banco de dados).
	@DeleteMapping("/{id}")
	public void deletarCategoria(@PathVariable long id) {
		repository.deleteById(id);
	}

}
