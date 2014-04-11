/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.Controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pt.uc.dei.paj.proj5.grupoF.EJB.LoggedUser;
import pt.uc.dei.paj.proj5.grupoF.Entity.ApUser;
import pt.uc.dei.paj.proj5.grupoF.Entity.Criterion;
import pt.uc.dei.paj.proj5.grupoF.Entity.Evaluation;
import pt.uc.dei.paj.proj5.grupoF.Entity.Log;
import pt.uc.dei.paj.proj5.grupoF.Entity.Project;
import pt.uc.dei.paj.proj5.grupoF.Facades.EvaluationFacade;
import pt.uc.dei.paj.proj5.grupoF.Facades.LogFacade;

/**
 *
 * @author Grupo F
 */
@Named
@ViewScoped
public class EvaluationController implements Serializable {

    @Inject
    private EvaluationFacade evaluationfacade;
    @Inject
    private LogFacade logfacade;
    private Evaluation evaluation;
    private List<Evaluation> evaluationList;
    private ApUser apUser;
    private ApUser apUserSelected;
    private Criterion criterion;
    private Project project;
    private int rating;
    private Log log;
    @Inject
    private LoggedUser lg;

    public EvaluationController() {
    }

    @PostConstruct
    public void initEvaluationController() {
        this.evaluation = new Evaluation();
        this.log = new Log();
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public LogFacade getLogfacade() {
        return logfacade;
    }

    public void setLogfacade(LogFacade logfacade) {
        this.logfacade = logfacade;
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
        //apUserSelected = student;
        if (studentStatus(student).equalsIgnoreCase("Submited")) {
            evaluationList = evaluationfacade.findStudentProjectEvaluation(student, lg.getSelectedProject());
            return "PF('Result').show();";
        } else {
            return "PF('Email').show();";
        }
    }

    public String disableLink(ApUser student) {
        if (studentStatus(student).equals("Submited")) {
            return "true";
        }
        return "false";
    }

    public void prepareResultsView(Project project) {
        evaluationList = evaluationfacade.findStudentProjectEvaluation(lg.getLoggedUser(), project);
    }

    public String saveProject(Project project) {
        lg.setSelectedProject(project);
        this.evaluationList = evaluationfacade.studentEvaluationsSetCriteria(lg.getLoggedUser(), project);
        log.setApUserId(lg.getLoggedUser().getApUserId());
        log.setLogOperation("Open Evaluation");
        logfacade.createLog(log);
        return "PF('submitAv').show();";
    }

    public List<Evaluation> evaluationListStudentProject() {
        this.evaluationList = evaluationfacade.studentEvaluationsSetCriteria(lg.getLoggedUser(), lg.getSelectedProject());
        return this.evaluationList;
    }

    public void validateEvaluation() {
        evaluationfacade.evaluationsSubmit(evaluationList);
        log.setApUserId(lg.getLoggedUser().getApUserId());
        log.setLogOperation("Save Evaluation");
        logfacade.createLog(log);
    }

    public void prepareResults(ApUser student) {
        student = lg.getLoggedUser();
        evaluationList = evaluationfacade.findStudentProjectEvaluation(student, lg.getSelectedProject());
    }

//    public List<Evaluation> givenEvaluations() {
//        return evaluationfacade.findStudentProjectEvaluation(lg.getLoggedUser(), lg.getSelectedProject());
//    }
}
