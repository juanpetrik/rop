package com.petrik.rop.util;

import com.petrik.rop.dto.ContactDTO;
import com.petrik.rop.dto.PeopleDTO;
import com.petrik.rop.entity.Contact;
import com.petrik.rop.entity.People;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PeopleUtil {

    public static PeopleDTO getPeopleDTO(Long id, String name) {
        PeopleDTO peopleDTO = new PeopleDTO();
        peopleDTO.setId(id);
        peopleDTO.setName(name);
        peopleDTO.setCpf("89182402108");
        peopleDTO.setDateOfBirth(LocalDate.parse("1995-01-01"));
        peopleDTO.setContacts(createContactDTOList());
        return peopleDTO;
    }

    private static List<ContactDTO> createContactDTOList() {
        List<ContactDTO> contacts = new ArrayList<>();
        ContactDTO contact = new ContactDTO();
        contact.setId(new Random().nextLong());
        contact.setName("Name Contact");
        contact.setPhone("45999999999");
        contact.setEmail("mail@mail.com");
        contacts.add(contact);
        return contacts;
    }

    public static People getPeopleEntity(String name) {
        People people = new People();
        people.setName(name);
        people.setCpf("89182402108");
        people.setDateOfBirth(LocalDate.parse("1995-01-01"));
        people.setContacts(createContactEntityList(people));
        return people;
    }

    private static List<Contact> createContactEntityList(People people) {
        List<Contact> contacts = new ArrayList<>();
        Contact contact = new Contact();
        contact.setName("Name Contact");
        contact.setPhone("45999999999");
        contact.setEmail("mail@mail.com");
        contact.setPeople(people);
        contacts.add(contact);
        return contacts;
    }
}
