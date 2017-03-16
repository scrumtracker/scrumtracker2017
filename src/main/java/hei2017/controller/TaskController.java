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
 * Created by pic on 13/02/2017.
 */
@Controller
public class TaskController {

    @Inject
    ProjectService projectService;

    @Inject
    SprintService sprintService;

    @RequestMapping("/tasks")
    public String goTasks(Model model,
                          HttpServletRequest request,
                          HttpServletResponse response)
    {
        model.addAttribute("isTaskPage", true);

        model.addAttribute("projects", projectService.findAll());

        model.addAttribute("sprints", sprintService.findAll());

        return "tasks";
    }

}
