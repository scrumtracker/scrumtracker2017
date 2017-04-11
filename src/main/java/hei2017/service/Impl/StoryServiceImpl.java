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
import java.util.ArrayList;
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
    public Story updateStory(Long id, Story story) {
        Story storyAUpdater = storyDAO.findOneById(id);
        storyAUpdater.setNom(story.getNom());
        storyAUpdater.setDescription(story.getDescription());
        storyAUpdater.setPoints(story.getPoints());
        storyAUpdater.setStatus(story.getStatus());
        storyAUpdater.setStorySprint(story.getStorySprint());
        storyAUpdater.setStoryTasks(story.getStoryTasks());

        return storyDAO.save(storyAUpdater);
    }

    //Stories non affectées à un sprint
    @Override
    public List<Story> findAllWithoutSprint() {
        List<Story> listStoriesWithoutSprint = new ArrayList<>();
        List<Story> listStories = storyDAO.findAll();
        if(listStories != null){
            for(Story story : listStories){
                if(story.getStorySprint() == null)
                    listStoriesWithoutSprint.add(story);
            }
        }
        return listStoriesWithoutSprint;
    }

    @Override
    public Set<Story> findByStorySprintWithTask(Long idSprint) {
        Set<Story> stories = findByStorySprint(idSprint);
        if(stories!=null)
        {
            for(Story story:stories){
                Set<Task> tasks = taskDAO.findByTaskStoriesId(story.getId());
                story.setStoryTasks(tasks);
            }
        }
        return stories;
    }

    @Override
    public Boolean exists(String nom) { return null!=storyDAO.findOneByNom(nom); }

    @Override
    public Story findOneById(Long id) { return storyDAO.findOne(id); }

    @Override
    public Story findOneByIdWithAll(Long id) {
        Story story = storyDAO.findOneById(id);
        if(null!=story)
        {
            Set<Task> storyTasks = taskDAO.findByTaskStoriesId(story.getId());
            story.setStoryTasks(storyTasks);

            Sprint storySprint = sprintDAO.findBySprintStoriesId(story.getId()).iterator().next();
            story.setStorySprint(storySprint);
        }
        return story;
    }
}
