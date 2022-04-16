package com.teksystems.pokemon.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.teksystems.pokemon.database.entity.User;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    User findById(
            @Param("id") Integer id);   // Must be Integer, even though above we have <User, Long>

    User findByEmail(
            @Param("email") String email);

    List<User> findByTeamName(
            @Param("teamName") String teamName);

    List<User> findByTeamNameIgnoreCaseContaining(
            @Param("teamName") String teamName);
}