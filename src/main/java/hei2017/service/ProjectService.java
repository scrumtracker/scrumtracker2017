package hei2017.service;

import hei2017.entity.Project;

import java.util.List;

/**
 * Created by pic on 09/02/2017.
 */
public interface ProjectService
{

    long count();

    void delete(Project project);

    Boolean exists(Long id);

    Boolean exists(String nom);

    void deleteOneById(Long id);

    List<Project> findAll();

    Project findOneById(Long id);

    Project findOneByNom(String nom);

    void save(Project project);
}
