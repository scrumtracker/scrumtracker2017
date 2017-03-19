package hei2017.service.Impl;

import hei2017.dao.ProjectDAO;
import hei2017.dao.SprintDAO;
import hei2017.dao.StoryDAO;
import hei2017.entity.Project;
import hei2017.entity.Sprint;
import hei2017.entity.Story;
import hei2017.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Set;

/**
 * Created by pic on 08/02/2017.
 */
@Named
@Transactional
public class SprintServiceImpl implements SprintService {

    @Inject
    ProjectDAO projectDAO;

    @Inject
    SprintDAO sprintDAO;

    @Inject
    StoryDAO storyDAO;

    @Override
    public List<Sprint> findAll() {
        return sprintDAO.findAll();
    }

    @Override
    public List<Sprint> findAllWithAll() {
        List<Sprint> sprints = sprintDAO.findAll();
        for(Sprint sprint : sprints)
        {
            Set<Story> sprintStories = storyDAO.findByStorySprintId(sprint.getId());
            sprint.setSprintStories(sprintStories);

            Project sprintProject = projectDAO.findByProjectSprintsId(sprint.getId()).iterator().next();
            sprint.setSprintProject(sprintProject);
        }
        return sprints;
    }

    @Override
    public Set<Sprint> findBySprintProject(Long idProject) {
        return sprintDAO.findBySprintProjectId(idProject);
    }

    @Override
    public Sprint findOneById(Long id) { return sprintDAO.findOne(id); }

    @Override
    public long countAll() {
        return sprintDAO.count();
    }

    @Override
    public void delete(Sprint sprint) { sprintDAO.delete(sprint); }

    @Override
    public void deleteOneById(Long id) { sprintDAO.delete(id); }

    @Override
    public Boolean exists(Long id) { return sprintDAO.exists(id); }

    @Override
    public Boolean exists(String nom) { return null!=sprintDAO.findOneByNom(nom); }

    @Override
    public void save(Sprint sprint) { sprintDAO.save(sprint); }
}
