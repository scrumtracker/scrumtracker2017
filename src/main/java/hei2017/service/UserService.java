package hei2017.service;

import hei2017.entity.User;

import java.util.List;

/**
 * Created by pic on 02/03/2017.
 */
public interface UserService
{
    long count();

    List<User> findAll();

    User findOneByNomAndPrenom(String nom, String prenom);

    User findOneByPseudo(String pseudo);

    void save(User user);
}
