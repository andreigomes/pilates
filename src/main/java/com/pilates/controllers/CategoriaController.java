package com.pilates.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pilates.dto.response.CategoriaResponseDTO;
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
	public ResponseEntity<List<CategoriaResponseDTO>>  findAll() {
		List<Categoria> listaCategoria = categoriaService.findAll();
		List<CategoriaResponseDTO> listaCategoriaResponseDTO = listaCategoria.stream().map(obj -> new CategoriaResponseDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaCategoriaResponseDTO);
	}
	
	
	//http://localhost:8080/categorias/page?linesPerPage=3&page=0&direction=DESC
	@RequestMapping(value = "page", method=RequestMethod.GET)
	public ResponseEntity<Page<CategoriaResponseDTO>>  findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Categoria> listaCategoria = categoriaService.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaResponseDTO> listaCategoriaDto = listaCategoria.map(obj -> new CategoriaResponseDTO(obj));
		return ResponseEntity.ok().body(listaCategoriaDto);
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria>  FindById(@PathVariable Integer id) {
		Categoria categoria = categoriaService.findById(id);

		return ResponseEntity.ok().body(categoria);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaResponseDTO categoriaResponseDto) {
		Categoria categoria = categoriaService.fromDto(categoriaResponseDto);
		categoria = categoriaService.insert(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaResponseDTO categoriaResponseDto, @PathVariable Integer id) {
		Categoria categoria = categoriaService.fromDto(categoriaResponseDto);
		categoria.setId(id);
		categoria = categoriaService.update(categoria);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
