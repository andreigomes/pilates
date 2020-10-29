package com.pilates.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import com.pilates.dto.request.ClienteRequestDTO;
import com.pilates.dto.response.ClienteResponseDTO;
import com.pilates.models.Cidade;
import com.pilates.models.Endereco;
import com.pilates.models.enums.TipoCliente;
import com.pilates.repositories.EnderecoRepository;
import com.pilates.services.exceptions.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pilates.models.Cliente;
import com.pilates.repositories.ClienteRepository;
import com.pilates.services.exceptions.ObjectNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public List<Cliente> findAll() {
		List<Cliente> categoria = clienteRepository.findAll();
		return categoria;
	}

	public Cliente findById(Integer id) {
		Optional<Cliente> categoria = clienteRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: Cliente"));
	}

	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return clienteRepository.save(cliente);
	}

	public Cliente update(Cliente cliente) {
		Cliente clienteBD = findById(cliente.getId());
		updateData(cliente, clienteBD);
		return clienteRepository.save(clienteBD);
	}

	private void updateData(Cliente cliente, Cliente clienteBD) {
		clienteBD.setNome(cliente.getNome());
		clienteBD.setEmail(cliente.getEmail());
	}

	public void delete(Integer id) {
		findById(id);
		try {
			clienteRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);

	}

	public Cliente fromDto(ClienteResponseDTO clienteResponseDTO) {
		return new Cliente(clienteResponseDTO.getId(), clienteResponseDTO.getNome(), clienteResponseDTO.getEmail(), null, null);
	}

	public Cliente fromDto(ClienteRequestDTO clienteRequestDTO) {
		Cidade cidade = Cidade.
						builder().
						id(clienteRequestDTO.getCidadeId()).
						build();

		Cliente cliente =  Cliente.
				builder().
				id(null).
				nome(clienteRequestDTO.getNome()).
				email(clienteRequestDTO.getEmail()).
				cpfOuCnpj(clienteRequestDTO.getCpfOuCnpj()).
				tipo(clienteRequestDTO.getTipo()).
				enderecos(new ArrayList<>()).
				telefones(new HashSet<>()).
				build();

		Endereco endereco = Endereco.
							builder().
							id(null).
							logradouro(clienteRequestDTO.getLogradouro()).
							numero(clienteRequestDTO.getNumero()).
							complemento(clienteRequestDTO.getComplemento()).
							bairro(clienteRequestDTO.getBairro()).
							cep(clienteRequestDTO.getCep()).
							cidade(cidade).
							cliente(cliente).
							build();

		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(clienteRequestDTO.getTelefone1());

		if (clienteRequestDTO.getTelefone2() != null) {
			cliente.getTelefones().add(clienteRequestDTO.getTelefone2());
		}

		if (clienteRequestDTO.getTelefone3() != null) {
			cliente.getTelefones().add(clienteRequestDTO.getTelefone3());
		}

		return cliente;
	}



}
