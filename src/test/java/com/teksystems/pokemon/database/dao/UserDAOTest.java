package com.teksystems.pokemon.database.dao;

import com.teksystems.pokemon.database.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles({"test", "default"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDAOTest {

    @Autowired
    private UserDAO userDao;
    User user;

    @BeforeEach
    void name() {
        user = new User();
        user.setEmail("alex.daniel.ertl@gmail.com");
        user.setTeamName("Mystic");
        user.setCoins(1000);
        user.setPassword("asdfasdf");
    }

    @Test
    void findById() {
        //user = userDao.findById();
        userDao.save(user);
        assertEquals(user.getEmail(), "alex.daniel.ertl@gmail.com");
    }
}