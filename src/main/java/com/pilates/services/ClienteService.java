package com.pilates.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pilates.models.Cliente;
import com.pilates.repositories.ClienteRepository;
import com.pilates.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	private ClienteRepository clienteRepository;
	
	public List<Cliente> findAll() {
		List<Cliente> categoria = clienteRepository.findAll();
		return categoria;
	}

	public Cliente findById(Integer id) {
		Optional<Cliente> categoria = clienteRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: Cliente"));
	}

	public ClienteService(ClienteRepository repository) {
		super();
		this.clienteRepository = repository;
	}
	

}
