package com.pilates.services.validation;

import com.pilates.controllers.exception.FIeldMessage;
import com.pilates.dto.request.ClienteRequestDTO;
import com.pilates.models.enums.TipoCliente;
import com.pilates.services.validation.utils.BR;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteRequestDTO> {

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteRequestDTO objDto, ConstraintValidatorContext context) {
        List<FIeldMessage> list = new ArrayList<>();

        if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
            list.add( new FIeldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
            list.add( new FIeldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        for (FIeldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}