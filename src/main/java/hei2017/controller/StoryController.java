package hei2017.controller;

import hei2017.service.ProjectService;
import hei2017.service.SprintService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by pic on 09/02/2017.
 */
@Controller
public class StoryController {

    @Inject
    ProjectService projectService;

    @Inject
    SprintService sprintService;

    @RequestMapping("/stories")
    public String goStories(Model model,
                            HttpServletRequest request,
                            HttpServletResponse response)
    {
        model.addAttribute("isStoryPage", true);

        model.addAttribute("projects", projectService.findAll());

        model.addAttribute("sprints", sprintService.findAll());

        return "stories";
    }
}
