package com.pilates.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pilates.models.Pedido;
import com.pilates.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoController {
	
	private PedidoService pedidoService;
	
	public PedidoController(PedidoService pedidoService) {
		super();
		this.pedidoService = pedidoService;
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?>  findAll() {
		List<Pedido> pedido = pedidoService.findAll();
		return ResponseEntity.ok().body(pedido);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?>  FindById(@PathVariable Integer id) {
		Pedido pedido = pedidoService.findById(id);

		return ResponseEntity.ok().body(pedido);
	}

}
