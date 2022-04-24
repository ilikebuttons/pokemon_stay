package com.teksystems.pokemon.database.dao;

import com.teksystems.pokemon.database.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface LocationDAO extends JpaRepository<Location, Long> {
    Location findById(@Param("id") Integer id);
}