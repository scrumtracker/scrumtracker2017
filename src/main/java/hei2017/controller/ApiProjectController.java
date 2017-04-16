package hei2017.controller;

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
    @JsonView(JsonViews.Basique.class)
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/project", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public List<Project> showProjects()
    {
        LOGGER.debug("ApiController - showProjects");
        return projectService.findAll();
    }

    @JsonView(JsonViews.Project.class)
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/project/details", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public List<Project> showProjectsDetails()
    {
        LOGGER.debug("ApiController - showProjectsDetails");
        return projectService.findAllWithAll();
    }

    @JsonView(JsonViews.Basique.class)
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/project/{id}", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public ResponseEntity<Project> showProject(@PathVariable Long id)
    {
        LOGGER.debug("ApiController - showProject");
        Project project = null;
        if(projectService.exists(id))
        {
            project = projectService.findOneById(id);
            return new ResponseEntity<Project>(project, HttpStatus.OK);
        }
        return new ResponseEntity<Project>(project, HttpStatus.NOT_FOUND);
    }

    @JsonView(JsonViews.Project.class)
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/project/{id}/details", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public ResponseEntity<Project> showProjectWithAll(@PathVariable Long id)
    {
        LOGGER.debug("ApiController - showProjectWithAll");
        Project project = null;
        if(projectService.exists(id))
        {
            project = projectService.findOneByIdWithAll(id);
            return new ResponseEntity<Project>(project, HttpStatus.OK);
        }
        return new ResponseEntity<Project>(project, HttpStatus.NOT_FOUND);
    }

    @JsonView(JsonViews.Basique.class)
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

    @JsonView(JsonViews.Basique.class)
    @RequestMapping(value = "/api/project/update/{idProject}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Project> updateProject(@PathVariable Long idProject, @RequestBody Project project)
    {
        LOGGER.debug("ApiController - updateProject");

        projectService.updateProject(idProject, project);
        LOGGER.debug("ApiController - updateProject - Project maj");
        return new ResponseEntity<Project>(project, HttpStatus.ACCEPTED);
    }

    @JsonView(JsonViews.Basique.class)
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
