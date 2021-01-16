package com.petrik.rop.controller;

import com.petrik.rop.dto.PeopleDTO;
import com.petrik.rop.exception.CustomException;
import com.petrik.rop.service.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PeopleController {

    private final PeopleService peopleService;

    @PostMapping(value = "/people", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PeopleDTO> save(@Valid @RequestBody PeopleDTO people) {
        PeopleDTO savedPeople = peopleService.save(people);
        return new ResponseEntity<PeopleDTO>(savedPeople, HttpStatus.CREATED);
    }

    @GetMapping(value = "/people/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PeopleDTO> getById(@PathVariable(value = "id") long id) {
        PeopleDTO peopleDTO = peopleService.findOnePeople(id);
        return new ResponseEntity<PeopleDTO>(peopleDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/people", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Page<PeopleDTO> search(@RequestParam("search") String search,
                               @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                               @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

        return peopleService.search(search, page, size);
    }

    @PutMapping(value = "/people/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PeopleDTO>  update(@PathVariable(value = "id") Long id, @RequestBody PeopleDTO people) throws ParseException {
        if (!id.equals(people.getId())) {
            throw new CustomException("O ID da URI e do body estão diferentes. Por favor verifique e tente novamente.");
        }

        PeopleDTO updatedPeople = peopleService.update(people);
        return new ResponseEntity<PeopleDTO>(updatedPeople, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/people/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        peopleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}