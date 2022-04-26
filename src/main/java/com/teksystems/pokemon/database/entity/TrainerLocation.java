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
@Table(name = "trainer_locations")
public class TrainerLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)                  Integer id;
    @Column(name = "status", nullable = false)              String status;
    @Column(name = "progress", nullable = true)             Double progress;
    @Column(name = "pokeballs", nullable = true)            Integer pokeballs;
    @Column(name = "potions", nullable = true)              Integer potions;
    //@Column(name = "location_id", nullable = false, insertable = false, updatable = false)         Integer locaitonId;

    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    Trainer trainer;

    @JsonIgnore         // error at json charcter 8
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    Location location;
}