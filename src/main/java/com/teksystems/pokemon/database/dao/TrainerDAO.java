package com.teksystems.pokemon.database.dao;

import com.teksystems.pokemon.database.entity.Trainer;
import com.teksystems.pokemon.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerDAO extends JpaRepository<Trainer, Long> {
    Trainer findById(@Param("id") Integer id);

    List<Trainer> findByUserId(@Param("userId") Integer userId);
}