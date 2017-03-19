package hei2017.service.Impl;

import hei2017.dao.StoryDAO;
import hei2017.dao.TaskDAO;
import hei2017.dao.UserDAO;
import hei2017.entity.Story;
import hei2017.entity.Task;
import hei2017.entity.User;
import hei2017.service.TaskService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by pic on 09/02/2017.
 */
@Named
@Transactional
public class TaskServiceImpl implements TaskService
{

    @Inject
    StoryDAO storyDAO;

    @Inject
    TaskDAO taskDAO;

    @Inject
    UserDAO userDAO;

    @Override
    public List<Task> findAll() {
        return taskDAO.findAll();
    }

    @Override
    public List<Task> findAllWithAll() {
        List<Task> tasks = taskDAO.findAll();
        for(Task task: tasks)
        {
            Set<User> taskUsers = userDAO.findByUserTasksId(task.getId());
            Set<Story> taskStories = storyDAO.findByStoryTasksId(task.getId());

            task.setTaskUsers(taskUsers);
            task.setTaskStories(taskStories);
        }
        return tasks;
    }

    @Override
    public Set<Task> findByTaskStories(Long idStory) {
        return taskDAO.findByTaskStoriesId(idStory);
    }

    @Override
    public Task findOneById(Long id) { return taskDAO.findOne(id); }

    @Override
    public Task findOneByNom(String nom) {
        return taskDAO.findOneByNom(nom);
    }

    @Override
    public long count() {
        return taskDAO.count();
    }

    @Override
    public void delete(Task task) { taskDAO.delete(task); }

    @Override
    public void deleteOneById(Long id) { taskDAO.delete(id); }

    @Override
    public Boolean exists(Long id) { return taskDAO.exists(id); }

    @Override
    public void save(Task task) { taskDAO.save(task); }

    @Override
    public Boolean exists(String nom) { return null!=taskDAO.findOneByNom(nom); }
}
