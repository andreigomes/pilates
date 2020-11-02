package com.pilates.controllers;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.pilates.controllers.Utils.URL;
import com.pilates.dto.response.ClienteResponseDTO;
import com.pilates.dto.response.ProdutoResponseDTO;
import com.pilates.models.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	//http://localhost:8080/products/page/?nome=a&categorias=1
	@RequestMapping(value = "page", method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoResponseDTO>>  findPage(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) throws UnsupportedEncodingException {

		String nomeDecoded = URL.decodeParam(nome);
		Page<Produto> listaProdutos = produtoService.search(nomeDecoded, URL.converterIds(categorias), page, linesPerPage, orderBy, direction);
		Page<ProdutoResponseDTO> listaProdutosResponseDTO = listaProdutos.map(produto -> new ProdutoResponseDTO(produto));
		return ResponseEntity.ok().body(listaProdutosResponseDTO);
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
