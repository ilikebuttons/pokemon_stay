package com.teksystems.pokemon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teksystems.pokemon.database.dao.TrainerDAO;
import com.teksystems.pokemon.database.entity.Trainer;
import com.teksystems.pokemon.database.entity.User;
import com.teksystems.pokemon.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class TrainerController {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private UserService userService;

    @Autowired
    private TrainerDAO trainerDao;

    @RequestMapping(value = "/game/trainers", method = RequestMethod.GET)
    public ModelAndView create() throws Exception {
        //List<Trainer> trainers = trainerDao.getAvailableTrainers();

        Integer userId = userService.getCurrentUser().getId();

        List<Trainer> trainers = trainerDao.findAll();
        List<Trainer> availableTrainers = new ArrayList<>();

        for (Trainer trainer : trainers) {
            if (trainer.getUser() == null) {
                availableTrainers.add(trainer);
                continue;
            }
            if (trainer.getUser().getId() != userId) {
                availableTrainers.add(trainer);
            }
        };

        ModelAndView response = new ModelAndView();
        response.addObject("availableTrainers", availableTrainers);
        response.setViewName("game/trainers");
        return response;
    }

    @RequestMapping(value = "/game/trainers/create", method = RequestMethod.POST)
    public ModelAndView submitTrainer(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info("name: " + name + "\nuploaded file = " + file.getOriginalFilename() + " size = " + file.getSize());

        String imgUrl = "c:/dev/uploads/trainers/" + name + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        File targetFile = new File(imgUrl);

        log.info(imgUrl);

        FileUtils.copyInputStreamToFile(file.getInputStream(), targetFile);

        // Create & Save Trainer
        Trainer trainer = new Trainer();
        trainer.setName(name);
        trainer.setImageUrl(imgUrl);
        trainerDao.save(trainer);

        response.setViewName("redirect:/game/trainers");
        return response;
    }

    @RequestMapping(value = "/getTrainers", method = RequestMethod.GET)
    public ResponseEntity<String> getTrainers() throws Exception {
        User user = userService.getCurrentUser();

        if (user == null) {         // TODO use preauth instead
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        List<Trainer> trainers = trainerDao.findByUserId(user.getId());
        String payload = mapper.writeValueAsString(trainers);

        log.info(payload);
        return new ResponseEntity(payload, HttpStatus.OK);
    }

    @RequestMapping(value = "/addToTeam", method = RequestMethod.POST)
    public ModelAndView addToTeam(@RequestParam("selectedTrainerId") Integer selectedTrainerId) throws Exception {
        User user = userService.getCurrentUser();

        Trainer trainer = trainerDao.findById(selectedTrainerId);
        trainer.setUser(user);

        trainerDao.save(trainer);
        log.info(trainer.getName() + " assigned to Team " + user.getTeamName());

        ModelAndView response = new ModelAndView();
        response.setViewName("redirect:/game");
        return response;
    }
}