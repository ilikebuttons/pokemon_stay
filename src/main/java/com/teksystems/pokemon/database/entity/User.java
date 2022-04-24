package com.teksystems.pokemon.database.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Transient
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Trainer> trainers;
}