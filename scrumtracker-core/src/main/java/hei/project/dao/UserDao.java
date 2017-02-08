package hei.project.dao;

import hei.project.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by nicol on 07/02/2017.
 */
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public User getUser(int id){
        return  entityManager.find(User.class, id);
    }

}
