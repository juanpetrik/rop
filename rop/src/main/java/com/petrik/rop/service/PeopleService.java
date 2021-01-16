package com.petrik.rop.service;

import com.petrik.rop.business.PeopleBusiness;
import com.petrik.rop.dto.PeopleDTO;
import com.petrik.rop.entity.People;
import com.petrik.rop.exception.PeopleNotFoundException;
import com.petrik.rop.mapper.PeopleMapper;
import com.petrik.rop.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class PeopleService {

    private final PeopleRepository peopleRepository;
    private final PeopleMapper peopleMapper;
    private final PeopleBusiness peopleBusiness;

    public PeopleDTO save(PeopleDTO peopleDTO) {
        peopleBusiness.validatePeople(peopleDTO);

        People people = peopleMapper.convertToEntity(peopleDTO);
        People savedPeople = peopleRepository.save(people);
        return peopleMapper.convertToDto(savedPeople);
    }

    public PeopleDTO findOnePeople(Long id) {
        Optional<People> people = findById(id);
        return peopleMapper.convertToDto(people.get());
    }

    private Optional<People> findById(Long id) {
        return Optional.ofNullable(peopleRepository.findById(id)
                .orElseThrow(() -> new PeopleNotFoundException(id)));
    }

    public void delete(Long id) {
        Optional<People> people = findById(id);

        if (people.isPresent()) {
            peopleRepository.delete(people.get());
        }
    }

    public PeopleDTO update(PeopleDTO peopleDTO) {
        Optional<People> oldPeople = Optional.ofNullable(peopleRepository.findById(peopleDTO.getId())
                .orElseThrow(() -> new PeopleNotFoundException(peopleDTO.getId())));

        People people = peopleMapper.convertToEntity(peopleDTO);
        people.setId(oldPeople.get().getId());
        People savedPeople = peopleRepository.save(people);
        return peopleMapper.convertToDto(savedPeople);
    }

    public Page<PeopleDTO> search(String search, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "name");

        Page<People> entities = peopleRepository.search(search != null ? search.toLowerCase() : "", pageRequest);

        Page<PeopleDTO> dtoPage = entities.map(new Function<People, PeopleDTO>() {

            @Override
            public PeopleDTO apply(People people) {
                return peopleMapper.convertToDto(people);
            }
        });

        return dtoPage;
    }
}
