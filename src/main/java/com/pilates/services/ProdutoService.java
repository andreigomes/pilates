package com.pilates.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pilates.models.Produto;
import com.pilates.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	private ProdutoRepository produtoRepository;
	
	public List<Produto> findAll() {
		List<Produto> produto = produtoRepository.findAll();
		return produto;
	}

	
	public Produto findById(Integer id) {
		Optional<Produto> categoria = produtoRepository.findById(id);
		return categoria.orElse(null);
	}

	public ProdutoService(ProdutoRepository repository) {
		super();
		this.produtoRepository = repository;
	}
	

}
