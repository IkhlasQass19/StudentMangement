/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

/**
 *
 * @author hp
 */
import java.io.Serializable;
public class Response implements Serializable {

    String nom;
    String prenom;
    String Sem;
    String idmod;
    String module;
    String idprof;
    String idetud;

    public Response(String nom, String prenom, String Sem, String idmod, String module, String idprof, String idetud) {
        this.nom = nom;
        this.prenom = prenom;
        this.Sem = Sem;
        this.idmod = idmod;
        this.module = module;
        this.idprof = idprof;
        this.idetud = idetud;
    }

    public Response() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSem() {
        return Sem;
    }

    public void setSem(String Sem) {
        this.Sem = Sem;
    }

    public String getIdmod() {
        return idmod;
    }

    public void setIdmod(String idmod) {
        this.idmod = idmod;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getIdprof() {
        return idprof;
    }

    public void setIdprof(String idprof) {
        this.idprof = idprof;
    }

    public String getIdetud() {
        return idetud;
    }

    public void setIdetud(String idetud) {
        this.idetud = idetud;
    }
    

}
