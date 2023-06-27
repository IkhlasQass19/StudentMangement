/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mimif
 */
@Entity
@Table(name = "coordinateur", catalog = "ds", schema = "")
@NamedQueries({
    @NamedQuery(name = "Coordinateur.findAll", query = "SELECT c FROM Coordinateur c"),
    @NamedQuery(name = "Coordinateur.findById", query = "SELECT c FROM Coordinateur c WHERE c.id = :id"),
    @NamedQuery(name = "Coordinateur.findByNom", query = "SELECT c FROM Coordinateur c WHERE c.nom = :nom"),
    @NamedQuery(name = "Coordinateur.findByPrenom", query = "SELECT c FROM Coordinateur c WHERE c.prenom = :prenom"),
    @NamedQuery(name = "Coordinateur.findByEmail", query = "SELECT c FROM Coordinateur c WHERE c.email = :email"),
    @NamedQuery(name = "Coordinateur.findByDateNaissance", query = "SELECT c FROM Coordinateur c WHERE c.dateNaissance = :dateNaissance"),
    @NamedQuery(name = "Coordinateur.findByCin", query = "SELECT c FROM Coordinateur c WHERE c.cin = :cin"),
    @NamedQuery(name = "Coordinateur.findByTelephone", query = "SELECT c FROM Coordinateur c WHERE c.telephone = :telephone"),
    @NamedQuery(name = "Coordinateur.findByAge", query = "SELECT c FROM Coordinateur c WHERE c.age = :age"),
    @NamedQuery(name = "Coordinateur.findByRole", query = "SELECT c FROM Coordinateur c WHERE c.role = :role")})
public class Coordinateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "prenom")
    private String prenom;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "date_naissance")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @Basic(optional = false)
    @Column(name = "cin")
    private String cin;
    @Basic(optional = false)
    @Column(name = "telephone")
    private String telephone;
    @Basic(optional = false)
    @Column(name = "age")
    private int age;
    @Basic(optional = false)
    @Column(name = "role")
    private String role;

    public Coordinateur() {
    }

    public Coordinateur(Integer id) {
        this.id = id;
    }

    public Coordinateur(Integer id, String nom, String prenom, String email, Date dateNaissance, String cin, String telephone, int age, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.cin = cin;
        this.telephone = telephone;
        this.age = age;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coordinateur)) {
            return false;
        }
        Coordinateur other = (Coordinateur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Coordinateur[ id=" + id + " ]";
    }
    
}
