package hei2017.dao;

import hei2017.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by pic on 09/02/2017.
 */
public interface StoryDAO extends JpaRepository<Story, Long>
{
    long count();

    Story findOneByNom(String nom);
}
