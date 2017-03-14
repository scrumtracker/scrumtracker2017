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

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/sprint/{id}", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public ResponseEntity<Sprint> showSprint(@PathVariable Long id)
    {
        LOGGER.debug("ApiController - showSprint");
        Sprint sprint = sprintService.findOneById(id);
        if(null!=sprint)
            return new ResponseEntity<Sprint>(sprint, HttpStatus.OK);
        return new ResponseEntity<Sprint>(sprint, HttpStatus.NOT_FOUND);
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

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/story/{id}", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public ResponseEntity<Story> showStory(@PathVariable Long id)
    {
        LOGGER.debug("ApiController - showStory");
        Story story = storyService.findOneById(id);
        if(null!=story)
            return new ResponseEntity<Story>(story, HttpStatus.OK);
        return new ResponseEntity<Story>(story, HttpStatus.NOT_FOUND);
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

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/task/{id}", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public ResponseEntity<Task> showTask(@PathVariable Long id)
    {
        LOGGER.debug("ApiController - showTask");
        Task task = taskService.findOneById(id);
        if(null!=task)
            return new ResponseEntity<Task>(task, HttpStatus.OK);
        return new ResponseEntity<Task>(task, HttpStatus.NOT_FOUND);
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

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/user/{id}", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public ResponseEntity<User> showUser(@PathVariable Long id)
    {
        LOGGER.debug("ApiController - showUser");
        User user = userService.findOneById(id);
        if(null!=user)
            return new ResponseEntity<User>(user, HttpStatus.OK);
        return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/api/user/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> sendUser(@RequestBody User user)
    {
        LOGGER.debug("ApiController - sendUser");
        if(!userService.exists(user.getEmail()))
        {
            userService.save(user);
            LOGGER.debug("ApiController - sendUser - User créé");
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        }
        else
        {
            LOGGER.debug("ApiController - sendUser - User déjà existant");
            return new ResponseEntity<User>(user, HttpStatus.CONFLICT);
        }
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
