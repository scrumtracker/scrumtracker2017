package hei2017.dao;

import hei2017.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * Created by pic on 02/03/2017.
 */
public interface UserDAO extends JpaRepository<User, Long>
{
    long count();

    User findOneByEmail(String email);

    User findOneByNomAndPrenom(String nom, String prenom);

    User findOneByPseudo(String pseudo);

    Set<User> findByUserTasksId(Long id);

    Set<User> findByUserProjectsId(Long id);

    User findOneById(Long id);
}
