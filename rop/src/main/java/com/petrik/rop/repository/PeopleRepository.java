package com.petrik.rop.repository;

import com.petrik.rop.entity.People;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {

    @Query(value = "select * FROM people p WHERE LOWER(p.name) like %:str% OR LOWER(p.cpf) like %:str%", nativeQuery = true)
    Page<People> search(@Param("str") String str, Pageable pageable);

}