package com.pilates.services.validation;

import com.pilates.controllers.exception.FIeldMessage;
import com.pilates.dto.request.ClienteRequestDTO;
import com.pilates.models.Cliente;
import com.pilates.models.enums.TipoCliente;
import com.pilates.repositories.ClienteRepository;
import com.pilates.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteRequestDTO> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteRequestDTO objDto, ConstraintValidatorContext context) {
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        Integer uriId = 0;
        if (map.size() > 0) {
            uriId = Integer.parseInt(map.get("id"));
        }

        List<FIeldMessage> list = new ArrayList<>();

        switch(objDto.getTipo()) {
            case PESSOAFISICA:
                if (!BR.isValidCPF(objDto.getCpfOuCnpj())) {
                    list.add( new FIeldMessage("cpfOuCnpj", "CPF inválido"));
            }
                break;
            case PESSOAJURIDICA:
                if (!BR.isValidCPF(objDto.getCpfOuCnpj())) {
                    list.add( new FIeldMessage("cpfOuCnpj", "CNPJ inválido"));
                }
                break;
            default:
                list.add( new FIeldMessage("Tipo", "Tipo inválido, informe: PESSOAFISICA ou PESSOAJURIDICA"));
        }

        Cliente cliente = clienteRepository.findByEmail(objDto.getEmail());
        if (cliente != null && !cliente.getId().equals(uriId)) {
            list.add(new FIeldMessage("email", "Email já existente no banco de dados."));
        }

        Cliente cliente2 = clienteRepository.findBycpfOuCnpj(objDto.getCpfOuCnpj());
        if (cliente2 != null && !cliente2.getId().equals(uriId)) {
            list.add(new FIeldMessage("cpfOuCnpj", "CPF/CNPJ já existente no banco de dados."));
        }

        for (FIeldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}