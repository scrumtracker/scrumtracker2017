package hei2017.controller;

import com.fasterxml.jackson.annotation.JsonView;
import hei2017.entity.Story;
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
import java.util.Set;

/**
 * Created by pic on 16/03/2017.
 */

@EnableWebMvc
@RestController
public class ApiStoryController {

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



}
