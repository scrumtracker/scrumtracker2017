package hei2017.service;

import hei2017.entity.User;

import java.util.List;

/**
 * Created by pic on 02/03/2017.
 */
public interface UserService
{
    long count();

    void deleteOneById(Long id);

    Boolean exists(Long id);

    List<User> findAll();

    User findOneById(Long id);

    User findOneByNomAndPrenom(String nom, String prenom);

    User findOneByPseudo(String pseudo);

    void save(User user);
}
