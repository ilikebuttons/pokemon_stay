package com.teksystems.pokemon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teksystems.pokemon.database.dao.LocationDAO;
import com.teksystems.pokemon.database.dao.PokemonDAO;
import com.teksystems.pokemon.database.dao.TrainerDAO;
import com.teksystems.pokemon.database.dao.TrainerLocationDAO;
import com.teksystems.pokemon.database.entity.Location;
import com.teksystems.pokemon.database.entity.Trainer;
import com.teksystems.pokemon.database.entity.TrainerLocation;
import com.teksystems.pokemon.database.entity.User;
import com.teksystems.pokemon.formbean.TrainerLocationBean;
import com.teksystems.pokemon.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
//@PreAuthorize("hasAuthority('PLAYER')") //TODO
@PreAuthorize("hasAnyAuthority('PLAYER','ADMIN')")
public class TrainerLocationController {
    private static final ObjectMapper mapper = new ObjectMapper();

    /*@Autowired
    private UserService userService;*/

    @Autowired
    private TrainerDAO trainerDao;

    @Autowired
    private TrainerLocationDAO trainerLocationDao;

    @Autowired
    private LocationDAO locationDao;

    @Autowired
    private PokemonDAO pokemonDao;

    @RequestMapping(value = "game/getTrainerLocations", method = RequestMethod.GET)
    public ResponseEntity<String> getTrainerLocations() throws Exception {
        //User user = userService.getCurrentUser();

        /*List<TrainerLocation> trainerLocations = trainerLocationDao.findByUserId(user.getId());*/
        List<TrainerLocation> trainerLocations = trainerLocationDao.findAll();
        String payload = mapper.writeValueAsString(trainerLocations);

        return new ResponseEntity(payload, HttpStatus.OK);
    }

    @RequestMapping(value = "/game/assignSubmit", method = RequestMethod.POST)
    public ResponseEntity<String> assignSubmit(
            @RequestParam Integer trainerId,
            @RequestParam Integer locationId,
            @RequestParam Integer pokeballs,
            @RequestParam Integer potions) throws Exception {

        List<String> errors = new ArrayList<>();

        Location location = locationDao.findById(locationId);
        // TODO verify trainer has strong enough pokemon

        Trainer trainer = trainerDao.findById(trainerId);
        if (trainer.getPokemons().isEmpty()) {
            errors.add("Are you nuts!? There are wild pokemon out there! Assign at least one pokemon to this trainer.");
        }

        TrainerLocation currentAssignments = trainerLocationDao.findByTrainerId(trainerId);
        if (currentAssignments != null) {
            errors.add("This trainer is already on adventure!");
            //TODO give specifics
        }

        /*if (trainer.getLocationId() != null) {
            //TrainerLocation test = trainerLocationDao.findByTrainerId(trainer);   //TODO give specifics
            errors.add("This trainer is already on adventure!");
        }*/

        if (pokeballs < 1) {
            errors.add("Must equip trainer with at least one pokeball.");
            //TODO could send out just to gain exp?
        }

        if (errors.size() > 0) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(mapper.writeValueAsString(errors));
        }

        // Create & Save Assignment
        TrainerLocation tl = new TrainerLocation();
        tl.setStatus("exploring");                          //TODO en route
        tl.setProgress(1.0);
        tl.setTrainer(trainer);
        tl.setLocation(location);
        tl.setPokeballs(pokeballs);
        tl.setPotions(potions);

        trainerLocationDao.save(tl);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.writeValueAsString(tl));
    }
}