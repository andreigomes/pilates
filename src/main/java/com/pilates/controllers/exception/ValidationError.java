package com.pilates.controllers.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationError extends StandardError{

    private List<FIeldMessage> errors = new ArrayList<>();

    public void AddError(String fieldName, String message) {
        errors.add(new FIeldMessage(fieldName, message));
    }

    public ValidationError(Integer status, String mensagem, Long timeStamp) {
        super(status, mensagem, timeStamp);
    }
}
