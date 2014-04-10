/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.Controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import pt.uc.dei.paj.proj5.grupoF.EJB.LoggedUser;
import pt.uc.dei.paj.proj5.grupoF.EJB.Check;
import pt.uc.dei.paj.proj5.grupoF.Entity.Criterion;
import pt.uc.dei.paj.proj5.grupoF.Entity.Edition;
import pt.uc.dei.paj.proj5.grupoF.Entity.Project;
import pt.uc.dei.paj.proj5.grupoF.Exception.InvalidDeleteEdition;
import pt.uc.dei.paj.proj5.grupoF.Facades.EditionFacade;

/**
 *
 * @author Grupo F
 */
@Named
@RequestScoped
public class EditionController {

    @Inject
    private EditionFacade editionfacade;
//    @Inject
//    private EvaluationFacade evaluationfacade;
    @Inject
    private LoggedUser lg;
    @Inject
    private Check transation;
    private String name;
    private String error;
    private int scale;
    private List<Criterion> criterionList;
    private Edition edition;
    private Edition selectedEdition;
    private List<Project> projectList;

    /**
     * Creates a new instance of EditionController
     */
    public EditionController() {
    }

    @PostConstruct
    public void initEdition() {
        this.edition = new Edition();
    }

    public String EditionLogin(Edition edition) {
        lg.setCurrentEdition(edition);
        return "Project.xhtml?faces-redirect=true";
    }

    //create now edition
    public String createEdition() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        if (editionfacade.findByName(name) == null) {
            if (scale != 0) {

                editionfacade.createEdition(name, scale);
                return "AdminPrincipal";
            } else {
                ctx.addMessage("createEdition", new FacesMessage("Scale null!!"));
                return "AdminPrincipal";
            }
        }
        ctx.addMessage("createEdition", new FacesMessage("Project already existe!"));
        return null;
    }

//    //clears list if no review
//    public void deleteEdition() {
//        transation.deleteEditionTransation(selectedEdition.getId());
//    }
    public List<Project> getProjectList() {
        return projectList;
    }

    public Edition getEdition() {

        return edition;

    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Edition> getAllEdition() {
        return editionfacade.findAll();
    }

    public EditionFacade getEditionfacade() {
        return editionfacade;
    }

    public void setEditionfacade(EditionFacade editionfacade) {
        this.editionfacade = editionfacade;
    }

    public List<Criterion> getCriterionList() {
        return criterionList;
    }

    public void setCriterionList(List<Criterion> criterionList) {
        this.criterionList = criterionList;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public Edition getSelectedEdition() {
        return selectedEdition;
    }

    public void setSelectedEdition(Edition selectedEdition) {
        this.selectedEdition = selectedEdition;
    }

    public Check getTransation() {
        return transation;
    }

    public void setTransation(Check transation) {
        this.transation = transation;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public LoggedUser getLg() {
        return lg;
    }

    public void setLg(LoggedUser lg) {
        this.lg = lg;
    }

    public void removeEdition(Edition ed) {
        try {
            editionfacade.deleteEdition(ed);
        } catch (InvalidDeleteEdition e) {
            Logger.getLogger(EditionController.class.getName()).log(Level.SEVERE, null, e);
            error = e.getMessage();
        }
    }
}
