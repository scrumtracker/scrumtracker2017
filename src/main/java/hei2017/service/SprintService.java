package hei2017.service;

import hei2017.entity.Sprint;

import java.util.List;

/**
 * Created by pic on 08/02/2017.
 */
public interface SprintService {

    long countAll();

    void deleteOneById(Long id);

    Boolean exists(Long id);

    Boolean exists(String nom);

    List<Sprint> findAll();

    Sprint findOneById(Long id);

    void save(Sprint sprint);
}
