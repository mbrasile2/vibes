/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author kATHRYN
 */
@Entity
@Table(name = "salesdata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salesdata.findAll", query = "SELECT s FROM Salesdata s"),
    @NamedQuery(name = "Salesdata.findByTransactionID", query = "SELECT s FROM Salesdata s WHERE s.transactionID = :transactionID"),
    @NamedQuery(name = "Salesdata.findByDate", query = "SELECT s FROM Salesdata s WHERE s.date = :date"),
    @NamedQuery(name = "Salesdata.findByNumUnits", query = "SELECT s FROM Salesdata s WHERE s.numUnits = :numUnits")})
public class Salesdata implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "transactionID")
    private Integer transactionID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numUnits")
    private int numUnits;
    @JoinColumn(name = "accountNum", referencedColumnName = "AccountNumber")
    @ManyToOne
    private User accountNum;
    @JoinColumn(name = "advertisementID", referencedColumnName = "advertisementID")
    @ManyToOne
    private Advertisement advertisementID;

    public Salesdata() {
    }

    public Salesdata(Integer transactionID) {
        this.transactionID = transactionID;
    }

    public Salesdata(Integer transactionID, Date date, int numUnits) {
        this.transactionID = transactionID;
        this.date = date;
        this.numUnits = numUnits;
    }

    public Integer getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Integer transactionID) {
        this.transactionID = transactionID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumUnits() {
        return numUnits;
    }

    public void setNumUnits(int numUnits) {
        this.numUnits = numUnits;
    }

    public User getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(User accountNum) {
        this.accountNum = accountNum;
    }

    public Advertisement getAdvertisementID() {
        return advertisementID;
    }

    public void setAdvertisementID(Advertisement advertisementID) {
        this.advertisementID = advertisementID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionID != null ? transactionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salesdata)) {
            return false;
        }
        Salesdata other = (Salesdata) object;
        if ((this.transactionID == null && other.transactionID != null) || (this.transactionID != null && !this.transactionID.equals(other.transactionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Salesdata[ transactionID=" + transactionID + " ]";
    }
    
}
