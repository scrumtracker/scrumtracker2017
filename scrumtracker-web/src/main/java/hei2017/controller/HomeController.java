package hei2017.controller;

import hei2017.entity.Sprint;
import hei2017.service.SprintService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Timestamp;
import java.util.Date;

/**
 * Created by pic on 08/02/2017.
 */
@Controller
public class HomeController {

    @Inject
    SprintService sprintService;

    @RequestMapping({"/*","/home"})
    public String goHome(Model model, HttpServletRequest request, HttpServletResponse reponse)
    {
        Sprint sprintTest = new Sprint();
        sprintTest.setNom("Hello World d'un Sprint");

        model.addAttribute("ObjetTest", sprintTest);

        return "pageDeTest";
    }

}
