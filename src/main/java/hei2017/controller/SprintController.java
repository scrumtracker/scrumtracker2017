package hei2017.controller;

import hei2017.entity.Project;
import hei2017.entity.Sprint;
import hei2017.entity.Story;
import hei2017.enumeration.StoryStatus;
import hei2017.service.ProjectService;
import hei2017.service.SprintService;
import hei2017.service.StoryService;
import hei2017.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by pic on 09/02/2017.
 */
@Controller
public class SprintController {

    @Inject
    ProjectService projectService;

    @Inject
    SprintService sprintService;

    @Inject
    StoryService storyService;

    @Inject
    TaskService taskService;

    @RequestMapping("/sprint/{idSprint}")
    public String goSprints(Model model,
                            HttpServletRequest request,
                            HttpServletResponse response,
                            @PathVariable Long idSprint)
    {
        model.addAttribute("isSprintPage", true);

        model.addAttribute("projects", projectService.findAll());

        model.addAttribute("sprints", sprintService.findAll());

        model.addAttribute("tasks", taskService.findAll());

        Sprint sprint = sprintService.findOneById(idSprint);

        model.addAttribute("sprintStories", storyService.findByStorySprintWithTask(idSprint));

        model.addAttribute("status", StoryStatus.values());

        Project project = sprintService.findOneById(idSprint).getSprintProject();

        model.addAttribute("currentProject", project);

        Long idProject = project.getId();

        //Sprints du projet
        model.addAttribute("sprintsOfProjectWithStories", sprintService.findByProjectSprintIdWithStories(idProject));

        if(null==sprint)
        {
            model.addAttribute("errorMessage", "Sprint #"+idSprint+" does not exist.");

            model.addAttribute("isErrorPage", true);

            return "erreur";
        }

        model.addAttribute("sprint", sprint);

        model.addAttribute("sprintProject", sprint.getSprintProject());

        return "sprint";
    }
}
