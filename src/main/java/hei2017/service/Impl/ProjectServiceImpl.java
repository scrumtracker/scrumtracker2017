package hei2017.service.Impl;

import hei2017.dao.ProjectDAO;
import hei2017.dao.SprintDAO;
import hei2017.dao.UserDAO;
import hei2017.entity.Project;
import hei2017.entity.Sprint;
import hei2017.entity.User;
import hei2017.service.ProjectService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Set;

/**
 * Created by pic on 09/02/2017.
 */
@Named
@Transactional
public class ProjectServiceImpl implements ProjectService
{
    @Inject
    ProjectDAO projectDAO;

    @Override
    public List<Project> findAll() {
        return projectDAO.findAll();
    }

    @Override
    public Project findOneById(Long id) { return projectDAO.findOne(id); }

    @Override
    public Project findOneByNom(String nom) {
        return projectDAO.findOneByNom(nom);
    }

    @Override
    public long count() {
        return projectDAO.count();
    }

    @Override
    public void delete(Project project) { projectDAO.delete(project); }

    @Override
    public Boolean exists(Long id) { return projectDAO.exists(id); }

    @Override
    public void save(Project project) {
        projectDAO.save(project);
    }

    @Override
    public Boolean exists(String nom) {
        if(null!=projectDAO.findOneByNom(nom))
            return true;
        else
            return false;
    }

    @Override
    public void deleteOneById(Long id) { projectDAO.delete(id); }
}
