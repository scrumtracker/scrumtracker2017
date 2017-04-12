package hei2017.dao;

import hei2017.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * Created by pic on 09/02/2017.
 */
public interface ProjectDAO extends JpaRepository<Project, Long>
{
    long count();

    Project findOneById(Long id);

    Project findOneByNom(String nom);

    Set<Project> findByProjectSprintsId(Long id);
}
