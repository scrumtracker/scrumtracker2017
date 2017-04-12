package hei2017.service.Impl;

import hei2017.dao.StoryDAO;
import hei2017.dao.TaskDAO;
import hei2017.dao.UserDAO;
import hei2017.entity.Sprint;
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
import java.util.HashSet;
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
            Story taskStory = storyDAO.findByStoryTasksId(task.getId());

            task.setTaskUsers(taskUsers);
            task.setTaskStory(taskStory);
        }
        return tasks;
    }

    @Override
    public Set<Task> findByTaskStory(Long idStory) {
        return taskDAO.findByTaskStoryId(idStory);
    }

    @Override
    public Task findOneById(Long id) { return taskDAO.findOne(id); }

    @Override
    public Task findOneByIdWithAll(Long id) {
        Task task = taskDAO.findOneById(id);
        if(null!=task)
        {
            Set<User> taskUsers = userDAO.findByUserTasksId(task.getId());
            Story taskStory = storyDAO.findByStoryTasksId(task.getId());

            task.setTaskUsers(taskUsers);
            task.setTaskStory(taskStory);
        }
        return task;
    }

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
    public void deleteOneById(Long idTask) {
        taskDAO.delete(idTask);
    }

    @Override
    public Boolean exists(Long id) { return taskDAO.exists(id); }

    @Override
    public Task save(Task task) { return taskDAO.save(task); }

    @Override
    public Boolean exists(String nom) { return null!=taskDAO.findOneByNom(nom); }

    @Override
    public List<Story> findBySprintStoryIdWithTasks(Long idSprint) {
        Set<Story> stories = storyDAO.findByStorySprintId(idSprint);
        List<Story> storiesOfSprint = new ArrayList<>();
        for(Story story : stories)
        {
            Set<Task> storyTasks = taskDAO.findByTaskStoryId(story.getId());
            story.setStoryTasks(storyTasks);
            storiesOfSprint.add(story);
        }
        return storiesOfSprint;
    }
}
