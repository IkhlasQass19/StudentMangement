/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Remote;

import entities.Etudiant;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author hp
 */
@Remote
public interface EtudiantFacadeRemote {

    void create(Etudiant etudiant);

    void edit(Etudiant etudiant);

    void remove(Etudiant etudiant);

    Etudiant find(Object id);

    List<Etudiant> findAll();

    List<Etudiant> findRange(int[] range);

    int count();
    
}
