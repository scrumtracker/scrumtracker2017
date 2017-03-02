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
    public long countAll() {
        return sprintDAO.count();
    }

    @Override
    public void saveSprint(Sprint sprint) {
        sprintDAO.save(sprint);
    }

    @Override
    public Boolean exists(String nom) { return null!=sprintDAO.findOneByNom(nom); }

    @Override
    public void save(Sprint sprint) { sprintDAO.save(sprint); }
}
