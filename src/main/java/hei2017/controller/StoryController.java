package hei2017.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by pic on 09/02/2017.
 */
@Controller
public class StoryController {

    @RequestMapping("/stories")
    public String goStories(Model model,
                            HttpServletRequest request,
                            HttpServletResponse response)
    {
        model.addAttribute("isStoryPage", true);
        return "stories";
    }
}
