package hei2017.service.Impl;

import hei2017.dao.TaskDAO;
import hei2017.entity.Task;
import hei2017.service.TaskService;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by pic on 09/02/2017.
 */
public class TaskServiceImpl implements TaskService
{
    @Inject
    TaskDAO taskDAO;

    @Override
    public List<Task> findAll() {
        return taskDAO.findAll();
    }

    @Override
    public Task findOneByNom(String nom) {
        return taskDAO.findOneByNom(nom);
    }

    @Override
    public long count() {
        return taskDAO.count();
    }
}
