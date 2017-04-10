package hei2017.controller;

import com.fasterxml.jackson.annotation.JsonView;
import hei2017.entity.Sprint;
import hei2017.entity.Story;
import hei2017.entity.Task;
import hei2017.entity.User;
import hei2017.enumeration.StoryStatus;
import hei2017.json.JsonViews;
import hei2017.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @JsonView(JsonViews.Basique.class)
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/task", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public List<Task> showTasks()
    {
        LOGGER.debug("ApiController - showTasks");
        return taskService.findAll();
    }


    @JsonView(JsonViews.Task.class)
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/task/details", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public List<Task> showTasksDetails()
    {
        LOGGER.debug("ApiController - showTasksDetails");
        return taskService.findAllWithAll();
    }

    @JsonView(JsonViews.Basique.class)
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/task/{id}", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public ResponseEntity<Task> showTask(@PathVariable Long id)
    {
        LOGGER.debug("ApiController - showTask");
        Task task = null;
        if(taskService.exists(id))
        {
            task = taskService.findOneById(id);
            return new ResponseEntity<Task>(task, HttpStatus.OK);
        }
        return new ResponseEntity<Task>(task, HttpStatus.NOT_FOUND);
    }

    @JsonView(JsonViews.Task.class)
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/task/{id}/details", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public ResponseEntity<Task> showTaskWithAll(@PathVariable Long id)
    {
        LOGGER.debug("ApiController - showTaskWithAll");

        Task task = null;
        if(taskService.exists(id))
        {
            task = taskService.findOneByIdWithAll(id);
            return new ResponseEntity<Task>(task, HttpStatus.OK);
        }
        return new ResponseEntity<Task>(task, HttpStatus.NOT_FOUND);
    }

    @JsonView(JsonViews.Basique.class)
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

    @JsonView(JsonViews.Basique.class)
    @RequestMapping(value = "/api/task/add/story/{idStory}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Task> sendTaskWithStoryId(@PathVariable Long idStory, @RequestBody Task task)
    {
        LOGGER.debug("ApiController - sendTaskWithStoryId");
        Story story = storyService.findOneById(idStory);
        Set<Story> stories = new HashSet<Story>(0);
        stories.add(story);

        if(null==story)
        {
            LOGGER.debug("ApiController - sendTaskWithStoryId - Story inexistante");
            return new ResponseEntity<Task>(task, HttpStatus.NOT_FOUND);
        }
        if(task.getStatus()==null)
            task.setStatus(StoryStatus.TODO);

        task = taskService.save(task);
        task.setTaskStories(stories);
        task = taskService.save(task);

        LOGGER.debug("ApiController - sendTaskWithStoryId - Task créée");
        return new ResponseEntity<Task>(task, HttpStatus.CREATED);
    }

    @JsonView(JsonViews.Basique.class)
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

    //Renvoie toutes les TASKS attachées a la STORY d'id idStory
    @JsonView(JsonViews.Basique.class)
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/task/story/{idStory}", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public Set<Task> showTasksAssociatedToThisStory(@PathVariable Long idStory)
    {
        LOGGER.debug("ApiController - showTasksAssociatedToThisStory");
        Set<Task> tasks = taskService.findByTaskStories(idStory);
        return tasks;
    }
}
