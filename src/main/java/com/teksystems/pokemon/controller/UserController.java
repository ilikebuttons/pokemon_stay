package com.teksystems.pokemon.controller;

import com.teksystems.pokemon.database.dao.UserRoleDAO;
import com.teksystems.pokemon.database.entity.UserRole;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import java.util.List;

@Slf4j
@Controller
//@PreAuthorize("hasAnyAuthority('PLAYER','ADMIN')")
public class UserController {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private UserRoleDAO userRoleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // LOGIN
    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public ModelAndView login() throws Exception {
        ModelAndView response = new ModelAndView();

        response.setViewName("/user/login");
        return response;
    }

    // REGISTER
    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public ModelAndView register() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/register");

        // Seed with empty form bean so JSP page substitutions will not error
        RegisterFormBean form = new RegisterFormBean();
        response.addObject("form", form);

        return response;
    }

    // REGISTER_SUBMIT
    @RequestMapping(value = "/user/registerSubmit", method = RequestMethod.POST)
    public ModelAndView registerSubmit(@Valid RegisterFormBean form, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info(form.toString());

        if (bindingResult.hasErrors()) {

            //List<String> errorMessages = new ArrayList<>(); //TODO: show errors in JSP

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

        // retreive user by id from hidden form element (edit) //TODO
        User user = userDao.findById(form.getId());

        // if no id found, new user (create)
        if (user == null) {
            user = new User();
        }

        // create and save user
        user.setEmail(form.getEmail());
        user.setTeamName(form.getTeamName());
        user.setCoins(1000);
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        userDao.save(user);

        // create and save the user role object
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setUserRole("PLAYER");
        userRoleDao.save(userRole);

        //TODO autologin after register

        response.setViewName("redirect:/game/");
        return response;
    }

    // EDIT
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/user/edit/{userId}")
    public ModelAndView editUser(@PathVariable("userId") Integer userId) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/register");

        // Populate form using ID
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

    // SEARCH
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/user/search")
    public ModelAndView search(@RequestParam(required = false) String searchTeamName) {
        ModelAndView response = new ModelAndView();

        // Set View
        response.setViewName("user/search"); // Project Directory

        // Create Model
        List<User> users = new ArrayList<>();

        // Validate Input
        if (searchTeamName != null && !searchTeamName.isBlank()) {

            // Query
            users = userDao.findByTeamNameIgnoreCaseContaining(searchTeamName);
        }   //TODO else?

        // Add users to model
        response.addObject("users", users);

        // Add search value to model (so it displays in search box upon reloading)
        response.addObject("searchTeamName", searchTeamName);

        return response;
    }
}