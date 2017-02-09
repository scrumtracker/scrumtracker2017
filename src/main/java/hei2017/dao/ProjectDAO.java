package hei2017.dao;

import hei2017.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by pic on 09/02/2017.
 */
public interface ProjectDAO extends JpaRepository<Project, Long>
{
    List<Project> findAll();

    Project findOneByNom(String nom);

    long count();
}
