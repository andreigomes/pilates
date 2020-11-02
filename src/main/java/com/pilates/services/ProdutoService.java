package com.pilates.services;

import java.util.List;
import java.util.Optional;

import com.pilates.models.Categoria;
import com.pilates.models.Pedido;
import com.pilates.repositories.CategoriaRepository;
import com.pilates.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pilates.models.Produto;
import com.pilates.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Produto> findAll() {
		List<Produto> produto = produtoRepository.findAll();
		return produto;
	}

	public Produto findById(Integer id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: Produto"));
	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage,
								String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		List<Categoria> categoriaList = categoriaRepository.findAllById(ids);

		return produtoRepository.findDistinctByNameContainingAndCategoriasIn(nome, categoriaList, pageRequest);
	}

}
