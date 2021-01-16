package com.petrik.rop.business;

import com.petrik.rop.business.PeopleBusiness;
import com.petrik.rop.dto.PeopleDTO;
import com.petrik.rop.exception.FieldException;
import com.petrik.rop.exception.GroupException;
import com.petrik.rop.util.ExceptionUtils;
import com.petrik.rop.util.PeopleUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PeopleBusinessTest {

    private static String NAME = "Name";
    
    @Autowired
    private PeopleBusiness peopleBusiness;

    @Test
    public void whenSavePeopleWithoutID_thenException() {
        FieldException fieldException = assertThrows(FieldException.class, () -> {
            PeopleDTO peopleDTO = PeopleUtil.getPeopleDTO(1L, NAME);
            peopleDTO.setId(null);
            peopleBusiness.validatePeople(peopleDTO);
        });

        assertEquals(ExceptionUtils.getFieldException("ID", "[Dados da Pessoa]"), fieldException.getMessage());
    }

    @Test
    public void whenSavePeopleWithoutName_thenException() {
        FieldException fieldException = assertThrows(FieldException.class, () -> {
            PeopleDTO peopleDTO = PeopleUtil.getPeopleDTO(1L, NAME);
            peopleDTO.setName(null);
            peopleBusiness.validatePeople(peopleDTO);
        });

        assertEquals(ExceptionUtils.getFieldException("Name", "[Dados da Pessoa]"), fieldException.getMessage());
    }

    @Test
    public void whenSavePeopleWithoutCPF_thenException() {
        FieldException fieldException = assertThrows(FieldException.class, () -> {
            PeopleDTO peopleDTO = PeopleUtil.getPeopleDTO(1L, NAME);
            peopleDTO.setCpf(null);
            peopleBusiness.validatePeople(peopleDTO);
        });

        assertEquals(ExceptionUtils.getFieldException("CPF", "[Dados da Pessoa]"), fieldException.getMessage());
    }

    @Test
    public void whenSavePeopleWithoutDateOfBirth_thenException() {
        FieldException fieldException = assertThrows(FieldException.class, () -> {
            PeopleDTO peopleDTO = PeopleUtil.getPeopleDTO(1L, NAME);
            peopleDTO.setDateOfBirth(null);
            peopleBusiness.validatePeople(peopleDTO);
        });

        assertEquals(ExceptionUtils.getFieldException("Date Of Birth", "[Dados da Pessoa]"), fieldException.getMessage());
    }

    @Test
    public void whenSavePeopleWithDateOfBirthAfterActualDate_thenException() {
        FieldException fieldException = assertThrows(FieldException.class, () -> {
            PeopleDTO peopleDTO = PeopleUtil.getPeopleDTO(1L, NAME);
            peopleDTO.setDateOfBirth(LocalDate.now().plusDays(1));
            peopleBusiness.validatePeople(peopleDTO);
        });

        assertEquals(ExceptionUtils.getFieldException("Date Of Birth", "[Dados da Pessoa] - A data é posterior ao dia atual."), fieldException.getMessage());
    }

    @Test
    public void whenSavePeopleWithContactsNull_thenException() {
        GroupException fieldException = assertThrows(GroupException.class, () -> {
            PeopleDTO peopleDTO = PeopleUtil.getPeopleDTO(1L, NAME);
            peopleDTO.setContacts(null);
            peopleBusiness.validatePeople(peopleDTO);
        });

        assertEquals(ExceptionUtils.getGroupException("Contacts", "É necessário informar ao menos 1 contato."), fieldException.getMessage());
    }

    @Test
    public void whenSavePeopleWithContactsWithoutID_thenException() {
        FieldException fieldException = assertThrows(FieldException.class, () -> {
            PeopleDTO peopleDTO = PeopleUtil.getPeopleDTO(1L, NAME);
            peopleDTO.getContacts().get(0).setId(null);
            peopleBusiness.validatePeople(peopleDTO);
        });

        assertEquals(ExceptionUtils.getFieldException("ID", "[Dados do Contato]"), fieldException.getMessage());
    }

    @Test
    public void whenSavePeopleWithContactsWithoutName_thenException() {
        FieldException fieldException = assertThrows(FieldException.class, () -> {
            PeopleDTO peopleDTO = PeopleUtil.getPeopleDTO(1L, NAME);
            peopleDTO.getContacts().get(0).setName(null);
            peopleBusiness.validatePeople(peopleDTO);
        });

        assertEquals(ExceptionUtils.getFieldException("Name", "[Dados do Contato]"), fieldException.getMessage());
    }

    @Test
    public void whenSavePeopleWithContactsWithoutPhone_thenException() {
        FieldException fieldException = assertThrows(FieldException.class, () -> {
            PeopleDTO peopleDTO = PeopleUtil.getPeopleDTO(1L, NAME);
            peopleDTO.getContacts().get(0).setPhone(null);
            peopleBusiness.validatePeople(peopleDTO);
        });

        assertEquals(ExceptionUtils.getFieldException("Phone", "[Dados do Contato]"), fieldException.getMessage());
    }

    @Test
    public void whenSavePeopleWithContactsWithoutEmail_thenException() {
        FieldException fieldException = assertThrows(FieldException.class, () -> {
            PeopleDTO peopleDTO = PeopleUtil.getPeopleDTO(1L, NAME);
            peopleDTO.getContacts().get(0).setEmail(null);
            peopleBusiness.validatePeople(peopleDTO);
        });

        assertEquals(ExceptionUtils.getFieldException("E-mail", "[Dados do Contato]"), fieldException.getMessage());
    }
}