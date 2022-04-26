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
@Table(name = "generators")
public class Generator {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)                          Integer id;
    @Column(name = "pokedex", nullable = false, length = 45)        Integer pokedex;
    @Column(name = "min_level", nullable = false, length = 45)      Integer minLevel;
    @Column(name = "max_level", nullable = false)                   Integer maxLevel;
    @Column(name = "distribution", nullable = false)                Double distribution;

/*    @Transient
    @OneToMany(mappedBy = "locationsByLocationId")              Collection<Pokemons> pokemonsById;
    @Transient
    @OneToMany(mappedBy = "locationsByLocationId")              Collection<Trainers> trainersById;*/
}