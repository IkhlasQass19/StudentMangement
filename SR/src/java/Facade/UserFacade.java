/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;


import entities.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author hp
 */
@Stateless(name="User")
public class UserFacade extends AbstractFacade<User> implements Remote.UserFacadeRemote {

    @PersistenceContext(name = "persistence")
    private EntityManager em;
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    // Authenticate user
    public User signIn(String login, String password) {
        System.out.println("hhh1234");
        Query query = em.createQuery("SELECT u FROM User u WHERE u.login = :login AND u.password = :password");
        query.setParameter("login", login);
        query.setParameter("password", password);

        try {
            
            User user = (User) query.getSingleResult();
            return user;
        } catch (Exception e) {
            // User not found or multiple users found
            return null;
        }
    }
}