package com.pilates.dto.response;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pilates.models.Categoria;

public class CategoriaResponseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Size(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	@NotNull
	private String nome;
	
	public CategoriaResponseDTO() {
		
	}
	
	public CategoriaResponseDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
