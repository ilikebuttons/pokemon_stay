package com.teksystems.pokemon.database.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
@Table(name = "trainers")
public class Trainer {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)                                  Integer id;
    @Column(name = "name", nullable = false, length = 20)                   String name;
    @Column(name = "image_url", nullable = true, length = 255)              String imageUrl;
    @Column(name = "user_id", nullable = true)                              Integer userId;
    @Column(name = "location_id", nullable = true)                          Integer locationId;
/*
    @Transient
    @OneToMany(mappedBy = "trainersByTrainerId")                            Collection<TrainerPokemon> trainerPokemonsById;

    @Transient
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")              Users usersByUserId;

    @Transient
    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")          Locations locationsByLocationId;*/
}