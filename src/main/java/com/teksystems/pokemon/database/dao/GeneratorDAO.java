package com.teksystems.pokemon.database.dao;

import com.teksystems.pokemon.database.entity.Generator;
import com.teksystems.pokemon.database.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface GeneratorDAO extends JpaRepository<Generator, Long> {
    Generator findByLocationId(@Param("id") Integer id);
}