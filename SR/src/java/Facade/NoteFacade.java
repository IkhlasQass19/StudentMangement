/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

import entities.Note;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hp
 */
@Stateless(name="Note")
public class NoteFacade extends AbstractFacade<Note> implements Remote.NoteFacadeRemote {

     @PersistenceContext(name = "persistence")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NoteFacade() {
        super(Note.class);
    }
    //ajouter ligne note dans la table note
    public void add(double note, int idEns, int idEtd, int idMod){
        
        //Enseignant ens = enseignantFacade.getEnsByCIN(cin);
        //entities.Module mod = moduleFacade.getModuleByLibelle(Libelle);
        //Etudiant etd = etudiantFacade.getEtudiantById(idetd);
        Note noteObj = new Note(note,idEns,idEtd, idMod);
        em.persist(noteObj);

    }
    
    //editer la note d'un etudiant
    public void editerNote(int idNote,double newNoteValue) {
        Note note = em.find(Note.class, idNote);
        if (note != null) {
            note.setNote(newNoteValue);
            em.merge(note);
        }
    }
    
    
    //liste des notes
    public List<Note> findAll() {
        List<Note> modules = em.createNamedQuery("Note.findAll", Note.class).getResultList();
        return modules;
    }
  
}
