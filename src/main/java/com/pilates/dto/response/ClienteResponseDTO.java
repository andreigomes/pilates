package com.pilates.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pilates.models.Cliente;
import com.pilates.models.Endereco;
import com.pilates.models.Pedido;
import com.pilates.models.enums.TipoCliente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
public class ClienteResponseDTO {

    private Integer id;
    private String nome;
    private String email;
    private String cpfOuCnpj;
    private TipoCliente tipo;

    private List<Endereco> enderecos = new ArrayList<>();

    private Set<String> telefones = new HashSet<>();

    @JsonIgnore
    private List<Pedido> pedidos = new ArrayList<>();

    public ClienteResponseDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.cpfOuCnpj = cliente.getCpfOuCnpj();
        this.tipo = cliente.getTipo();
        this.enderecos = cliente.getEnderecos();
        this.telefones = cliente.getTelefones();
        this.pedidos = cliente.getPedidos();
    }
}
