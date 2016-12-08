/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Conor
 */
@Entity
@Table(name = "advertisement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Advertisement.findAll", query = "SELECT a FROM Advertisement a")
    , @NamedQuery(name = "Advertisement.findByAdvertisementID", query = "SELECT a FROM Advertisement a WHERE a.advertisementID = :advertisementID")
    , @NamedQuery(name = "Advertisement.findByType", query = "SELECT a FROM Advertisement a WHERE a.type = :type")
    , @NamedQuery(name = "Advertisement.findByItemName", query = "SELECT a FROM Advertisement a WHERE a.itemName = :itemName")
    , @NamedQuery(name = "Advertisement.findByDateCreated", query = "SELECT a FROM Advertisement a WHERE a.dateCreated = :dateCreated")
    , @NamedQuery(name = "Advertisement.findByCompany", query = "SELECT a FROM Advertisement a WHERE a.company = :company")
    , @NamedQuery(name = "Advertisement.findByContent", query = "SELECT a FROM Advertisement a WHERE a.content = :content")
    , @NamedQuery(name = "Advertisement.findByPrice", query = "SELECT a FROM Advertisement a WHERE a.price = :price")
    , @NamedQuery(name = "Advertisement.findByUnitsAvailable", query = "SELECT a FROM Advertisement a WHERE a.unitsAvailable = :unitsAvailable")})
public class Advertisement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "advertisementID")
    private Integer advertisementID;
    @Size(max = 15)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "itemName")
    private String itemName;
    @Column(name = "dateCreated")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "company")
    private String company;
    @Size(max = 50)
    @Column(name = "content")
    private String content;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unitsAvailable")
    private int unitsAvailable;
    @JoinColumn(name = "employeeID", referencedColumnName = "ssn")
    @ManyToOne
    private Employee employeeID;
    @OneToMany(mappedBy = "advertisementID")
    private Collection<Salesdata> salesdataCollection;
    @OneToMany(mappedBy = "advertisementID")
    private Collection<Items> itemsCollection;

    public Advertisement() {
    }

    public Advertisement(Integer advertisementID) {
        this.advertisementID = advertisementID;
    }

    public Advertisement(Integer advertisementID, String itemName, String company, BigDecimal price, int unitsAvailable) {
        this.advertisementID = advertisementID;
        this.itemName = itemName;
        this.company = company;
        this.price = price;
        this.unitsAvailable = unitsAvailable;
    }

    public Integer getAdvertisementID() {
        return advertisementID;
    }

    public void setAdvertisementID(Integer advertisementID) {
        this.advertisementID = advertisementID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getUnitsAvailable() {
        return unitsAvailable;
    }

    public void setUnitsAvailable(int unitsAvailable) {
        this.unitsAvailable = unitsAvailable;
    }

    public Employee getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Employee employeeID) {
        this.employeeID = employeeID;
    }

    @XmlTransient
    public Collection<Salesdata> getSalesdataCollection() {
        return salesdataCollection;
    }

    public void setSalesdataCollection(Collection<Salesdata> salesdataCollection) {
        this.salesdataCollection = salesdataCollection;
    }

    @XmlTransient
    public Collection<Items> getItemsCollection() {
        return itemsCollection;
    }

    public void setItemsCollection(Collection<Items> itemsCollection) {
        this.itemsCollection = itemsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (advertisementID != null ? advertisementID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Advertisement)) {
            return false;
        }
        Advertisement other = (Advertisement) object;
        if ((this.advertisementID == null && other.advertisementID != null) || (this.advertisementID != null && !this.advertisementID.equals(other.advertisementID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Advertisement[ advertisementID=" + advertisementID + " ]";
    }
    
}
