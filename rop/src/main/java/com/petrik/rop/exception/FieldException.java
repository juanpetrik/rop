package com.petrik.rop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FieldException extends RuntimeException {

    public FieldException(String field, String additionalMessage) {
        super(String.format("O campo " + field + " está inválido. %s", additionalMessage));
    }

    public FieldException(String field) {
        super("O campo " + field + " é obrigatório.");
    }
}