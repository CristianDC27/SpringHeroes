package com.example.demo.controller;

import com.example.demo.repository.HeroeRepository;

import java.util.List;

import com.example.demo.entity.Hero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heroes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HeroController {
    @Autowired
    HeroeRepository heroRepo;

   /*  @RequestMapping("")
    public ResponseEntity<Object> findUsers() {
        return ResponseEntity.ok(heroRepo.findAll());
    } */

    @RequestMapping("")
    public ResponseEntity<Object> findUserByName(@RequestParam(name = "name", required = false) String name) {
        if (name == null){
            return ResponseEntity.ok(heroRepo.findAll());
        }
        List<Hero> hero = heroRepo.findByName(name);
        if(hero!=null) {
            return ResponseEntity.ok(hero);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("")
    public Hero newHero(@RequestBody Hero hero) {
        return heroRepo.save(hero);
    }

    @GetMapping("/{id}")
    public Hero one(@PathVariable Long id) {
        return heroRepo.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    Hero replaceUser(@RequestBody Hero newHero, @PathVariable Long id) {
        
        return heroRepo.findById(id)
        .map(hero -> {
            hero.setName(newHero.getName());
            hero.setSuperpoderes(newHero.getSuperpoderes());
            return heroRepo.save(hero);
        })
        .orElseGet(() -> {
            newHero.setId(id);
            return heroRepo.save(newHero);
        });
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        heroRepo.deleteById(id);
    }
}
