package com.pilates.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pilates.models.Cliente;
import com.pilates.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteController {
	
	private ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
		super();
		this.clienteService = clienteService;
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?>  findAll() {
		List<Cliente> cliente = clienteService.findAll();
		return ResponseEntity.ok().body(cliente);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?>  FindById(@PathVariable Integer id) {
		Cliente cliente = clienteService.findById(id);

		return ResponseEntity.ok().body(cliente);
	}

}
