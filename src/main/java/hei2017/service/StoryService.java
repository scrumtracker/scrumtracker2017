package hei2017.service;

import hei2017.entity.Story;

import java.util.List;

/**
 * Created by pic on 09/02/2017.
 */
public interface StoryService
{

    Long count();

    void delete(Story story);

    void deleteOneById(Long id);

    Boolean exists(Long id);

    Boolean exists(String nom);

    Story findOneById(Long id);

    List<Story> findAll();

    List<Story> findByStorySprint(Long idSprint);

    Story findOneByNom(String nom);

    void save(Story story);
}
