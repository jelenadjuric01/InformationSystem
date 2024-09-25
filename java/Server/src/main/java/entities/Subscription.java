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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jelena
 */
@Entity
@Table(name = "subscription")
@NamedQueries({
    @NamedQuery(name = "Subscription.findAll", query = "SELECT s FROM Subscription s"),
    @NamedQuery(name = "Subscription.findByIdS", query = "SELECT s FROM Subscription s WHERE s.idS = :idS"),
    @NamedQuery(name = "Subscription.findByDateTime", query = "SELECT s FROM Subscription s WHERE s.dateTime = :dateTime"),
    @NamedQuery(name = "Subscription.findByPrice", query = "SELECT s FROM Subscription s WHERE s.price = :price")})
public class Subscription implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdS")
    private Integer idS;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Price")
    private int price;
    @JoinColumn(name = "IdP", referencedColumnName = "IdP")
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    private Package idP;
    @JoinColumn(name = "IdU", referencedColumnName = "IdU")
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    private User idU;

    public Subscription() {
    }

    public Subscription(Integer idS) {
        this.idS = idS;
    }

    public Subscription(Integer idS, Date dateTime, int price) {
        this.idS = idS;
        this.dateTime = dateTime;
        this.price = price;
    }

    public Integer getIdS() {
        return idS;
    }

    public void setIdS(Integer idS) {
        this.idS = idS;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Package getIdP() {
        return idP;
    }

    public void setIdP(Package idP) {
        this.idP = idP;
    }

    public User getIdU() {
        return idU;
    }

    public void setIdU(User idU) {
        this.idU = idU;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idS != null ? idS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subscription)) {
            return false;
        }
        Subscription other = (Subscription) object;
        if ((this.idS == null && other.idS != null) || (this.idS != null && !this.idS.equals(other.idS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Subscription[ idS=" + idS + " ]";
    }
    
}
