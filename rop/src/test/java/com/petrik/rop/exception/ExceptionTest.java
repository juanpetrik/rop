package com.petrik.rop.exception;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExceptionTest {

    @Test
    public void whenFieldException1Parameter_thenCorrect() {
        assertTrue(new FieldException("MyField").getMessage().equals("O campo MyField é obrigatório."));
    }

    @Test
    public void whenFieldException2Parameter_thenCorrect() {
        assertTrue(new FieldException("MyField", "MyMessage").getMessage().equals("O campo MyField está inválido. MyMessage"));
    }

    @Test
    public void whenGroupException1Parameter_thenCorrect() {
        assertTrue(new GroupException("MyGroup").getMessage().equals("O Grupo MyGroup é obrigatório."));
    }

    @Test
    public void whenGroupException2Parameter_thenCorrect() {
        assertTrue(new GroupException("MyGroup", "MyMessage").getMessage().equals("O Grupo MyGroup está inválido. MyMessage"));
    }

    @Test
    public void whenCustomException_thenCorrect() {
        assertTrue(new CustomException("O ID da URI e do body estão diferentes. Por favor verifique e tente novamente.").getMessage()
                .equals("O ID da URI e do body estão diferentes. Por favor verifique e tente novamente."));
    }
}
