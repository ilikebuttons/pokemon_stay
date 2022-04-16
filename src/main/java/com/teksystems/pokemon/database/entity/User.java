package com.teksystems.pokemon.database.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
@Table(name = "users")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)                          Integer id;
    @Column(name = "team_name", nullable = false, length = 20)      String teamName;
    @Column(name = "email", nullable = false, length = 45)          String email;
    @Column(name = "password", nullable = false, length = 45)       String password;
    @Column(name = "coins", nullable = false)                       Integer coins;
/*
    @Transient
    @OneToMany(mappedBy = "usersByUserId")                          Collection<Pokemons> pokemonsById;

    @Transient
    @OneToMany(mappedBy = "usersByUserId")                          Collection<Trainers> trainersById;*/
}