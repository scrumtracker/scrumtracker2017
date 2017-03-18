package hei2017.dao;

import hei2017.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by pic on 09/02/2017.
 */
public interface TaskDAO extends JpaRepository<Task, Long>
{
    long count();

    List<Task> findByTaskStoriesId(Long idStory);

    Task findOneByNom(String nom);
}
