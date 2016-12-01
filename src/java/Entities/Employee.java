/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "employee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findBySsn", query = "SELECT e FROM Employee e WHERE e.ssn = :ssn"),
    @NamedQuery(name = "Employee.findByLastName", query = "SELECT e FROM Employee e WHERE e.lastName = :lastName"),
    @NamedQuery(name = "Employee.findByFirstName", query = "SELECT e FROM Employee e WHERE e.firstName = :firstName"),
    @NamedQuery(name = "Employee.findByAddress", query = "SELECT e FROM Employee e WHERE e.address = :address"),
    @NamedQuery(name = "Employee.findByCity", query = "SELECT e FROM Employee e WHERE e.city = :city"),
    @NamedQuery(name = "Employee.findByState", query = "SELECT e FROM Employee e WHERE e.state = :state"),
    @NamedQuery(name = "Employee.findByZipcode", query = "SELECT e FROM Employee e WHERE e.zipcode = :zipcode"),
    @NamedQuery(name = "Employee.findByTelephone", query = "SELECT e FROM Employee e WHERE e.telephone = :telephone"),
    @NamedQuery(name = "Employee.findByHourlyRate", query = "SELECT e FROM Employee e WHERE e.hourlyRate = :hourlyRate"),
    @NamedQuery(name = "Employee.findByStartDate", query = "SELECT e FROM Employee e WHERE e.startDate = :startDate")})
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ssn")
    private Integer ssn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "lastName")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "firstName")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "address")
    private String address;
    @Size(max = 20)
    @Column(name = "city")
    private String city;
    @Size(max = 2)
    @Column(name = "state")
    private String state;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "zipcode")
    private String zipcode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "hourlyRate")
    private Long hourlyRate;
    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @OneToMany(mappedBy = "employeeID")
    private Collection<Advertisement> advertisementCollection;

    public Employee() {
    }

    public Employee(Integer ssn) {
        this.ssn = ssn;
    }

    public Employee(Integer ssn, String lastName, String firstName, String address, String zipcode, String telephone) {
        this.ssn = ssn;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.zipcode = zipcode;
        this.telephone = telephone;
    }

    public Integer getSsn() {
        return ssn;
    }

    public void setSsn(Integer ssn) {
        this.ssn = ssn;
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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Long hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @XmlTransient
    public Collection<Advertisement> getAdvertisementCollection() {
        return advertisementCollection;
    }

    public void setAdvertisementCollection(Collection<Advertisement> advertisementCollection) {
        this.advertisementCollection = advertisementCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ssn != null ? ssn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.ssn == null && other.ssn != null) || (this.ssn != null && !this.ssn.equals(other.ssn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Employee[ ssn=" + ssn + " ]";
    }
    
}
