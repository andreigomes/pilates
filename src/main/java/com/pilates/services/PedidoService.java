package com.pilates.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pilates.models.Pedido;
import com.pilates.repositories.PedidoRepository;
import com.pilates.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	private PedidoRepository pedidoRepository;
	
	public List<Pedido> findAll() {
		List<Pedido> pedido = pedidoRepository.findAll();
		return pedido;
	}

	public Pedido findById(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: Cliente"));
	}

	public PedidoService(PedidoRepository repository) {
		super();
		this.pedidoRepository = repository;
	}
	

}
