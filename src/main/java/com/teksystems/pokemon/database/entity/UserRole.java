package com.teksystems.pokemon.database.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
@Table(name = "user_roles")
public class UserRole {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")            Integer     id;
    @Column(name = "user_id")       Integer     userId;
    @Column(name = "user_role")     String      userRole;   // ADMIN, PLAYER
}