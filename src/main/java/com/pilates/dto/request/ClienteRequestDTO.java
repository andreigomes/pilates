package com.pilates.dto.request;

import com.pilates.models.enums.TipoCliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequestDTO {

    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Email inválido")
    private String email;

    private String cpfOuCnpj;

    @NotNull
    private TipoCliente tipo;

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;

    private String telefone1;
    private String telefone2;
    private String telefone3;

    private Integer cidadeId;
}
