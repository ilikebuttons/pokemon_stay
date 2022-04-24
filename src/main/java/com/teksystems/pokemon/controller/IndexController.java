package com.teksystems.pokemon.controller;

import com.teksystems.pokemon.database.dao.UserDAO;
import com.teksystems.pokemon.database.entity.User;
import com.teksystems.pokemon.service.UserService;
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
public class IndexController {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView rootToIndex() {
        ModelAndView response = new ModelAndView();
        response.setViewName("redirect:/index");
        return response;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView response = new ModelAndView();

        response.setViewName("index");

        return response;
    }
}