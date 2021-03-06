/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Guilherme Pereira
 */
@NamedQueries({
    @NamedQuery(name = "Edition.findAll", query = "SELECT e FROM Edition e"),
    @NamedQuery(name = "Edition.findByEditionId", query = "SELECT e FROM Edition e WHERE e.id = :id"),
    @NamedQuery(name = "Edition.findByName", query = "SELECT e FROM Edition e WHERE e.name = :name")
//    @NamedQuery(name = "Edition.findAllEvaluationById_edition", query = "SELECT e FROM Edition e  ")
})
@Entity
public class Edition implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Basic(optional = false)
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date yearEdition;

    @NotNull
    private int scale;

    @OneToMany(mappedBy = "edition", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ApUser> userList;

    @OneToMany(mappedBy = "edition", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Project> projectList;

    @OneToMany(mappedBy = "edition", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Criterion> criterionList;

    public Edition() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getYearEdition() {
        return new Date(yearEdition.getYear(), 0, 0);
    }

    public void setYearEdition(Date yearEdition) {
        this.yearEdition = yearEdition;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public List<ApUser> getUserList() {
        return userList;
    }

    public void setUserList(List<ApUser> userList) {
        this.userList = userList;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public List<Criterion> getCriterionList() {
        return criterionList;
    }

    public void setCriterionList(List<Criterion> criterionList) {
        this.criterionList = criterionList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Edition)) {
            return false;
        }
        Edition other = (Edition) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pt.uc.dei.paj.proj5.grupoF.Entity.Edition[ id=" + id + " ]";
    }

}
