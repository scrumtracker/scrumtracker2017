package hei2017.service.Impl;

import hei2017.dao.SprintDAO;
import hei2017.entity.Sprint;
import hei2017.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by pic on 08/02/2017.
 */
@Named
@Transactional
public class SprintServiceImpl implements SprintService {

    @Inject
    SprintDAO sprintDAO;

    @Override
    public List<Sprint> findAll() {
        return sprintDAO.findAll();
    }

    @Override
    public List<Sprint> findBySprintProject(Long idProject) {
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
