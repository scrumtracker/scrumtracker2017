package hei2017.service;

import hei2017.entity.Story;
import hei2017.entity.Task;

import java.util.List;
import java.util.Set;

/**
 * Created by pic on 09/02/2017.
 */
public interface TaskService
{

    long count();

    void delete(Task task);

    void deleteOneById(Long id);

    Boolean exists(Long id);

    Boolean exists(String nom);

    List<Task> findAll();

    List<Task> findAllWithAll();

    Set<Task> findByTaskStories(Long idStory);

    Task findOneById(Long id);

    Task findOneByIdWithAll(Long id);

    Task findOneByNom(String nom);

    Task save(Task task);

    List<Story> findBySprintStoryIdWithTasks(Long idSprint);
}
