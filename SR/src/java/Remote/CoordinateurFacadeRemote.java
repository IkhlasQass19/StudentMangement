/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Remote;

import entities.Coordinateur;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author hp
 */
@Remote
public interface CoordinateurFacadeRemote {

    void create(Coordinateur coordinateur);

    void edit(Coordinateur coordinateur);

    void remove(Coordinateur coordinateur);

    Coordinateur find(Object id);

    List<Coordinateur> findAll();

    List<Coordinateur> findRange(int[] range);

    int count();
    
    public List<Object[]> getEtudiantNotesBySem(int id_etd, int sem);
}
