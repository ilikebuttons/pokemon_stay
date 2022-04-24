package com.teksystems.pokemon.database.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "locations")
public class Location {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)                          Integer id;
    @Column(name = "name", nullable = false, length = 45)           String name;
    @Column(name = "description", nullable = false, length = 45)    String description;
    @Column(name = "difficulty_level", nullable = false)            Integer difficultyLevel;
    @Column(name = "coordinates")                                   String coordinates;

/*    @Transient
    @OneToMany(mappedBy = "locationsByLocationId")              Collection<Pokemons> pokemonsById;
    @Transient
    @OneToMany(mappedBy = "locationsByLocationId")              Collection<Trainers> trainersById;*/
}