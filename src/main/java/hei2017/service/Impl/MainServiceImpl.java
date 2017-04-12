package hei2017.service.Impl;

import hei2017.dao.*;
import hei2017.entity.*;
import hei2017.service.MainService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by pic on 12/04/2017.
 */
@Named
@Transactional
public class MainServiceImpl implements MainService {
    @Inject
    ProjectDAO projectDAO;

    @Inject
    SprintDAO sprintDAO;

    @Inject
    UserDAO userDAO;

    @Inject
    StoryDAO storyDAO;

    @Inject
    TaskDAO taskDAO;

    @Override
    public void wipe() {
        List<User> users = userDAO.findAll();
        for(User user : users)
        {
            user.setUserTasks(null);
            userDAO.save(user);
            userDAO.delete(user);
        }

        List<Task> tasks = taskDAO.findAll();
        for(Task task:tasks){
            task.setTaskStory(null);
            task.setTaskUsers(null);
            taskDAO.save(task);
            taskDAO.delete(task);
        }


        List<Story> stories = storyDAO.findAll();
        for(Story story:stories){
            story.setStoryTasks(null);
            storyDAO.save(story);
            storyDAO.delete(story);
        }

        List<Sprint> sprints = sprintDAO.findAll();
        for(Sprint sprint:sprints)
        {
            sprint.setSprintProject(null);
            sprint.setSprintStories(null);
            sprintDAO.save(sprint);
            sprintDAO.delete(sprint);
        }

        List<Project> projects = projectDAO.findAll();
        for(Project project:projects)
        {
            project.setProjectSprints(null);
            projectDAO.save(project);
            projectDAO.delete(project);
        }
    }
}
