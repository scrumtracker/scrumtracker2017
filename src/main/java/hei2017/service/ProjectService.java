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

    List<Project> findAllWithAll();

    Project findOneById(Long id);

    Project findOneByIdWithAll(Long id);

    Project findOneByNom(String nom);

    Project save(Project project);

    Project updateProject(Long idProject, Project project);
}
