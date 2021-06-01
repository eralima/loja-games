package com.generation.lojaDeGames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

	//Crie um findAllProduto, um endPoint com a capacidade de trazer todos os Produtos
	@GetMapping
	public ResponseEntity<List<Produto>> todosProdutos(){
		return ResponseEntity.ok(repository.findAll());
	}
}
