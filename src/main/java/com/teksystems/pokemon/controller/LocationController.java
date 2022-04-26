package com.teksystems.pokemon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teksystems.pokemon.database.dao.LocationDAO;
import com.teksystems.pokemon.database.entity.User;
import com.teksystems.pokemon.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.teksystems.pokemon.database.entity.Location;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class LocationController {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private LocationService locationService;

    @Autowired
    private LocationDAO locationDao;

    @RequestMapping(value = "/getMap", method = RequestMethod.GET)
    public ResponseEntity<String> getMap() throws Exception {
        List<Location> locations = locationDao.findAll();

        String payload = mapper.writeValueAsString(locations);

        return new ResponseEntity(payload, HttpStatus.OK);
    }

    @RequestMapping(value = "/getDistance", method = RequestMethod.GET)
    public ResponseEntity<String> getDistance(
            @RequestParam Integer fromId,
            @RequestParam Integer toId) throws Exception {

        String payload = mapper.writeValueAsString(locationService.calculateDistance(fromId, toId));

        return new ResponseEntity(payload, HttpStatus.OK);
    }

    // SEARCH
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/locations")
    public ModelAndView search(@RequestParam(required = false) String searchTeamName) {
        ModelAndView response = new ModelAndView();

        // Set View
        response.setViewName("admin/locations"); // Project Directory

        // Create Model
        List<Location> locations = new ArrayList<>();
        locations = locationDao.findAll();

        log.info(locations.toString());

        // Add users to model
        response.addObject("locations", locations);

        return response;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/generator/{locationId}")
    //public ModelAndView editUser(@RequestParam("userId") Integer userId) throws Exception {
    public ModelAndView generator(
                @PathVariable("id") Integer id,
                @PathVariable("pokedex") Integer pokedex,
                @PathVariable("minLevel") Integer minLevel,
                @PathVariable("maxLevel") Integer maxLevel,
                @PathVariable("distribution") Double distribution) {

        ModelAndView response = new ModelAndView();
        response.setViewName("admin/generator");
/*

        Location user = userDao.findById(userId);

        // in this case we are adding the RegisterFormBean to the model
        response.addObject("form", form);
*/
        return response;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/generator/submit", method = RequestMethod.GET)
    public ModelAndView editGenerator(
            @RequestParam Integer id,
            @RequestParam Integer pokedex,
            @RequestParam Integer minLevel,
            @RequestParam Integer maxLevel,
            @RequestParam Double distribution) {

        ModelAndView response = new ModelAndView();
        response.setViewName("admin/generator");
/*

        Location user = userDao.findById(userId);

        // in this case we are adding the RegisterFormBean to the model
        response.addObject("form", form);
*/

        return response;
    }
}