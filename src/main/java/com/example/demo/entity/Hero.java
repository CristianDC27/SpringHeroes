package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import javax.persistence.CascadeType;

@Entity
@Table(name = "heroes")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    @OrderColumn
    private List<Superpoder> superpoderes;

    public Hero() {
    }

    public Hero(String name) {
        this.name = name;
        //this.superpoderes = new ArrayList<Superpoder>();
    }

    public Hero(String name, List<Superpoder> superpoderes) {
        this.name = name;
        this.superpoderes = superpoderes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Superpoder> getSuperpoderes() {
        return superpoderes;
    }

    public void setSuperpoderes(List<Superpoder> superpoderes) {
        this.superpoderes = superpoderes;
    }

    @Override
    public String toString() {
        return "Hero [id=" + id + ", name=" + name + superpoderes.toString() + "]";
    }

}