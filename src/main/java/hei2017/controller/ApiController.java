package hei2017.controller;

import hei2017.entity.*;
import hei2017.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.security.Timestamp;
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
     * Requêtes DEBUG
     */
    // ***/api/debug ajoute des entités en BDD
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/debug", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public String debug()
    {
        for(int i = 0; i< 10; i++)
        {
            Project p = new Project();
            p.setNom("Projet test "+i);
            p.setDescription(Instant.now().toString());
            projectService.save(p);

            Sprint s = new Sprint();
            s.setNom("Sprint test "+i);
            s.setDescription(Instant.now().toString());
            sprintService.save(s);

            Story st = new Story();
            st.setNom("Story test "+i);
            st.setDescription(Instant.now().toString());
            storyService.save(st);

            Task t = new Task();
            t.setNom("Task test "+i);
            t.setDescription(Instant.now().toString());
            taskService.save(t);

            User u = new User();
            u.setNom("User test "+i);
            u.setPrenom("Yolo"+i);
            userService.save(u);

        }
        LOGGER.debug("ApiController - debugProjects");
        return "10 Projets / sprints / stories / tasks rajoutés en BDD";
    }


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

    /*
     * Requêtes PROJET
     */

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/project", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public List<Project> showProjects()
    {
        LOGGER.debug("ApiController - showProjects");
        return projectService.findAll();
    }

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

    /*
     * Requêtes SPRINT
     */

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/sprint", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public List<Sprint> showSprints()
    {
        LOGGER.debug("ApiController - showSprints");
        return sprintService.findAll();
    }

    @RequestMapping(value = "/api/sprint/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Sprint> sendSprint(@RequestBody Sprint sprint)
    {
        LOGGER.debug("ApiController - sendSprint");
        if(!sprintService.exists(sprint.getNom()))
        {
            sprintService.save(sprint);
            LOGGER.debug("ApiController - sendSprint - Sprint créé");
            return new ResponseEntity<Sprint>(sprint, HttpStatus.CREATED);
        }
        else
        {
            LOGGER.debug("ApiController - sendSprint - Sprint déjà existant");
            return new ResponseEntity<Sprint>(sprint, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/api/sprint/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Sprint> deleteSprint(@PathVariable("id") Long id)
    {
        LOGGER.debug("ApiController - deleteSprint");

        Sprint sprint = null;
        if(sprintService.exists(id))
        {
            sprint = sprintService.findOneById(id);
            sprintService.deleteOneById(id);
            return new ResponseEntity<Sprint>(sprint, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<Sprint>(sprint, HttpStatus.NOT_FOUND);
    }


    /*
     * Requêtes STORY
     */

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/story", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public List<Story> showStories()
    {
        LOGGER.debug("ApiController - showStories");
        return storyService.findAll();
    }

    @RequestMapping(value = "/api/story/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Story> sendStory(@RequestBody Story story)
    {
        LOGGER.debug("ApiController - sendStory");
        if(!storyService.exists(story.getNom()))
        {
            storyService.save(story);
            LOGGER.debug("ApiController - sendStory - Story créé");
            return new ResponseEntity<Story>(story, HttpStatus.CREATED);
        }
        else
        {
            LOGGER.debug("ApiController - sendStory - Story déjà existante");
            return new ResponseEntity<Story>(story, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/api/story/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Story> deleteStory(@PathVariable("id") Long id)
    {
        LOGGER.debug("ApiController - deleteStory");

        Story story = null;
        if(storyService.exists(id))
        {
            story = storyService.findOneById(id);
            storyService.deleteOneById(id);
            return new ResponseEntity<Story>(story, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<Story>(story, HttpStatus.NOT_FOUND);
    }

    /*
     * Requêtes TASK
     */

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/task", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public List<Task> showTasks()
    {
        LOGGER.debug("ApiController - showTasks");
        return taskService.findAll();
    }

    @RequestMapping(value = "/api/task/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Task> sendStory(@RequestBody Task task)
    {
        LOGGER.debug("ApiController - sendTask");
        if(!taskService.exists(task.getNom()))
        {
            taskService.save(task);
            LOGGER.debug("ApiController - sendTask - Task créé");
            return new ResponseEntity<Task>(task, HttpStatus.CREATED);
        }
        else
        {
            LOGGER.debug("ApiController - sendTask - Task déjà existante");
            return new ResponseEntity<Task>(task, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/api/task/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Task> deleteTask(@PathVariable("id") Long id)
    {
        LOGGER.debug("ApiController - deleteTask");

        Task task = null;
        if(taskService.exists(id))
        {
            task = taskService.findOneById(id);
            taskService.deleteOneById(id);
            return new ResponseEntity<Task>(task, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<Task>(task, HttpStatus.NOT_FOUND);
    }
    /*
     * Requêtes USER
     */

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/user", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public List<User> showUsers()
    {
        LOGGER.debug("ApiController - showUsers");
        return userService.findAll();
    }

    @RequestMapping(value = "/api/user/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id)
    {
        LOGGER.debug("ApiController - deleteUser");

        User user = null;
        if(userService.exists(id))
        {
            user = userService.findOneById(id);
            userService.deleteOneById(id);
            return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
    }


}
