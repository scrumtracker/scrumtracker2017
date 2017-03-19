package hei2017.service.Impl;

import hei2017.dao.SprintDAO;
import hei2017.dao.StoryDAO;
import hei2017.dao.TaskDAO;
import hei2017.entity.Sprint;
import hei2017.entity.Story;
import hei2017.entity.Task;
import hei2017.service.StoryService;
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
public class StoryServiceImpl implements StoryService
{

    @Inject
    SprintDAO sprintDAO;

    @Inject
    StoryDAO storyDAO;

    @Inject
    TaskDAO taskDAO;



    @Override
    public List<Story> findAll() {
        return storyDAO.findAll();
    }

    @Override
    public List<Story> findAllWithAll() {
        List<Story> stories = storyDAO.findAll();
        for(Story story:stories)
        {
            Set<Task> storyTasks = taskDAO.findByTaskStoriesId(story.getId());
            story.setStoryTasks(storyTasks);

            Sprint storySprint = sprintDAO.findBySprintStoriesId(story.getId()).iterator().next();
            story.setStorySprint(storySprint);
        }
        return stories;
    }

    @Override
    public Set<Story> findByStorySprint(Long idSprint) {
        return storyDAO.findByStorySprintId(idSprint);
    }

    @Override
    public Story findOneByNom(String nom) {
        return storyDAO.findOneByNom(nom);
    }

    @Override
    public Long count() {
        return storyDAO.count();
    }

    @Override
    public void delete(Story story) { storyDAO.delete(story); }

    @Override
    public void deleteOneById(Long id) { storyDAO.delete(id); }

    @Override
    public Boolean exists(Long id) { return storyDAO.exists(id); }

    @Override
    public Story save(Story story) { return storyDAO.save(story); }

    @Override
    public Boolean exists(String nom) { return null!=storyDAO.findOneByNom(nom); }

    @Override
    public Story findOneById(Long id) { return storyDAO.findOne(id); }
}
