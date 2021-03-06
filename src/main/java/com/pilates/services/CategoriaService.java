package com.pilates.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.pilates.dto.response.CategoriaResponseDTO;
import com.pilates.models.Categoria;
import com.pilates.repositories.CategoriaRepository;
import com.pilates.services.exceptions.DataIntegrityException;
import com.pilates.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
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
	
	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}
	
	public Categoria update(Categoria categoria) {
		Categoria categoriaDB = findById(categoria.getId());
		updateData(categoria, categoriaDB);
		return categoriaRepository.save(categoriaDB);
	}

	private void updateData(Categoria categoria, Categoria categoriaDB) {
		categoriaDB.setNome(categoria.getNome());
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			categoriaRepository.deleteById(id);
		} 
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);
		
	}
	
	public Categoria fromDto(CategoriaResponseDTO categoriaResponseDto) {
		return new Categoria(categoriaResponseDto.getId(), categoriaResponseDto.getNome());
	}

}
