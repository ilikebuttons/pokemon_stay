package com.teksystems.pokemon.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pokemons")
public class Pokemon {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)                              Integer id;
    @Column(name = "index", nullable = false)                           Integer index;
    @Column(name = "name", nullable = false, length = 12)               String name;
    @Column(name = "level", nullable = false)                           Integer level;
    @Column(name = "location_id", nullable = true)                      Integer locationId;
    @Column(name = "user_id", nullable = true)                          Integer userId;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trainer_id", nullable = false)
    Trainer trainer;
}