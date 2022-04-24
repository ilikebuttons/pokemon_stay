package com.teksystems.pokemon.controller;

import com.teksystems.pokemon.database.dao.UserDAO;
import com.teksystems.pokemon.database.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class GameController {

    @Autowired
    private UserDAO userDao;

    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView response = new ModelAndView();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userDao.findByEmail(currentPrincipalName);

        if ( user == null ) {
            log.info("Not logged in");
            response.setViewName("login/login");
            return response;
        }

        log.info("User logged in: " + user);
        response.addObject("teamName", user.getTeamName());
        response.addObject("coins", user.getCoins().toString());

        // Return something here?

        response.setViewName("game/game");
        return response;
    }
}