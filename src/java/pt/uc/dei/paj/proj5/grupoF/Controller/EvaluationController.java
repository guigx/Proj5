/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.Controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pt.uc.dei.paj.proj5.grupoF.EJB.LoggedUser;
import pt.uc.dei.paj.proj5.grupoF.Entity.ApUser;
import pt.uc.dei.paj.proj5.grupoF.Entity.Criterion;
import pt.uc.dei.paj.proj5.grupoF.Entity.Evaluation;
import pt.uc.dei.paj.proj5.grupoF.Entity.Project;
import pt.uc.dei.paj.proj5.grupoF.Facades.EvaluationFacade;

/**
 *
 * @author Grupo F
 */
@Named
@RequestScoped
public class EvaluationController {

    @Inject
    private EvaluationFacade evaluationfacade;
    private Evaluation evaluation;
    private ApUser apUser;
    private Criterion criterion;
    private Project project;
    private int rating;
    private LoggedUser lg;

    public EvaluationController() {
    }

    @PostConstruct
    public void initEvaluationController() {
        this.evaluation = new Evaluation();
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LoggedUser getLg() {
        return lg;
    }

    public void setLg(LoggedUser lg) {
        this.lg = lg;
    }

    public String StudentStatus(ApUser student) {

        //setProject(lg.getSelectedProject());
        if (evaluationfacade.findStudentProjectEvaluation(student, project).isEmpty()) {
            return "Send Mail";
        } else {
            return "Submited";
        }
    }

    public void status(String value) {
        if (value.equalsIgnoreCase("Submited")) {

        } else {

        }
    }
}
