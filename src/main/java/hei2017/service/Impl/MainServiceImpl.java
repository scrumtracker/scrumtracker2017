package hei2017.service.Impl;

import hei2017.dao.*;
import hei2017.entity.*;
import hei2017.enumeration.StoryStatus;
import hei2017.service.MainService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by pic on 12/04/2017.
 */
@Named
@Transactional
public class MainServiceImpl implements MainService {
    @Inject
    ProjectDAO projectDAO;

    @Inject
    SprintDAO sprintDAO;

    @Inject
    UserDAO userDAO;

    @Inject
    StoryDAO storyDAO;

    @Inject
    TaskDAO taskDAO;

    String[] adj = {
            "Crazy",
            "Silent",
            "Hard working",
            "Lovely",
            "Cute",
            "The Best",
            "Blind"
    };
    String[] noms = {
            "Jimmie Neutron",
            "Mike from the Goonies",
            "Yoda", "Chris Knight",
            "Smurf",
            "Dada",
            "Harry Potter",
            "Poney",
            "Looney Tunes",
            "Bugs Bunny",
            "Daffy Duck",
            "Forrest Gump"
    };
    String[] desc = {
            "To be or not to be",
            "Yet another funny description. Ah. Ah.",
            "Bazinga",
            "The cake is a lie",
            "42 is the answer",
            "Merry Christmas",
            "Run Forrest, run !",
            "Strange women lying in ponds distributing swords is no basis for a system of government. Supreme executive power derives from a mandate from the masses, not from some farcical aquatic ceremony.",
            "One ring to rule them all, one ring to find them, one ring the bring them all, and in the darkness bind them. In the land of Mordor where the shadows lie.",
            "I’m sorry, Dave. I’m afraid I can’t do that.",
            "Spock. This child is about to wipe out every living thing on Earth. Now, what do you suggest we do….spank it?",
            "With great power there must also come — great responsibility.",
            "Never argue with the data.",
            "Generating random comments",
            "Greetings, programs!",
            "Try not. Do, or do not. There is no try.",
            "This episode was BADLY written!",
            "Goonies never say die.",
            "It’s a moral imperative.",
            "Based on this morning’s reading, it would be a Twinkie thirty-five feet long, weighing approximately six hundred pounds.",
            "Time…to die.",
            "Kneel before Zod.",
            "Ya Ta!"
    };

    @Override
    public void wipe() {
        List<User> users = userDAO.findAll();
        for(User user : users)
        {
            //user.setUserTasks(null);
            //userDAO.save(user);
            userDAO.delete(user);
        }

        List<Task> tasks = taskDAO.findAll();
        for(Task task:tasks){
           // task.setTaskStory(null);
            //task.setTaskUsers(null);
            //taskDAO.save(task);
            taskDAO.delete(task);
        }


        List<Story> stories = storyDAO.findAll();
        for(Story story:stories){
            //story.setStoryTasks(null);
            //storyDAO.save(story);
            storyDAO.delete(story);
        }

        List<Project> projects = projectDAO.findAll();
        for(Project project:projects)
        {
            //project.setProjectSprints(null);
            //projectDAO.save(project);
            projectDAO.delete(project);
        }

        List<Sprint> sprints = sprintDAO.findAll();
        for(Sprint sprint:sprints)
        {
            //sprint.setSprintProject(null);
            //sprint.setSprintStories(null);
            //sprintDAO.save(sprint);
            sprintDAO.delete(sprint);
        }


    }

    @Override
    public void populate() {
        int nbProject = ThreadLocalRandom.current().nextInt(1, 5);
        for(int h=0; h<nbProject;h++)
        {
        Project projet = new Project();
        projet.setNom("Project "+giveName());
        projet.setDescription(giveDescription());
        projet.setDateCreation(new Timestamp(System.currentTimeMillis()));

        projet = projectDAO.save(projet);

        int nbSprint = ThreadLocalRandom.current().nextInt(2, 6);
        for(int i =0; i<nbSprint; i++)
        {
            Sprint sprint = new Sprint();
            sprint.setNom("Sprint "+giveName());
            sprint.setDescription(giveDescription());
            sprint.setDateCreation(new Timestamp(System.currentTimeMillis()));
            sprint.setDateDebut(new Timestamp(System.currentTimeMillis()));
            sprint.setDateFin(new Timestamp(System.currentTimeMillis()));
            sprint.setSprintProject(projet);

            sprint = sprintDAO.save(sprint);

            int nbStory = ThreadLocalRandom.current().nextInt(2, 10);
            for(int j=0;j<nbStory;j++)
            {
                Story story = new Story();
                story.setNom("Story "+giveName());
                story.setDescription(giveDescription());
                story.setDateCreation(new Timestamp(System.currentTimeMillis()));
                story.setStatus(StoryStatus.TODO);
                if(j%2==0)
                    story.setStatus(StoryStatus.DOING);
                if(j%5==0)
                    story.setStatus(StoryStatus.DONE);
                story.setPoints(ThreadLocalRandom.current().nextInt(1, 99));
                story.setStorySprint(sprint);

                story = storyDAO.save(story);
                int nbTask = ThreadLocalRandom.current().nextInt(3, 10);
                for(int k=0; k<nbTask;k++)
                {
                    Task task = new Task();
                    task.setNom("Task "+giveName());
                    task.setDescription(giveDescription());
                    task.setTaskStory(story);
                    task.setStatus(StoryStatus.TODO);
                    if(k%2==0)
                        task.setStatus(StoryStatus.DOING);
                    if(k%5==0)
                        task.setStatus(StoryStatus.DONE);

                    taskDAO.save(task);
                }
            }
        }
        }

    }

    private String giveName()
    {
        return adj[ThreadLocalRandom.current().nextInt(0, adj.length)]+" "+noms[ThreadLocalRandom.current().nextInt(0, noms.length)];
    }

    private String giveDescription()
    {
        return desc[ThreadLocalRandom.current().nextInt(0, desc.length)];
    }
}
