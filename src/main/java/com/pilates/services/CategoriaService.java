package com.pilates.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pilates.models.Categoria;
import com.pilates.repositories.CategoriaRepository;
import com.pilates.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> findAll() {
		List<Categoria> categoria = categoriaRepository.findAll();
		return categoria;
	}

	public Categoria findById(Integer id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: Categoria"));
	}

	public CategoriaService(CategoriaRepository repository) {
		super();
		this.categoriaRepository = repository;
	}
	

}
