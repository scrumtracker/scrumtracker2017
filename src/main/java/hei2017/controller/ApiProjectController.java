package hei2017.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import hei2017.entity.Project;
import hei2017.json.JsonViews;
import hei2017.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by pic on 16/03/2017.
 */
@EnableWebMvc
@RestController
public class ApiProjectController {

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
     * Requêtes PROJET
     */
    @JsonView(JsonViews.Project.class)
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/project", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public List<Project> showProjects()
    {
        LOGGER.debug("ApiController - showProjects");
        return projectService.findAllWithAll();
    }

    @JsonView(JsonViews.Project.class)
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/project/{id}", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public ResponseEntity<Project> showProject(@PathVariable Long id)
    {
        LOGGER.debug("ApiController - showProject");
        Project projet = projectService.findOneById(id);
        if(null!=projet)
            return new ResponseEntity<Project>(projet, HttpStatus.OK);
        return new ResponseEntity<Project>(projet, HttpStatus.NOT_FOUND);
    }

    @JsonView(JsonViews.Project.class)
    @RequestMapping(value = "/api/project/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Project> sendProject(@RequestBody Project project)
    {
        LOGGER.debug("ApiController - sendProject");
        if(!projectService.exists(project.getNom()))
        {
            projectService.save(project);
            LOGGER.debug("ApiController - sendProject - Projet créé");
            return new ResponseEntity<Project>(project, HttpStatus.CREATED);
        }
        else
        {
            LOGGER.debug("ApiController - sendProject - Projet déjà existant");
            return new ResponseEntity<Project>(project, HttpStatus.CONFLICT);
        }
    }

    @JsonView(JsonViews.Project.class)
    @RequestMapping(value = "/api/project/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Project> deleteProject(@PathVariable("id") Long id)
    {
        LOGGER.debug("ApiController - deleteProject");

        Project project = null;
        if(projectService.exists(id))
        {
            project = projectService.findOneById(id);
            projectService.deleteOneById(id);
            return new ResponseEntity<Project>(project, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<Project>(project, HttpStatus.NOT_FOUND);
    }
}
