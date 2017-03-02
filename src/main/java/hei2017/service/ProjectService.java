package hei2017.service;

import hei2017.entity.Project;

import java.util.List;

/**
 * Created by pic on 09/02/2017.
 */
public interface ProjectService {

    List<Project> findAll();

    Project findOneByNom(String nom);

    long count();

    void save(Project project);

    Boolean exists(String nom);
}
