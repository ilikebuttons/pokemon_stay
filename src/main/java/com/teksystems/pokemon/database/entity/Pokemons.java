package com.teksystems.pokemon.database.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
@Table(name = "pokemons")
public class Pokemons {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)                              Integer id;
    @Column(name = "index", nullable = false)                           Integer index;
    @Column(name = "name", nullable = false, length = 12)               String name;
    @Column(name = "level", nullable = false)                           Integer level;
    @Column(name = "image_url", nullable = true, length = 255)          String imageUrl;
    @Column(name = "location_id", nullable = true)                      Integer locationId;
    @Column(name = "user_id", nullable = true)                          Integer userId;
    /*@Transient
    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")      Locations locationsByLocationId;
    @Transient
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")          Users usersByUserId;
    @OneToMany(mappedBy = "pokemonsByPokemonId")                        Collection<TrainerPokemon> trainerPokemonsById;*/
}
