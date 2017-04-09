package hei2017.controller;

import hei2017.entity.Sprint;
import hei2017.enumeration.StoryStatus;
import hei2017.service.ProjectService;
import hei2017.service.SprintService;
import hei2017.service.StoryService;
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

    @RequestMapping("/sprint/{idSprint}")
    public String goSprints(Model model,
                            HttpServletRequest request,
                            HttpServletResponse response,
                            @PathVariable Long idSprint)
    {
        model.addAttribute("isSprintPage", true);

        model.addAttribute("projects", projectService.findAll());

        model.addAttribute("sprints", sprintService.findAll());

        Sprint sprint = sprintService.findOneById(idSprint);

        model.addAttribute("sprintStories", storyService.findByStorySprint(idSprint));

        model.addAttribute("status", StoryStatus.values());

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
