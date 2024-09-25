/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jelena
 */
@Entity
@Table(name = "belongs")
@NamedQueries({
    @NamedQuery(name = "Belongs.findAll", query = "SELECT b FROM Belongs b"),
    @NamedQuery(name = "Belongs.findByIdB", query = "SELECT b FROM Belongs b WHERE b.idB = :idB")})
public class Belongs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdB")
    private Integer idB;
    @JoinColumn(name = "IdC", referencedColumnName = "IdC")
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    private Category idC;
    @JoinColumn(name = "IdV", referencedColumnName = "IdV")
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    private Video idV;

    public Belongs() {
    }

    public Belongs(Integer idB) {
        this.idB = idB;
    }

    public Integer getIdB() {
        return idB;
    }

    public void setIdB(Integer idB) {
        this.idB = idB;
    }

    public Category getIdC() {
        return idC;
    }

    public void setIdC(Category idC) {
        this.idC = idC;
    }

    public Video getIdV() {
        return idV;
    }

    public void setIdV(Video idV) {
        this.idV = idV;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idB != null ? idB.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Belongs)) {
            return false;
        }
        Belongs other = (Belongs) object;
        if ((this.idB == null && other.idB != null) || (this.idB != null && !this.idB.equals(other.idB))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Belongs[ idB=" + idB + " ]";
    }
    
}
