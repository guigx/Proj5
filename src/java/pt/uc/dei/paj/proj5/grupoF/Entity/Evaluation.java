/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Guilherme Pereira
 */
@NamedQueries({
    @NamedQuery(name = "Evaluation.findByIdEdition", query = "SELECT e FROM Evaluation e WHERE e.project.edition.id = :id_edition"),
    @NamedQuery(name = "Evaluation.findStudentProject", query = "SELECT e FROM Evaluation e WHERE e.apUser.ApUserId = :apUserId and e.project.id = :projectId"),
    @NamedQuery(name = "Evaluation.avgAdminStudentProject", query = "SELECT AVG(u.rating), u.apUser.name, u.project.name FROM Evaluation u WHERE u.project.edition = :edition group by u.apUser.name, u.project.name"),
    // @NamedQuery(name = "Evaluation.avgProj", query = "SELECT AVG(u.rating) FROM Evaluation u WHERE u.project.id = :projectId"),
    @NamedQuery(name = "Evaluation.avgStudentEachCriterionEdition", query = "SELECT AVG(u.rating), u.criterion.question FROM Evaluation u WHERE u.apUser.ApUserId = :apuserId group by u.criterion.question"),
    @NamedQuery(name = "Evaluation.avgEachCriterionEachProj", query = "SELECT AVG(u.rating), u.criterion.question, u.project.name FROM Evaluation u WHERE u.project.edition = :edition group by u.criterion.question, u.project.name"),
    @NamedQuery(name = "Evaluation.avgEachProjInEdition", query = "SELECT AVG(u.rating), u.project.name FROM Evaluation u WHERE u.project.edition = :edition group by u.project.name"),
    @NamedQuery(name = "Evaluation.avgEachCriterionInEdition", query = "SELECT AVG(u.rating), u.criterion.question, u.project.edition.name FROM Evaluation u WHERE u.project.edition = :edition group by u.criterion.question"),
    @NamedQuery(name = "Evaluation.avgEachProjEdition", query = "SELECT AVG(u.rating), u.project.name, u.project.edition.name FROM Evaluation u WHERE u.project.edition = :edition group by u.project.name"),
    @NamedQuery(name = "Evaluation.avgAdminStdEachProj", query = "SELECT AVG(u.rating), u.project.name FROM Evaluation u WHERE u.apUser = :apuser group by u.project.name")})
@Entity
public class Evaluation implements Serializable {

    //VER ID COMPOSTO
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private ApUser apUser;

    @ManyToOne
    private Criterion criterion;

    @ManyToOne
    private Project project;

    @NotNull
    private int rating;          //nota avaliacao

    public Evaluation() {
    }

    public Evaluation(ApUser apUser, Criterion criterion, Project project, int rating) {
        this.apUser = apUser;
        this.criterion = criterion;
        this.project = project;
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ApUser getApUser() {
        return apUser;
    }

    public void setApUser(ApUser apUser) {
        this.apUser = apUser;
    }

    public Criterion getCriterion() {
        return criterion;
    }

    public void setCriterion(Criterion criterion) {
        this.criterion = criterion;
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
        if (!(object instanceof Evaluation)) {
            return false;
        }
        Evaluation other = (Evaluation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pt.uc.dei.paj.proj5.grupoF.Entity.Evaluation[ id=" + id + " ]";
    }

}
