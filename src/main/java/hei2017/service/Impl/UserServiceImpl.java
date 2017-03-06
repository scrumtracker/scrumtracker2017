package hei2017.service.Impl;

import hei2017.dao.UserDAO;
import hei2017.entity.User;
import hei2017.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by pic on 02/03/2017.
 */
@Named
@Transactional
public class UserServiceImpl implements UserService
{
    @Inject
    UserDAO userDAO;

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findOneById(Long id) { return userDAO.findOne(id); }

    @Override
    public User findOneByNomAndPrenom(String nom, String prenom) {
        return userDAO.findOneByNomAndPrenom(nom, prenom);
    }

    @Override
    public User findOneByPseudo(String pseudo) { return userDAO.findOneByPseudo(pseudo); }

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public long count() {
        return userDAO.count();
    }

    @Override
    public void delete(User user) { userDAO.delete(user); }

    @Override
    public void deleteOneById(Long id) { userDAO.delete(id); }

    @Override
    public Boolean exists(Long id) { return userDAO.exists(id); }

    @Override
    public Boolean exists(String email) { return null!=userDAO.findOneByEmail(email); }
}
