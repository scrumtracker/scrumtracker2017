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
 * Created by pic on 02/03/2017.
 */

@EnableWebMvc
@RestController
public class ApiController {

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
     * Requêtes générales
     */


    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @RequestMapping(value="/api/*", produces = "application/json")
    public String showErreur(HttpServletRequest request)
    {
        LOGGER.error("RestAPIController - Requête vers l'api incorrecte. \n"+request.toString());
        return "Demande incorrecte";
    }


}
