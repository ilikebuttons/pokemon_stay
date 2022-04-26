package com.teksystems.pokemon.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)                                  Integer id;
    @Column(name = "name", nullable = false, length = 20)                   String name;
    @Column(name = "description", nullable = false, length = 100)           String description;
    @Column(name = "price")                                                 Integer price;
    @Column(name = "max_quantity", nullable = false)                        Integer maxQuantity;
    @Column(name = "image_url", length = 255)                               String imageUrl;
/*
    //@Transient
    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Inventory> inventories;
    */
}