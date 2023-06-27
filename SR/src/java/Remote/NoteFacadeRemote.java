/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Remote;

import entities.Note;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author hp
 */
@Remote
public interface NoteFacadeRemote {

    void edit(Note note);

    void remove(Note note);

    Note find(Object id);

    List<Note> findAll();
    
    void add(double note, int idEns, int idEtd, int idMod);
    
    void editerNote(int idNote, double newNoteValue);
    
}
