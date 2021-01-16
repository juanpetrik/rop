package com.petrik.rop.business;

import com.petrik.rop.dto.ContactDTO;
import com.petrik.rop.dto.PeopleDTO;
import com.petrik.rop.entity.Contact;
import com.petrik.rop.entity.People;
import com.petrik.rop.exception.FieldException;
import com.petrik.rop.exception.GroupException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PeopleBusiness {

    public void validatePeople(PeopleDTO people) {
        if (people.getId() == null) {
            throw new FieldException("ID", "[Dados da Pessoa]");
        }

        if (people.getName() == null || people.getName().isEmpty()) {
            throw new FieldException("Name", "[Dados da Pessoa]");
        }

        if (people.getCpf() == null || people.getCpf().isEmpty()) {
            throw new FieldException("CPF", "[Dados da Pessoa]");
        }

        if (people.getDateOfBirth() == null) {
            throw new FieldException("Date Of Birth", "[Dados da Pessoa]");
        }

        if (people.getDateOfBirth().isAfter(LocalDate.now())) {
            throw new FieldException("Date Of Birth", "[Dados da Pessoa] - A data é posterior ao dia atual.");
        }

        if (people.getContacts() == null || people.getContacts().size() == 0) {
            throw new GroupException("Contacts", "É necessário informar ao menos 1 contato.");
        }

        people.getContacts().forEach(c -> validateContact(c));
    }

    private void validateContact(ContactDTO contact) {
        if (contact.getId() == null) {
            throw new FieldException("ID", "[Dados do Contato]");
        }

        if (contact.getName() == null || contact.getName().isEmpty()) {
            throw new FieldException("Name", "[Dados do Contato]");
        }

        if (contact.getPhone() == null || contact.getPhone().isEmpty()) {
            throw new FieldException("Phone", "[Dados do Contato]");
        }

        if (contact.getEmail() == null || contact.getEmail().isEmpty()) {
            throw new FieldException("E-mail", "[Dados do Contato]");
        }
    }
}
