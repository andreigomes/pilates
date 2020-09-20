package com.pilates.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pilates.model.Categoria;
import com.pilates.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	private CategoriaRepository categoriaRepository;
	
	public Categoria findOne(Integer id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return categoria.orElse(null);
	}

	public CategoriaService(CategoriaRepository repository) {
		super();
		this.categoriaRepository = repository;
	}
	

}
