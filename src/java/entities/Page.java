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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kATHRYN
 */
@Entity
@Table(name = "page")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Page.findAll", query = "SELECT p FROM Page p"),
    @NamedQuery(name = "Page.findByPageID", query = "SELECT p FROM Page p WHERE p.pageID = :pageID"),
    @NamedQuery(name = "Page.findByPostCount", query = "SELECT p FROM Page p WHERE p.postCount = :postCount")})
public class Page implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PageID")
    private Integer pageID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PostCount")
    private int postCount;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pageID")
    private Collection<Fbgroup> fbgroupCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "page")
    private Grouppage grouppage;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "page")
    private Collection<Posts> postsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pageID")
    private Collection<User> userCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "page")
    private Personalpage personalpage;

    public Page() {
    }

    public Page(Integer pageID) {
        this.pageID = pageID;
    }

    public Page(Integer pageID, int postCount) {
        this.pageID = pageID;
        this.postCount = postCount;
    }

    public Integer getPageID() {
        return pageID;
    }

    public void setPageID(Integer pageID) {
        this.pageID = pageID;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    @XmlTransient
    public Collection<Fbgroup> getFbgroupCollection() {
        return fbgroupCollection;
    }

    public void setFbgroupCollection(Collection<Fbgroup> fbgroupCollection) {
        this.fbgroupCollection = fbgroupCollection;
    }

    public Grouppage getGrouppage() {
        return grouppage;
    }

    public void setGrouppage(Grouppage grouppage) {
        this.grouppage = grouppage;
    }

    @XmlTransient
    public Collection<Posts> getPostsCollection() {
        return postsCollection;
    }

    public void setPostsCollection(Collection<Posts> postsCollection) {
        this.postsCollection = postsCollection;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    public Personalpage getPersonalpage() {
        return personalpage;
    }

    public void setPersonalpage(Personalpage personalpage) {
        this.personalpage = personalpage;
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
        if (!(object instanceof Page)) {
            return false;
        }
        Page other = (Page) object;
        if ((this.pageID == null && other.pageID != null) || (this.pageID != null && !this.pageID.equals(other.pageID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Page[ pageID=" + pageID + " ]";
    }
    
}
