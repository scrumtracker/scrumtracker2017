package hei2017.controller;

import hei2017.entity.*;
import hei2017.enumeration.StoryStatus;
import hei2017.enumeration.UniteTemps;
import hei2017.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * Created by pic on 16/03/2017.
 */

@EnableWebMvc
@RestController
public class ApiDebugController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    @Inject
    ProjectService projectService;

    @Inject
    SprintService sprintService;

    @Inject
    StoryService storyService;

    @Inject
    TaskService taskService;

    @Inject
    UserService userService;

    /*
     * Requêtes DEBUG
     */

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/test", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public List<Project> test()
    {
        return projectService.findAll();
    }

    // ***/api/debug ajoute des entités en BDD
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/debug", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public String debug()
    {
        //CREATION DES ENTITES
        User testeur = new User();
        testeur.setNom("Teur");
        testeur.setPrenom("Tess");
        testeur.setEmail("tess.teur@da.fr");
        testeur.setPassword("LetMeIn");
        testeur.setPseudo("Mr Motte");

        Task tache = new Task();
        tache.setNom("Créer une tâche via Controlleur "+Instant.now());
        tache.setDescription("Je suis la description de la tâche");
        tache.setTempsDeCharge(new Long(2));
        tache.setUniteTempsDeCharge(UniteTemps.h);

        Story story = new Story();
        story.setNom("Je suis une Story "+Instant.now());
        story.setDescription("Une story correspond à une fonctionnalité attendue par le client");
        story.setStatus(StoryStatus.DOING);

        Sprint sprint = new Sprint();
        sprint.setNom("Sprint test de "+Instant.now());
        sprint.setDescription("Description du sprint");
        sprint.setDateDebut(new Timestamp(System.currentTimeMillis()));
        sprint.setDateFin(new Timestamp(System.currentTimeMillis()));

        Project projet = new Project();
        projet.setNom("Projet test du "+ Instant.now());
        projet.setDescription("Description du "+projet.getNom());

        //AJOUT DES LIAISONS INTER-ENTITES
        projet.addSprint(sprint);
        projet.addUser(testeur);

        sprint.addStory(story);
        story.addTask(tache);
        tache.addUser(testeur);

        projectService.save(projet);
        sprintService.save(sprint);
        storyService.save(story);
        taskService.save(tache);
        userService.save(testeur);

        return "DB peuplée";
    }

    //SUPPRESSION DES ENTITES EN BDD
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/debug/wipe", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public String debugWipe()
    {
        for (Project x:projectService.findAll()) {
            projectService.delete(x);
        }
        for (Sprint x:sprintService.findAll()) {
            sprintService.delete(x);
        }
        for (Story x:storyService.findAll()) {
            storyService.delete(x);
        }
        for (Task x:taskService.findAll()) {
            taskService.delete(x);
        }
        for (User x:userService.findAll()) {
            userService.delete(x);
        }

        return "Reset BDD";
    }

}
