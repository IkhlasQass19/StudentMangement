/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author mimif
 */
@Entity
@Table(name = "module", catalog = "ds", schema = "")
@NamedQueries({
    @NamedQuery(name = "Module.findAll", query = "SELECT m FROM Module m"),
    @NamedQuery(name = "Module.findById", query = "SELECT m FROM Module m WHERE m.id = :id"),
    @NamedQuery(name = "Module.findByLibelleDuModule", query = "SELECT m FROM Module m WHERE m.libelleDuModule = :libelleDuModule"),
    @NamedQuery(name = "Module.findByEtablissement", query = "SELECT m FROM Module m WHERE m.etablissement = :etablissement"),
    @NamedQuery(name = "Module.findByIntervenant", query = "SELECT m FROM Module m WHERE m.intervenant = :intervenant")})
public class Module implements Serializable {

       private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nom")
    private String libelleDuModule;
    @Basic(optional = false)
    @Column(name = "semestre")
    private String etablissement;
    @Basic(optional = false)
    @Column(name = "intervenant")
    private String intervenant;
    @Basic(optional = false)
    @Column(name = "idprof")
    private String idprof;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMod")
    private Collection<Note> noteCollection;

    public Module() {
    }

    public Module(Integer id) {
        this.id = id;
    }

    public Module(Integer id, String libelleDuModule, String etablissement, String idprof, String intervenant) {
        this.id = id;
        this.libelleDuModule = libelleDuModule;
        this.etablissement = etablissement;
        this.intervenant = intervenant;
        this.idprof=idprof;
    }

    public String getIdprof() {
        return idprof;
    }

    public void setIdprof(String idprof) {
        this.idprof = idprof;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelleDuModule() {
        return libelleDuModule;
    }

    public void setLibelleDuModule(String libelleDuModule) {
        this.libelleDuModule = libelleDuModule;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }



    public String getIntervenant() {
        return intervenant;
    }

    public void setIntervenant(String intervenant) {
        this.intervenant = intervenant;
    }

    public Collection<Note> getNoteCollection() {
        return noteCollection;
    }

    public void setNoteCollection(Collection<Note> noteCollection) {
        this.noteCollection = noteCollection;
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
        if (!(object instanceof Module)) {
            return false;
        }
        Module other = (Module) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Module[ id=" + id + " ]";
    }
    
}
