package hei2017.dao;

import hei2017.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by pic on 02/03/2017.
 */
public interface UserDAO extends JpaRepository<User, Long>
{
    long count();

    User findOneByNomAndPrenom(String nom, String prenom);

    User findOneByPseudo(String pseudo);
}
