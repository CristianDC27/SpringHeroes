package com.example.demo.repository;

import com.example.demo.entity.Hero;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RepositoryRestResource
public interface HeroeRepository extends CrudRepository<Hero, Long> {
    List<Hero> findByName(@Param("name") String name);
}
