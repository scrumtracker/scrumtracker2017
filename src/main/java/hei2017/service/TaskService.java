package hei2017.service;

import hei2017.entity.Task;

import java.util.List;

/**
 * Created by pic on 09/02/2017.
 */
public interface TaskService
{
    List<Task> findAll();

    Task findOneByNom(String nom);

    long count();

}
