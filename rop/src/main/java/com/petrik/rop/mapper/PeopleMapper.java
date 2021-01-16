package com.petrik.rop.mapper;

import com.petrik.rop.dto.PeopleDTO;
import com.petrik.rop.entity.People;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PeopleMapper {

    private final ModelMapper modelMapper;

    public PeopleDTO convertToDto(People people) {
        PeopleDTO peopleDTO = modelMapper.map(people, PeopleDTO.class);
        return peopleDTO;
    }

    public People convertToEntity(PeopleDTO peopleDTO) {
        People people = modelMapper.map(peopleDTO, People.class);
        people.getContacts().forEach(c -> c.setPeople(people));

        return people;
    }

}
