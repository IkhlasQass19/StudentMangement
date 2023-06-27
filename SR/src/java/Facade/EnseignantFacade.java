/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

import entities.Enseignant;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author hp
 */
@Stateless(name="Enseignant")
public class EnseignantFacade extends AbstractFacade<Enseignant> implements Remote.EnseignantFacadeRemote {
    TypedQuery<Enseignant> query;
     @PersistenceContext(name = "persistence")
    private EntityManager em;
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EnseignantFacade() {
        super(Enseignant.class);
    }
    
    //get enseignant par cin
    public Enseignant getEnsByCIN(String cin) {
        query = em.createQuery("SELECT e FROM Enseignant e WHERE e.cin = :cin", Enseignant.class);
        query.setParameter("cin", cin);
        return query.getSingleResult();
    }

    //liste des enseignants
     public List<Enseignant> findAll(){
        List<Enseignant> l = em.createNamedQuery("Enseignant.findAll", Enseignant.class).getResultList();
        return l;
    }
    
}
