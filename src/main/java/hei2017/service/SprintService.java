package hei2017.service;

import hei2017.entity.Sprint;

import java.util.List;

/**
 * Created by pic on 08/02/2017.
 */
public interface SprintService {

    List<Sprint> findAll();

    long countAll();

    void saveSprint(Sprint sprint);

    void save(Sprint sprint);

    Boolean exists(String nom);
}
