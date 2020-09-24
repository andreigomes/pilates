package com.pilates.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pilates.models.Categoria;
import com.pilates.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaController {
	
	private CategoriaService categoriaService;
	
	public CategoriaController(CategoriaService categoriaService) {
		super();
		this.categoriaService = categoriaService;
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?>  findAll() {
		List<Categoria> categoria = categoriaService.findAll();
		return ResponseEntity.ok().body(categoria);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?>  FindById(@PathVariable Integer id) {
		Categoria categoria = categoriaService.findById(id);

		return ResponseEntity.ok().body(categoria);
	}

}
