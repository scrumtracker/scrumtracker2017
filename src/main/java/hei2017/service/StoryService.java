package hei2017.service;

import hei2017.entity.Story;

import java.util.List;

/**
 * Created by pic on 09/02/2017.
 */
public interface StoryService
{

    List<Story> findAll();

    Story findOneByNom(String nom);

    Long count();

    void save(Story story);

    Boolean exists(String nom);
}
