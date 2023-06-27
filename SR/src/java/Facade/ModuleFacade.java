/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

import entities.Module;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author hp
 */
@Stateless(name="Module")
public class ModuleFacade extends AbstractFacade<Module> implements Remote.ModuleFacadeRemote {
    TypedQuery<Module> query;
     @PersistenceContext(name = "persistence")
    private EntityManager em;

   
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModuleFacade() {
        super(Module.class);
    }
    
    // ligne module par libelle
    public Module getModuleByLibelle(String libelle) {
        query = em.createQuery("SELECT m FROM Module m WHERE m.libelle = :libelle", Module.class);
        query.setParameter("libelle", libelle);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;//"Module not found"; 
        }
    }

    //liste des modules
    public List<Module> findAll() {
        List<Module> modules = em.createNamedQuery("Module.findAll", Module.class).getResultList();
        return modules;
    }
    
    
}
