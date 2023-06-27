/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Remote;

import entities.User;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author hp
 */
@Remote
public interface UserFacadeRemote {


  void create(User user);

    void edit(User user);

    void remove(User user);

    User find(Object id);

    List<User> findAll();

    List<User> findRange(int[] range);

    int count();
        
    User signIn(String login, String password);
}
