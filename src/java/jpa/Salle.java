/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author YB
 */
@Entity
@Table(name = "SALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salle.findAll", query = "SELECT s FROM Salle s"),
    @NamedQuery(name = "Salle.findByIdsalle", query = "SELECT s FROM Salle s WHERE s.idsalle = :idsalle"),
    @NamedQuery(name = "Salle.findByNomsalle", query = "SELECT s FROM Salle s WHERE s.nomsalle = :nomsalle"),
    @NamedQuery(name = "Salle.findByTypesalle", query = "SELECT s FROM Salle s WHERE s.typesalle = :typesalle"),
    @NamedQuery(name = "Salle.findByProj", query = "SELECT s FROM Salle s WHERE s.proj = :proj"),
    @NamedQuery(name = "Salle.findByCapacite", query = "SELECT s FROM Salle s WHERE s.capacite = :capacite")})
public class Salle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDSALLE")
    private Integer idsalle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "NOMSALLE")
    private String nomsalle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "TYPESALLE")
    private String typesalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROJ")
    private short proj;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAPACITE")
    private int capacite;

    public Salle() {
    }

    public Salle(Integer idsalle) {
        this.idsalle = idsalle;
    }

    public Salle( String nomsalle, String typesalle, short proj, int capacite) {
        this.nomsalle = nomsalle;
        this.typesalle = typesalle;
        this.proj = proj;
        this.capacite = capacite;
    }

    public Integer getIdsalle() {
        return idsalle;
    }

    public void setIdsalle(Integer idsalle) {
        this.idsalle = idsalle;
    }

    public String getNomsalle() {
        return nomsalle;
    }

    public void setNomsalle(String nomsalle) {
        this.nomsalle = nomsalle;
    }

    public String getTypesalle() {
        return typesalle;
    }

    public void setTypesalle(String typesalle) {
        this.typesalle = typesalle;
    }

    public short getProj() {
        return proj;
    }

    public void setProj(short proj) {
        this.proj = proj;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsalle != null ? idsalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salle)) {
            return false;
        }
        Salle other = (Salle) object;
        if ((this.idsalle == null && other.idsalle != null) || (this.idsalle != null && !this.idsalle.equals(other.idsalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Salle[ idsalle=" + idsalle + " ]";
    }
    
}
