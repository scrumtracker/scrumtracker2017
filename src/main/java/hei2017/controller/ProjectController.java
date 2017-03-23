package hei2017.controller;

import hei2017.entity.Project;
import hei2017.service.ProjectService;
import hei2017.service.SprintService;
import hei2017.service.StoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by pic on 09/02/2017.
 */
@Controller
public class ProjectController {

    @Inject
    ProjectService projectService;

    @Inject
    SprintService sprintService;

    @Inject
    StoryService storyService;

    @RequestMapping("/project/{idProject}")
    public String goProjects(Model model,
                             HttpServletRequest request,
                             HttpServletResponse response, @PathVariable Long idProject)
    {
        model.addAttribute("isProjectPage", true);

        model.addAttribute("projects", projectService.findAll());

        model.addAttribute("sprints", sprintService.findAll());

        model.addAttribute("stories", storyService.findAll());

        //stories non affectées à un sprint
        model.addAttribute("storiesAlone", storyService.findAllWithoutSprint());

        Project project = projectService.findOneById(idProject);

        if(null==project)
        {
            model.addAttribute("errorMessage", "Project #"+idProject+" does not exist.");

            model.addAttribute("isErrorPage", true);

            return "erreur";
        }

        model.addAttribute("project", project);

        model.addAttribute("projectSprints", project.getProjectSprints());

        return "project";
    }

}
