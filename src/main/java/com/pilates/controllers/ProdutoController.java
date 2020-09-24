package com.pilates.controllers;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pilates.models.Produto;
import com.pilates.services.ProdutoService;

@RestController
@CrossOrigin
@RequestMapping(value="/products")
public class ProdutoController {
	
	private ProdutoService produtoService;
	
	public ProdutoController(ProdutoService produtoService) {
		super();
		this.produtoService = produtoService;
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?>  findAll() {
		List<Produto> produtos = produtoService.findAll();
		return ResponseEntity.ok().body(produtos);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?>  FindById(@PathVariable Integer id) {
		Produto produto = produtoService.findById(id);

		return ResponseEntity.ok().body(produto);
	}

}
