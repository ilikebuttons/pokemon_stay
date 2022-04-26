package com.teksystems.pokemon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teksystems.pokemon.database.dao.LocationDAO;
import com.teksystems.pokemon.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.teksystems.pokemon.database.entity.Location;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
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
}