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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
 * @author kATHRYN
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "User.findByAddress", query = "SELECT u FROM User u WHERE u.address = :address"),
    @NamedQuery(name = "User.findByCity", query = "SELECT u FROM User u WHERE u.city = :city"),
    @NamedQuery(name = "User.findByState", query = "SELECT u FROM User u WHERE u.state = :state"),
    @NamedQuery(name = "User.findByZipCode", query = "SELECT u FROM User u WHERE u.zipCode = :zipCode"),
    @NamedQuery(name = "User.findByTelephone", query = "SELECT u FROM User u WHERE u.telephone = :telephone"),
    @NamedQuery(name = "User.findByEmailAddress", query = "SELECT u FROM User u WHERE u.emailAddress = :emailAddress"),
    @NamedQuery(name = "User.findByAccountNumber", query = "SELECT u FROM User u WHERE u.accountNumber = :accountNumber"),
    @NamedQuery(name = "User.findByAccountCreationDate", query = "SELECT u FROM User u WHERE u.accountCreationDate = :accountCreationDate"),
    @NamedQuery(name = "User.findByPreferences", query = "SELECT u FROM User u WHERE u.preferences = :preferences"),
    @NamedQuery(name = "User.findByRating", query = "SELECT u FROM User u WHERE u.rating = :rating"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "LastName")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "FirstName")
    private String firstName;
    @Size(max = 30)
    @Column(name = "Address")
    private String address;
    @Size(max = 30)
    @Column(name = "City")
    private String city;
    @Size(max = 2)
    @Column(name = "State")
    private String state;
    @Size(max = 5)
    @Column(name = "ZipCode")
    private String zipCode;
    @Size(max = 10)
    @Column(name = "Telephone")
    private String telephone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "EmailAddress")
    private String emailAddress;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AccountNumber")
    private Integer accountNumber;
    @Column(name = "AccountCreationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date accountCreationDate;
    @Size(max = 500)
    @Column(name = "Preferences")
    private String preferences;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Rating")
    private BigDecimal rating;
    @Size(max = 16)
    @Column(name = "password")
    private String password;
    @JoinTable(name = "groupmembership", joinColumns = {
        @JoinColumn(name = "UserID", referencedColumnName = "AccountNumber")}, inverseJoinColumns = {
        @JoinColumn(name = "GroupID", referencedColumnName = "groupid")})
    @ManyToMany
    private Collection<Fbgroup> fbgroupCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Collection<Comments> commentsCollection;
    @OneToMany(mappedBy = "ownerID")
    private Collection<Pages> pagesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Collection<Fbgroup> fbgroupCollection1;
    @OneToMany(mappedBy = "accountNum")
    private Collection<Salesdata> salesdataCollection;
    @OneToMany(mappedBy = "sender")
    private Collection<Message> messageCollection;
    @OneToMany(mappedBy = "receiver")
    private Collection<Message> messageCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Collection<Posts> postsCollection;

    public User() {
    }

    public User(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public User(Integer accountNumber, String lastName, String firstName, String emailAddress) {
        this.accountNumber = accountNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.emailAddress = emailAddress;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(Date accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<Fbgroup> getFbgroupCollection() {
        return fbgroupCollection;
    }

    public void setFbgroupCollection(Collection<Fbgroup> fbgroupCollection) {
        this.fbgroupCollection = fbgroupCollection;
    }

    @XmlTransient
    public Collection<Comments> getCommentsCollection() {
        return commentsCollection;
    }

    public void setCommentsCollection(Collection<Comments> commentsCollection) {
        this.commentsCollection = commentsCollection;
    }

    @XmlTransient
    public Collection<Pages> getPagesCollection() {
        return pagesCollection;
    }

    public void setPagesCollection(Collection<Pages> pagesCollection) {
        this.pagesCollection = pagesCollection;
    }

    @XmlTransient
    public Collection<Fbgroup> getFbgroupCollection1() {
        return fbgroupCollection1;
    }

    public void setFbgroupCollection1(Collection<Fbgroup> fbgroupCollection1) {
        this.fbgroupCollection1 = fbgroupCollection1;
    }

    @XmlTransient
    public Collection<Salesdata> getSalesdataCollection() {
        return salesdataCollection;
    }

    public void setSalesdataCollection(Collection<Salesdata> salesdataCollection) {
        this.salesdataCollection = salesdataCollection;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection1() {
        return messageCollection1;
    }

    public void setMessageCollection1(Collection<Message> messageCollection1) {
        this.messageCollection1 = messageCollection1;
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
        hash += (accountNumber != null ? accountNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.accountNumber == null && other.accountNumber != null) || (this.accountNumber != null && !this.accountNumber.equals(other.accountNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.User[ accountNumber=" + accountNumber + " ]";
    }
    
}
