/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kATHRYN
 */
@Entity
@Table(name = "grouppage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grouppage.findAll", query = "SELECT g FROM Grouppage g"),
    @NamedQuery(name = "Grouppage.findByPageID", query = "SELECT g FROM Grouppage g WHERE g.pageID = :pageID")})
public class Grouppage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PageID")
    private Integer pageID;
    @JoinColumn(name = "PageID", referencedColumnName = "PageID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Page page;
    @JoinColumn(name = "BelongsTo", referencedColumnName = "GroupID")
    @ManyToOne(optional = false)
    private Fbgroup belongsTo;

    public Grouppage() {
    }

    public Grouppage(Integer pageID) {
        this.pageID = pageID;
    }

    public Integer getPageID() {
        return pageID;
    }

    public void setPageID(Integer pageID) {
        this.pageID = pageID;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Fbgroup getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(Fbgroup belongsTo) {
        this.belongsTo = belongsTo;
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
        if (!(object instanceof Grouppage)) {
            return false;
        }
        Grouppage other = (Grouppage) object;
        if ((this.pageID == null && other.pageID != null) || (this.pageID != null && !this.pageID.equals(other.pageID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Grouppage[ pageID=" + pageID + " ]";
    }
    
}
