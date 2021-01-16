package com.petrik.rop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PeopleNotFoundException extends RuntimeException {

    public PeopleNotFoundException(Long idPeople) {
        super("Pessoa com id " + Long.valueOf(idPeople) + " não encontrada");
    }
}