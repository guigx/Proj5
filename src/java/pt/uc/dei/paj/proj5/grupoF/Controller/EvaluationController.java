/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.Controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
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
@ViewScoped
public class EvaluationController {

    @Inject
    private EvaluationFacade evaluationfacade;

    private Evaluation evaluation;
    private List<Evaluation> evaluationList;
    private ApUser apUser;
    private ApUser apUserSelected;
    private Criterion criterion;
    private Project project;
    private int rating;
    @Inject
    private LoggedUser lg;

    public EvaluationController() {
    }

    @PostConstruct
    public void initEvaluationController() {

        this.evaluation = new Evaluation();

    }

    public LoggedUser getLg() {
        return lg;
    }

    public void setLg(LoggedUser lg) {
        this.lg = lg;
    }

    public ApUser getApUser() {
        return apUser;
    }

    public ApUser getApUserSelected() {
        return apUserSelected;
    }

    public void setApUserSelected(ApUser apUserSelected) {
        this.apUserSelected = apUserSelected;
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

    public EvaluationFacade getEvaluationfacade() {
        return evaluationfacade;
    }

    public void setEvaluationfacade(EvaluationFacade evaluationfacade) {
        this.evaluationfacade = evaluationfacade;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public List<Evaluation> getEvaluationList() {
        return evaluationList;
    }

    public void setEvaluationList(List<Evaluation> evaluationList) {
        this.evaluationList = evaluationList;
    }

    public String studentStatus(ApUser student) {
        if (evaluationfacade.findStudentProjectEvaluation(student, lg.getSelectedProject()).isEmpty()) {
            return "Send Mail";
        } else {
            return "Submited";
        }
    }

    public String status(ApUser student) {
        apUserSelected = student;
        if (studentStatus(student).equalsIgnoreCase("Submited")) {
            evaluationList = evaluationfacade.findStudentProjectEvaluation(student, lg.getSelectedProject());
            return "PF('Result').show();";
        } else {
            return "PF('Email').show();";
        }
    }

    public String saveProject(Project project) {
        lg.setSelectedProject(project);
        this.evaluationList = evaluationfacade.studentEvaluationsSetCriteria(lg.getLoggedUser(), project);
        return "PF('submitAv').show();";
    }

    public List<Evaluation> evaluationListStudentProject() {
        this.evaluationList = evaluationfacade.studentEvaluationsSetCriteria(lg.getLoggedUser(), lg.getSelectedProject());
        return this.evaluationList;
    }

    public void validateEvaluation() {
        evaluationfacade.evaluationsSubmit(evaluationList);
    }

    public void prepareResults(ApUser student) {

        student = lg.getLoggedUser();
        evaluationList = evaluationfacade.findStudentProjectEvaluation(student, lg.getSelectedProject());
    }

//    public List<Evaluation> givenEvaluations() {
//        return evaluationfacade.findStudentProjectEvaluation(lg.getLoggedUser(), lg.getSelectedProject());
//    }
}
