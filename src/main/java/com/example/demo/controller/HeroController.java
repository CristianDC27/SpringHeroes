package com.example.demo.controller;

import com.example.demo.repository.HeroeRepository;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heroes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HeroController {
    @Autowired
    HeroeRepository heroRepo;

    @RequestMapping("")
    public ResponseEntity<Object> findUsers() {
        return ResponseEntity.ok(heroRepo.findAll());
    }

    @RequestMapping("/{name}")
    public ResponseEntity<Object> findUserByName(@PathVariable(value = "name") String name) {
        Hero hero = heroRepo.findByName(name);
        if(hero!=null) {
            return ResponseEntity.ok(hero);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("")
    public Hero newUser(@RequestBody Hero hero) {
        return heroRepo.save(hero);
    }

    @GetMapping("/{id}")
    public Hero one(@PathVariable Long id) {
        return heroRepo.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    Hero replaceUser(@RequestBody Hero newUser, @PathVariable Long id) {
        
        return heroRepo.findById(id)
        .map(hero -> {
            hero.setName(newUser.getName());
            hero.setSuperpoderes(newUser.getSuperpoderes());
            return heroRepo.save(hero);
        })
        .orElseGet(() -> {
            newUser.setId(id);
            return heroRepo.save(newUser);
        });
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        heroRepo.deleteById(id);
    }
}
