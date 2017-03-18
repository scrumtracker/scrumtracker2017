package hei2017.controller;

import hei2017.entity.Task;
import hei2017.entity.User;
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
public class ApiTaskController {

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

    //Renvoie toutes les TASKS attachées a la STORY d'id idStory
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/task/story/{idStory}", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public List<Task> showTasksAssociatedToThisStory(@PathVariable Long idStory)
    {
        LOGGER.debug("ApiController - showTasksAssociatedToThisStory");
        List<Task> tasks = taskService.findByTaskStories(idStory);
        return tasks;
    }


}
