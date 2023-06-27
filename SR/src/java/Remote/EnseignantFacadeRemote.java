/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Remote;

import entities.Enseignant;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author hp
 */
@Remote
public interface EnseignantFacadeRemote {

   
    void create(Enseignant enseignant);

    void edit(Enseignant enseignant);

    void remove(Enseignant enseignant);

    Enseignant find(Object id);

    List<Enseignant> findAll();

    List<Enseignant> findRange(int[] range);

    int count();
    
    Enseignant getEnsByCIN(String cin);
    
}
