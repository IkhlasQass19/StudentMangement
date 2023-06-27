/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

import entities.Etudiant;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
/**
 *
 * @author hp
 */
@Stateless(name="Etudiant")
public class EtudiantFacade extends AbstractFacade<Etudiant> implements Remote.EtudiantFacadeRemote {
    TypedQuery<Etudiant> query;
    @PersistenceContext(name = "persistence")
    private EntityManager em;
 


   @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EtudiantFacade() {
        super(Etudiant.class);
    }
    
    //liste des etudiants
    @Override
    public List<Etudiant> findAll(){
        List<Etudiant> l = em.createNamedQuery("Etudiant.findAll", Etudiant.class).getResultList();
        return l;
    }
    
    //liste des etudiants par semestre

    public List<Etudiant> EtudiantBySem(int sem) {
        query = em.createQuery("SELECT e FROM Etudiant e WHERE e.semester = :sem", Etudiant.class);
        query.setParameter("sem", sem);
        return query.getResultList();
    }

    //get Etudiant par cin

    public Etudiant getEtudiantByCIN(String cin) {
        query = em.createQuery("SELECT e FROM Etudiant e WHERE e.cin = :cin", Etudiant.class);
        query.setParameter("cin", cin);
        return query.getSingleResult();
    }
    
    //get Etudiant par id
  
     public Etudiant getEtudiantById(int id) {
        return em.find(Etudiant.class, id);
    }
     
    //consulet un cours d'un module
   
    public List<File> getAllCoursPDF(String mod) {
        String cheminRepertoire = "chemin/vers/le/repertoire/"+mod;
        File repertoire = new File(cheminRepertoire);

        List<File> fichiersPDF = new ArrayList<>();

        if (repertoire.exists() && repertoire.isDirectory()) {
            File[] fichiers = repertoire.listFiles();

            for (File fichier : fichiers) {
                if (fichier.isFile() && fichier.getName().endsWith(".pdf")) {
                    fichiersPDF.add(fichier);
                }
            }
        }

        return fichiersPDF;
    }

    
}
