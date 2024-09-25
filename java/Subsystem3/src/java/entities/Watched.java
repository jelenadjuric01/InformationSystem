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
@Table(name = "watched")
@NamedQueries({
    @NamedQuery(name = "Watched.findAll", query = "SELECT w FROM Watched w"),
    @NamedQuery(name = "Watched.findByIdW", query = "SELECT w FROM Watched w WHERE w.idW = :idW"),
    @NamedQuery(name = "Watched.findByDateTime", query = "SELECT w FROM Watched w WHERE w.dateTime = :dateTime"),
    @NamedQuery(name = "Watched.findByStartSecond", query = "SELECT w FROM Watched w WHERE w.startSecond = :startSecond"),
    @NamedQuery(name = "Watched.findBySecondsWatched", query = "SELECT w FROM Watched w WHERE w.secondsWatched = :secondsWatched")})
public class Watched implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdW")
    private Integer idW;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "StartSecond")
    private int startSecond;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SecondsWatched")
    private int secondsWatched;
    @JoinColumn(name = "IdU", referencedColumnName = "IdU")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User idU;
    @JoinColumn(name = "IdV", referencedColumnName = "IdV")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Video idV;

    public Watched() {
    }

    public Watched(Integer idW) {
        this.idW = idW;
    }

    public Watched(Integer idW, Date dateTime, int startSecond, int secondsWatched) {
        this.idW = idW;
        this.dateTime = dateTime;
        this.startSecond = startSecond;
        this.secondsWatched = secondsWatched;
    }

    public Integer getIdW() {
        return idW;
    }

    public void setIdW(Integer idW) {
        this.idW = idW;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getStartSecond() {
        return startSecond;
    }

    public void setStartSecond(int startSecond) {
        this.startSecond = startSecond;
    }

    public int getSecondsWatched() {
        return secondsWatched;
    }

    public void setSecondsWatched(int secondsWatched) {
        this.secondsWatched = secondsWatched;
    }

    public User getIdU() {
        return idU;
    }

    public void setIdU(User idU) {
        this.idU = idU;
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
        hash += (idW != null ? idW.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Watched)) {
            return false;
        }
        Watched other = (Watched) object;
        if ((this.idW == null && other.idW != null) || (this.idW != null && !this.idW.equals(other.idW))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Watched[ idW=" + idW + " ]";
    }
    
}
