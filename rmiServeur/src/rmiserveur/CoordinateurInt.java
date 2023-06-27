/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmiserveur;

import entities.Coordinateur;
import entities.Enseignant;
import entities.Etudiant;
import entities.Module;
import entities.Note;
import entities.Response;
import java.rmi.Remote;
import java.util.List;
import java.rmi.*;

/**
 *
 * @author mimif
 */
public interface CoordinateurInt extends Remote {

    void create(Coordinateur coordinateur) throws RemoteException;

    List<Coordinateur> findAll() throws RemoteException;

    List<Object[]> getEtudiantNotesBySem(int id_etd, int sem) throws RemoteException;

    public int EditNote(int idEtudiant, int idmod, double note) throws RemoteException;

    public int addEtudiant(Etudiant etudiant) throws RemoteException;

    public List<Etudiant> allEtudiants() throws RemoteException;

    public int addEnseignant(Enseignant enseignant) throws RemoteException;

    public int UpdateEnseignant(Enseignant enseignant) throws RemoteException;

    public int UpdateEtudiant(Etudiant etudiant) throws RemoteException;

    public List<Enseignant> allEnseignants() throws RemoteException;

    public void deleteEnseignantByCin(String cin) throws RemoteException;

    public void deleteEtudiantByCin(String cin) throws RemoteException;

    public String[][] getProfs() throws RemoteException;

    public void insertModule(String semestre, String intervenant, String idprof, String nom) throws RemoteException;

    public List<Module> allModules() throws RemoteException;

    public void deleteModule(int cin) throws RemoteException;

    public int UpdateModule(String semestre, String intervenant, String idprof, String nom, int id) throws RemoteException;
     public List<Response> allResponse(String idprof) throws RemoteException;
      public List<Module> Modules(String cin) throws RemoteException;
       public int addNote(Note note) throws RemoteException;
       public double getNote(int idetud) throws RemoteException ;
}
