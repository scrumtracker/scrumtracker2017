package hei2017.service.Impl;

import hei2017.dao.ProjectDAO;
import hei2017.dao.SprintDAO;
import hei2017.dao.UserDAO;
import hei2017.entity.Project;
import hei2017.entity.Sprint;
import hei2017.service.ProjectService;
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

    @Inject
    SprintDAO sprintDAO;

    @Inject
    UserDAO userDAO;

    @Override
    public List<Project> findAll() {
        return projectDAO.findAll();
    }

    @Override
    public List<Project> findAllWithAll() {
        List<Project> projects = projectDAO.findAll();
        for(Project project:projects)
        {
            Set<Sprint> projectSprints = sprintDAO.findBySprintProjectId(project.getId());
            project.setProjectSprints(projectSprints);
        }
        return projects;
    }

    @Override
    public Project findOneById(Long id) { return projectDAO.findOne(id); }

    @Override
    public Project findOneByIdWithAll(Long id) {
        Project project = projectDAO.findOneById(id);
        if(null!=project)
        {
            Set<Sprint> projectSprints = sprintDAO.findBySprintProjectId(project.getId());
            project.setProjectSprints(projectSprints);
        }
        return project;
    }

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
    public Project save(Project project) { return projectDAO.save(project); }

    @Override
    public Boolean exists(String nom) {
        if(null!=projectDAO.findOneByNom(nom))
            return true;
        else
            return false;
    }

    @Override
    public void deleteOneById(Long id) { projectDAO.delete(id); }

    @Override
    public Project updateProject(Long idProject, Project project) {
        Project projectAUpdater = projectDAO.findOneById(idProject);
        projectAUpdater.setNom(project.getNom());
        projectAUpdater.setDescription(project.getDescription());

        return projectDAO.save(projectAUpdater);
    }
}
