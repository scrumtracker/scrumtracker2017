package hei.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by thiba on 07/02/2017.
 */
@Controller
public class HomeController {
    @RequestMapping("/*")
    public String goHome(){
        return "Home";
    }
}

