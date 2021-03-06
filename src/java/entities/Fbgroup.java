/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kATHRYN
 */
@Entity
@Table(name = "fbgroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fbgroup.findAll", query = "SELECT f FROM Fbgroup f"),
    @NamedQuery(name = "Fbgroup.findByGroupID", query = "SELECT f FROM Fbgroup f WHERE f.groupID = :groupID"),
    @NamedQuery(name = "Fbgroup.findByGroupName", query = "SELECT f FROM Fbgroup f WHERE f.groupName = :groupName"),
    @NamedQuery(name = "Fbgroup.findByType", query = "SELECT f FROM Fbgroup f WHERE f.type = :type")})
public class Fbgroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "GroupID")
    private Integer groupID;
    @Size(max = 100)
    @Column(name = "GroupName")
    private String groupName;
    @Size(max = 50)
    @Column(name = "Type")
    private String type;
    @ManyToMany(mappedBy = "fbgroupCollection")
    private Collection<User> userCollection;
    @JoinColumn(name = "Owner", referencedColumnName = "AccountNumber")
    @ManyToOne(optional = false)
    private User owner;
    @JoinColumn(name = "PageID", referencedColumnName = "pageID")
    @ManyToOne(optional = false)
    private Pages pageID;

    public Fbgroup() {
    }

    public Fbgroup(Integer groupID) {
        this.groupID = groupID;
    }

    public Integer getGroupID() {
        return groupID;
    }

    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Pages getPageID() {
        return pageID;
    }

    public void setPageID(Pages pageID) {
        this.pageID = pageID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupID != null ? groupID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fbgroup)) {
            return false;
        }
        Fbgroup other = (Fbgroup) object;
        if ((this.groupID == null && other.groupID != null) || (this.groupID != null && !this.groupID.equals(other.groupID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Fbgroup[ groupID=" + groupID + " ]";
    }
    
}
