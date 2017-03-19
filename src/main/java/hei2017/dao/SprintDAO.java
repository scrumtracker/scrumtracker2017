package hei2017.dao;

import hei2017.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

/**
 * Created by pic on 08/02/2017.
 */
public interface SprintDAO extends JpaRepository<Sprint, Long>
{
    long count();

    Sprint findOneByNom(String nom);

    Set<Sprint> findBySprintStoriesId(Long id);

    Set<Sprint> findBySprintProjectId(Long idProject);
}
