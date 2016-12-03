/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kATHRYN
 */
@Entity
@Table(name = "posts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Posts.findAll", query = "SELECT p FROM Posts p"),
    @NamedQuery(name = "Posts.findByPostID", query = "SELECT p FROM Posts p WHERE p.postID = :postID"),
    @NamedQuery(name = "Posts.findByPostDate", query = "SELECT p FROM Posts p WHERE p.postDate = :postDate"),
    @NamedQuery(name = "Posts.findByContent", query = "SELECT p FROM Posts p WHERE p.content = :content")})
public class Posts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "postID")
    private Integer postID;
    @Column(name = "PostDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;
    @Size(max = 21000)
    @Column(name = "Content")
    private String content;
    @OneToMany(mappedBy = "post")
    private Collection<Comments> commentsCollection;
    @JoinColumn(name = "Author", referencedColumnName = "AccountNumber")
    @ManyToOne(optional = false)
    private User author;
    @JoinColumn(name = "Page", referencedColumnName = "pageID")
    @ManyToOne(optional = false)
    private Pages page;

    public Posts() {
    }

    public Posts(Integer postID) {
        this.postID = postID;
    }

    public Integer getPostID() {
        return postID;
    }

    public void setPostID(Integer postID) {
        this.postID = postID;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @XmlTransient
    public Collection<Comments> getCommentsCollection() {
        return commentsCollection;
    }

    public void setCommentsCollection(Collection<Comments> commentsCollection) {
        this.commentsCollection = commentsCollection;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Pages getPage() {
        return page;
    }

    public void setPage(Pages page) {
        this.page = page;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postID != null ? postID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Posts)) {
            return false;
        }
        Posts other = (Posts) object;
        if ((this.postID == null && other.postID != null) || (this.postID != null && !this.postID.equals(other.postID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Posts[ postID=" + postID + " ]";
    }
    
}
