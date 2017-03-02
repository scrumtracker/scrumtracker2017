package hei2017.dao;

import hei2017.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by pic on 09/02/2017.
 */
public interface TaskDAO extends JpaRepository<Task, Long>
{
    Task findOneByNom(String nom);
    long count();
}
