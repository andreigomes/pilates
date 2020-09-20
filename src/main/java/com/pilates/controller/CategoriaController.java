package com.pilates.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pilates.model.Categoria;
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
	public List<Categoria>  findAll() {
		
		Categoria categoria1 = new Categoria(1, "Informática");
		Categoria categoria2 = new Categoria(1, "Escritório");
		
		List<Categoria> lista = new ArrayList<>();
		lista.add(categoria1);
		lista.add(categoria2);
		
		return lista;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?>  FindById(@PathVariable Integer id) {
		Categoria categoria = categoriaService.findOne(id);

		return ResponseEntity.ok().body(categoria);
	}

}
