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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Grupo F
 */
@NamedQueries({
    @NamedQuery(name = "ApUser.findAll", query = "SELECT u FROM ApUser u"),
    @NamedQuery(name = "ApUser.findByUserId", query = "SELECT u FROM ApUser u WHERE u.ApUserId = :ApUserId"),
    @NamedQuery(name = "ApUser.findByName", query = "SELECT u FROM ApUser u WHERE u.name = :name"),
    @NamedQuery(name = "ApUser.findByEmail", query = "SELECT u FROM ApUser u WHERE u.email = :email"),
    @NamedQuery(name = "ApUser.findByPassword", query = "SELECT u FROM ApUser u WHERE u.password = :password"),
    @NamedQuery(name = "ApUser.findByregisterDate", query = "SELECT u FROM ApUser u WHERE u.registerDate = :registerDate"),
    @NamedQuery(name = "ApUser.findByregisterlogList", query = "SELECT u FROM ApUser u WHERE u.logList = :logList")})
@Entity
@NamedQueries({
    @NamedQuery(name = "ApUser.getUserByEmail", query = "SELECT u FROM ApUser u WHERE u.email = :email")
})

public class ApUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ApUserId;

    @NotNull
    @Basic(optional = false)
    @Column(nullable = false)
    private String name;

    @NotNull
    @Basic(optional = false)
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            + "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message = "Invalid email")
    @Column(nullable = false, unique = true)
    private String email;

    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private String password;

    @NotNull
    @Basic(optional = false)
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;

    @OneToMany(mappedBy = "apUser", cascade = CascadeType.ALL)
    private List<Log> logList;

    @OneToMany(mappedBy = "apUser", cascade = CascadeType.ALL)
    private List<Evaluation> evaluationList;

    @ManyToOne
    private Edition edition;

    @ManyToMany
    private List<Project> projectList;

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public void setLogList(List<Log> logList) {
        this.logList = logList;
    }

    public void setEvaluationList(List<Evaluation> evaluationList) {
        this.evaluationList = evaluationList;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public void setApUserId(Long ApUserId) {
        this.ApUserId = ApUserId;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public List<Log> getLogList() {
        return logList;
    }

    public List<Evaluation> getEvaluationList() {
        return evaluationList;
    }

    public String getEmail() {
        return email;
    }

    public Edition getEdition() {
        return edition;
    }

    public Long getApUserId() {
        return ApUserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ApUserId != null ? ApUserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApUser)) {
            return false;
        }
        ApUser other = (ApUser) object;
        if ((this.ApUserId == null && other.ApUserId != null) || (this.ApUserId != null && !this.ApUserId.equals(other.ApUserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pt.uc.dei.paj.proj5.grupoF.Entity.ApUser[ id=" + ApUserId + " ]";
    }

}
