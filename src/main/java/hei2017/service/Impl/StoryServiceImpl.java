package hei2017.service.Impl;

import hei2017.dao.StoryDAO;
import hei2017.entity.Story;
import hei2017.service.StoryService;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by pic on 09/02/2017.
 */
public class StoryServiceImpl implements StoryService
{
    @Inject
    StoryDAO storyDAO;

    @Override
    public List<Story> findAll() {
        return storyDAO.findAll();
    }

    @Override
    public Story findOneByNom(String nom) {
        return storyDAO.findOneByNom(nom);
    }

    @Override
    public Long count() {
        return storyDAO.count();
    }
}
