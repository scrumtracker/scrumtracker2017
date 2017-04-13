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
    MainService mainService;

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
        mainService.populate();
        return "DB peuplée";
    }

    //SUPPRESSION DES ENTITES EN BDD
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/debug/wipe", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public String debugWipe()
    {
        mainService.wipe();

        return "Reset BDD";
    }

}
