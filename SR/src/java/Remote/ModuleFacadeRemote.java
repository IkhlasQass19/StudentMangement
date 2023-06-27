/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Remote;

import entities.Module;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author hp
 */
@Remote
public interface ModuleFacadeRemote {

   void create(Module module);

    void edit(Module module);

    void remove(Module module);

    Module find(Object id);

    List<Module> findAll();

    List<Module> findRange(int[] range);

    int count();
    
    Module getModuleByLibelle(String libelle);
    
}
