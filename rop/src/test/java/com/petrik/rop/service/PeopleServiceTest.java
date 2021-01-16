package com.petrik.rop.service;

import com.petrik.rop.dto.PeopleDTO;
import com.petrik.rop.exception.FieldException;
import com.petrik.rop.exception.GroupException;
import com.petrik.rop.exception.PeopleNotFoundException;
import com.petrik.rop.util.ExceptionUtils;
import com.petrik.rop.util.PeopleUtil;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.temporal.Temporal;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PeopleServiceTest {

    public static final String ALEX = "Alex";
    public static final String RENATA = "Renata";
    public static final String ALEX_CHANGED = "Alex Alterado";

    @Autowired
    private PeopleService peopleService;

    @Test
    public void whenSavePeople_thenCorrect() {
        PeopleDTO result = peopleService.save(PeopleUtil.getPeopleDTO(1L, ALEX));
        assertTrue(result.getName().equals(ALEX));
    }

    @Test
    public void whenSaveAndFindPeople_thenCorrect() {
        PeopleDTO resultSave = peopleService.save(PeopleUtil.getPeopleDTO(1L, ALEX));
        PeopleDTO resultFind = peopleService.findOnePeople(resultSave.getId());
        assertTrue(resultFind.getName().equals(ALEX));
    }

    @Test(expected = PeopleNotFoundException.class)
    public void whenSaveAndDeletePeople_thenCorrect() {
        PeopleDTO resultSave = peopleService.save(PeopleUtil.getPeopleDTO(1L, ALEX));
        peopleService.delete(resultSave.getId());
        peopleService.findOnePeople(resultSave.getId());
    }

    @Test
    public void whenSaveTwoPeople_thenCorrect() {
        PeopleDTO resultAlex = peopleService.save(PeopleUtil.getPeopleDTO(1L, ALEX));
        PeopleDTO resultRenata = peopleService.save(PeopleUtil.getPeopleDTO(2L, RENATA));
        Page<PeopleDTO> search = peopleService.search("", 0, 10);

        assertTrue(search.getTotalElements() == 2L);
        Assertions.assertTrue(search.getContent().get(0).getName().equals(resultAlex.getName()));
        Assertions.assertTrue(search.getContent().get(1).getName().equals(resultRenata.getName()));
    }

    @Test
    public void whenSavePeopleAndUpdate_thenCorrect() {
        PeopleDTO resultSave = peopleService.save(PeopleUtil.getPeopleDTO(1L, ALEX));
        resultSave.setName(ALEX_CHANGED);
        PeopleDTO resultUpdate = peopleService.update(resultSave);

        assertTrue(resultUpdate.getName().equals(ALEX_CHANGED));
    }
}