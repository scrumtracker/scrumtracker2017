package hei2017.dao;

import hei2017.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by pic on 09/02/2017.
 */
public interface TaskDAO extends JpaRepository<Task, Long>
{
    List<Task> findAll();

    Task findOneByNom(String nom);

    long count();
}
