/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

import Remote.CoordinateurFacadeRemote;
import entities.Coordinateur;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author hp
 */
@Stateless(name="Coordinateur")
public class CoordinateurFacade extends AbstractFacade<Coordinateur> implements CoordinateurFacadeRemote {

     @PersistenceContext(name = "persistence")
     private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CoordinateurFacade() {
        super(Coordinateur.class);
    }
    
    //inscrire coordinateur
    public void create(Coordinateur c){
        em.persist(c);
    }
    
    //releve de note d'un etudiant 
    public List<Object[]> getEtudiantNotesBySem(int id_etd, int sem) {
        String sqlQuery = "SELECT * " +
                          "FROM etudiant e " +
                          "JOIN note n ON e.id = n.id_etd " +
                          "JOIN module m ON n.id_mod = m.id " +
                          "WHERE e.id = :id_etd AND n.semester = :sem";

        Query query = em.createNativeQuery(sqlQuery);
        query.setParameter("id_etd", id_etd);
        query.setParameter("sem", sem);

        return query.getResultList();
    }

    
    //liste des coordinateurs 
    public List<Coordinateur> findAll() {
    TypedQuery<Coordinateur> query = em.createQuery("SELECT c FROM Coordinateur c", Coordinateur.class);
    return query.getResultList();
}

}
