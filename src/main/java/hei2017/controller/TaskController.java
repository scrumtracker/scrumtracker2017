package hei2017.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by pic on 13/02/2017.
 */
@Controller
public class TaskController {

    @RequestMapping("/tasks")
    public String goTasks(Model model,
                          HttpServletRequest request,
                          HttpServletResponse response)
    {
        model.addAttribute("isTaskPage", true);

        return "tasks";
    }

}
