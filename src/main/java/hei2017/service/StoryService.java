package hei2017.service;

import hei2017.entity.Story;

import java.util.List;
import java.util.Set;

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

    Story findOneByIdWithAll(Long id);

    List<Story> findAll();

    List<Story> findAllWithAll();

    Set<Story> findByStorySprint(Long idSprint);

    Story findOneByNom(String nom);

    Story save(Story story);

    List<Story> findAllWithoutSprint();
}
