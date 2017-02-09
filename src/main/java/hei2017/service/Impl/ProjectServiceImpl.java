package hei2017.service.Impl;

import hei2017.dao.ProjectDAO;
import hei2017.entity.Project;
import hei2017.service.ProjectService;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by pic on 09/02/2017.
 */
public class ProjectServiceImpl implements ProjectService
{
    @Inject
    ProjectDAO projectDAO;

    @Override
    public List<Project> findAll() {
        return projectDAO.findAll();
    }

    @Override
    public Project findOneByNom(String nom) {
        return projectDAO.findOneByNom(nom);
    }

    @Override
    public long count() {
        return projectDAO.count();
    }
}
