package com.teksystems.pokemon.database.dao;

import com.teksystems.pokemon.database.entity.Pokemon;
import com.teksystems.pokemon.database.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PokemonDAO extends JpaRepository<Pokemon, Long> {
    Pokemon findById(@Param("id") Integer id);

    List<Pokemon> findByTrainerId(@Param("userId") Integer userId);
    //List<Pokemon> findByUserId(@Param("userId") Integer userId);

}
