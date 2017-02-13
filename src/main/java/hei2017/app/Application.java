package hei2017.app;

import hei2017.entity.Sprint;
import hei2017.service.SprintService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.inject.Inject;

/**
 * Created by pic on 08/02/2017.
 */
public class Application {


    public static void main(String[] args)
    {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext("hei2017.config");


        /*
        SprintService sprintService = context.getBean(SprintService.class);
        Sprint sprintTest = new Sprint();
        sprintTest.setNom("test sprint");
        sprintService.saveSprint(sprintTest);
         */
        context.close();
    }



}
