package com.example.demo.repository;

import com.example.demo.entity.Hero;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface HeroeRepository extends CrudRepository<Hero, Long> {

}
