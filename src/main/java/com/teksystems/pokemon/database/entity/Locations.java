package com.teksystems.pokemon.database.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
@Table(name = "locations")
public class Locations {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)                      Integer id;
    @Column(name = "name", nullable = false, length = 45)       String name;
    @Column(name = "biome", nullable = false, length = 45)      String biome;
    @Column(name = "difficulty_level", nullable = false)        Integer difficultyLevel;
    @Column(name = "x_coordinate", nullable = false)            Integer xCoordinate;
    @Column(name = "y_coordinate", nullable = false)            Integer yCoordinate;
    @Column(name = "width", nullable = false)                   Integer width;
    @Column(name = "height", nullable = false)                  Integer height;
/*    @Transient
    @OneToMany(mappedBy = "locationsByLocationId")              Collection<Pokemons> pokemonsById;
    @Transient
    @OneToMany(mappedBy = "locationsByLocationId")              Collection<Trainers> trainersById;*/
}
