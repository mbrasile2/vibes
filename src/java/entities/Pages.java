/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kATHRYN
 */
@Entity
@Table(name = "pages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pages.findAll", query = "SELECT p FROM Pages p"),
    @NamedQuery(name = "Pages.findByPageID", query = "SELECT p FROM Pages p WHERE p.pageID = :pageID"),
    @NamedQuery(name = "Pages.findByPostcount", query = "SELECT p FROM Pages p WHERE p.postcount = :postcount"),
    @NamedQuery(name = "Pages.findByPrimarypage", query = "SELECT p FROM Pages p WHERE p.primarypage = :primarypage")})
public class Pages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pageID")
    private Integer pageID;
    @Column(name = "postcount")
    private Integer postcount;
    @Column(name = "primarypage")
    private Character primarypage;
    @JoinColumn(name = "ownerID", referencedColumnName = "AccountNumber")
    @ManyToOne
    private User ownerID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pageID")
    private Collection<Fbgroup> fbgroupCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "page")
    private Collection<Posts> postsCollection;

    public Pages() {
    }

    public Pages(Integer pageID) {
        this.pageID = pageID;
    }

    public Integer getPageID() {
        return pageID;
    }

    public void setPageID(Integer pageID) {
        this.pageID = pageID;
    }

    public Integer getPostcount() {
        return postcount;
    }

    public void setPostcount(Integer postcount) {
        this.postcount = postcount;
    }

    public Character getPrimarypage() {
        return primarypage;
    }

    public void setPrimarypage(Character primarypage) {
        this.primarypage = primarypage;
    }

    public User getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(User ownerID) {
        this.ownerID = ownerID;
    }

    @XmlTransient
    public Collection<Fbgroup> getFbgroupCollection() {
        return fbgroupCollection;
    }

    public void setFbgroupCollection(Collection<Fbgroup> fbgroupCollection) {
        this.fbgroupCollection = fbgroupCollection;
    }

    @XmlTransient
    public Collection<Posts> getPostsCollection() {
        return postsCollection;
    }

    public void setPostsCollection(Collection<Posts> postsCollection) {
        this.postsCollection = postsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pageID != null ? pageID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pages)) {
            return false;
        }
        Pages other = (Pages) object;
        if ((this.pageID == null && other.pageID != null) || (this.pageID != null && !this.pageID.equals(other.pageID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pages[ pageID=" + pageID + " ]";
    }
    
}
