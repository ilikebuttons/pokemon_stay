package com.teksystems.pokemon.database.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
@Table(name = "trainer_pokemon")
public class TrainerPokemon {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)                                              Integer id;
    @Column(name = "bond_level", nullable = false)                                      Integer bondLevel;
    @Column(name = "is_currently_assigned", nullable = false)                           Boolean isCurrentlyAssigned;
    @Column(name = "trainer_id", nullable = false)                                      Integer trainerId;
    @Column(name = "pokemon_id", nullable = false)                                      Integer pokemonId;

    /*@ManyToOne
    @JoinColumn(name = "trainer_id", referencedColumnName = "id", nullable = false)     Trainers trainersByTrainerId;

    @ManyToOne
    @JoinColumn(name = "pokemon_id", referencedColumnName = "id", nullable = false)     Pokemons pokemonsByPokemonId;*/
}

// TODO catalog="" in @Table annotation?