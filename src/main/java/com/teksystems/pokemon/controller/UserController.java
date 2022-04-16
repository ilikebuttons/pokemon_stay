package com.teksystems.pokemon.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.teksystems.pokemon.database.dao.UserDAO;
import com.teksystems.pokemon.database.entity.User;
import com.teksystems.pokemon.formbean.RegisterFormBean;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserDAO userDao;

    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public ModelAndView register() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/register");

        // Seed with empty form bean so JSP page substitutions will not error
        RegisterFormBean form = new RegisterFormBean();
        response.addObject("form", form);

        return response;
    }

    @RequestMapping(value = "/user/registerSubmit", method = RequestMethod.POST)
    public ModelAndView registerSubmit(@Valid RegisterFormBean form, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info(form.toString());

        if (bindingResult.hasErrors()) {

            List<String> errorMessages = new ArrayList<>();

            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info( ((FieldError)error).getField() + " " +  error.getDefaultMessage());
            }

            // Keep form filled in
            response.addObject("form", form);

            // Add the error list to model
            response.addObject("bindingResult", bindingResult);

            // Keep user on register page (instead of posting to /user/registerSubmit)
            response.setViewName("user/register");
            return response;
        }


        // try to load user from DB using id on form (edit)
        User user = userDao.findById(form.getId());

        // if no id found, new user (create)
        if (user == null) {
            user = new User();
        }

        // create and save user
        user.setEmail(form.getEmail());
        user.setTeamName(form.getTeamName());
        user.setPassword(form.getPassword());
        user.setCoins(1000);

        userDao.save(user);

        log.info(form.toString());

        // redirect to edit page (redirect: navigates to url instead of view name)
        response.setViewName("redirect:/user/edit/" + user.getId());

        return response;
    }

    // Edit user, populate form using ID
    // @RequestMapping(value = "/user/edit/{userId}", method = {RequestMethod.GET, RequestMethod.POST})
    @GetMapping(value = "/user/edit/{userId}")
    public ModelAndView editUser(@PathVariable("userId") Integer userId) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/register");

        User user = userDao.findById(userId);
        RegisterFormBean form = new RegisterFormBean();

        form.setId(user.getId());
        form.setEmail(user.getEmail());
        form.setTeamName(user.getTeamName());
        form.setPassword(user.getPassword());
        form.setConfirmPassword(user.getPassword());

        response.addObject("form", form);

        return response;
    }

    @GetMapping("/user/search")
    public ModelAndView search(@RequestParam(required = false) String searchTeamName) {
        ModelAndView response = new ModelAndView();

        // Set the View
        response.setViewName("user/search"); // Project Directory

        // Create the Model
        List<User> users = new ArrayList<>();

        // Validate Input
        if (searchTeamName != null && !searchTeamName.isBlank()) {

            // Query
            users = userDao.findByTeamNameIgnoreCaseContaining(searchTeamName);
        }

        // Add users to model
        response.addObject("users", users);

        // Add search value to model (so it displays in search box upon reloading)
        response.addObject("searchTeamName", searchTeamName);

        return response;
    }
}