package com.teksystems.pokemon.database.dao;

import com.teksystems.pokemon.database.entity.Location;
import com.teksystems.pokemon.database.entity.TrainerLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainerLocationDAO extends JpaRepository<TrainerLocation, Long> {
    //List<TrainerLocation> findByUserId(@Param("userId") Integer id);

    TrainerLocation findById(@Param("id") Integer id);

    TrainerLocation findByTrainerId(Integer trainer_id);

    //List<TrainerLocation> findByUserId(Integer id);
}