package com.pilates.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.pilates.dto.request.ClienteRequestDTO;
import com.pilates.dto.response.CategoriaResponseDTO;
import com.pilates.dto.response.ClienteResponseDTO;
import com.pilates.models.Categoria;
import com.pilates.models.Cliente;
import com.pilates.services.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value="/clientes")
public class ClienteController {

	private ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		super();
		this.clienteService = clienteService;
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteResponseDTO>>  findAll() {
		List<Cliente> listaCliente = clienteService.findAll();
		List<ClienteResponseDTO> listaCategoriaDto = listaCliente.stream().map(obj -> new ClienteResponseDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaCategoriaDto);
	}


	//http://localhost:8080/categorias/page?linesPerPage=3&page=0&direction=DESC
	@RequestMapping(value = "page", method=RequestMethod.GET)
	public ResponseEntity<Page<ClienteResponseDTO>>  findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Cliente> listaCliente = clienteService.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteResponseDTO> listaClienteDto = listaCliente.map(obj -> new ClienteResponseDTO(obj));
		return ResponseEntity.ok().body(listaClienteDto);
	}


	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cliente>  FindById(@PathVariable Integer id) {
		Cliente cliente = clienteService.findById(id);

		return ResponseEntity.ok().body(cliente);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
		Cliente cliente = clienteService.fromDto(clienteRequestDTO);
		cliente = clienteService.insert(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO, @PathVariable Integer id) {
		Cliente cliente = clienteService.fromDto(clienteRequestDTO);
		cliente.setId(id);
		clienteService.update(cliente);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
