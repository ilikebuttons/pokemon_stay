package com.teksystems.pokemon.database.dao;

import com.teksystems.pokemon.database.entity.Trainer;
import com.teksystems.pokemon.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TrainerDAO extends JpaRepository<Trainer, Long> {
    Trainer findById(@Param("id") Integer id);

    List<Trainer> findByUserId(@Param("userId") Integer userId);

    List<Trainer> findAllByUserId(Integer userId);

    /*@Query(value="select * from trainers as t where t.user_id != :userID", nativeQuery = true);
    List<Map<String,Object>> getAvailableTrainers(@Param("userId") Integer userId);*/
}