package com.petrik.rop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class GroupException extends RuntimeException {

    public GroupException(String group, String additionalMessage) {
        super(String.format("O Grupo " + group + " está inválido. %s", additionalMessage));
    }

    public GroupException(String group) {
        super("O Grupo " + group + " é obrigatório.");
    }
}