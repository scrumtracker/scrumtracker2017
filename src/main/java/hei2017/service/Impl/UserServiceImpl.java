package hei2017.service.Impl;

import hei2017.dao.ProjectDAO;
import hei2017.dao.TaskDAO;
import hei2017.dao.UserDAO;
import hei2017.entity.Project;
import hei2017.entity.Task;
import hei2017.entity.User;
import hei2017.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Set;

/**
 * Created by pic on 02/03/2017.
 */
@Named
@Transactional
public class UserServiceImpl implements UserService
{
    @Inject
    ProjectDAO projectDAO;

    @Inject
    TaskDAO taskDAO;

    @Inject
    UserDAO userDAO;

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public List<User> findAllWithAll() {
        List<User> users = userDAO.findAll();
        for(User user:users)
        {
            Set<Task> userTasks = taskDAO.findByTaskUsersId(user.getId());
            user.setUserTasks(userTasks);

            Set<Project> userProjects = projectDAO.findByProjectUsersId(user.getId());
            user.setUserProjects(userProjects);
        }
        return users;
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
    public User save(User user) {
        return userDAO.save(user);
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
