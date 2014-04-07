/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.Controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import pt.uc.dei.paj.proj5.grupoF.EJB.LoggedUser;
import pt.uc.dei.paj.proj5.grupoF.Entity.Project;
import pt.uc.dei.paj.proj5.grupoF.Facades.ProjectFacade;

/**
 *
 * @author Grupo F
 */
@Named
@ViewScoped
public class ProjetController implements Serializable {

    @Inject
    private ProjectFacade projectfacade;
    private String name;
    private Project project;
    @Inject
    private LoggedUser lg;
    private List<Project> projectOfEdition;

    private Date initialDate;

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }
    private Date finalDate;

    public ProjetController() {
    }

    @PostConstruct
    public void initProject() {
        this.project = new Project();
    }

    public String createProject() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        List<Project> projectCollection = lg.getCurrentEdition().getProjectList();
//        project.setInitialDate(initialDate);
//        project.setFinalDate(finalDate);
//        project.setName(name);
        for (Project p : projectCollection) {
            if (p.getName().equals(project.getName())) {
                ctx.addMessage("newProject:nome", new FacesMessage("Não pode inserir uma projecto com nome repetido."));
                return null;
            }
        }
        project.setEdition(lg.getCurrentEdition());
        this.projectfacade.create(project);
        return "Project.xhtml?faces-redirect=true";
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public LoggedUser getLg() {
        return lg;
    }

    public void setLg(LoggedUser lg) {
        this.lg = lg;
    }

    public List<Project> getProjectOfEdition() {
        return projectfacade.projectsOfAnEdition(lg.getCurrentEdition().getId());
    }

    public void setProjectOfEdition(List<Project> projectOfEdition) {
        this.projectOfEdition = projectOfEdition;
    }

    public List<Project> getAllProject() {
        return projectfacade.findAll();
    }

    public ProjectFacade getProjectfacade() {
        return projectfacade;
    }

    public void setProjectfacade(ProjectFacade projectfacade) {
        this.projectfacade = projectfacade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
