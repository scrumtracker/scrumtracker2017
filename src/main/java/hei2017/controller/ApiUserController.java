package hei2017.controller;

import com.fasterxml.jackson.annotation.JsonView;
import hei2017.entity.Task;
import hei2017.entity.User;
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
 * Created by pic on 19/03/2017.
 */
@EnableWebMvc
@RestController
public class ApiUserController {

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
     * Requêtes USER
     */
    @JsonView(JsonViews.User.class)
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api/user", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    public List<User> showUsers()
    {
        LOGGER.debug("ApiController - showUsers");
        return userService.findAllWithAll();
    }

    @JsonView(JsonViews.User.class)
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

    @JsonView(JsonViews.User.class)
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

    @JsonView(JsonViews.User.class)
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
