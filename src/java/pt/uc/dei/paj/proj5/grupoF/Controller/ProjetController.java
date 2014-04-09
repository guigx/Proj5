/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.Controller;

import java.util.Date;
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
import pt.uc.dei.paj.proj5.grupoF.Entity.ApUser;
import pt.uc.dei.paj.proj5.grupoF.Entity.Project;
import pt.uc.dei.paj.proj5.grupoF.Exception.InvalidDeleteEdition;
import pt.uc.dei.paj.proj5.grupoF.Facades.ProjectFacade;

/**
 *
 * @author Grupo F
 */
@Named
@RequestScoped
public class ProjetController {

    @Inject
    private ProjectFacade projectfacade;
    private String name;
    private String error;
    private Project project;
    @Inject
    private LoggedUser lg;
    private List<Project> projectOfEdition;
    private List<ApUser> StudentList;
    private Date initialDate;
    private Date currentDay = new Date();

    private ApUser[] selectedStudentsToSubcribeInProject;

    public ProjetController() {
    }

    @PostConstruct
    public void initProjectController() {
        this.project = new Project();
    }

    public List<ApUser> getStudentList() {
        return StudentList;
    }

    public void setStudentList(List<ApUser> StudentList) {
        this.StudentList = StudentList;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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

    public String createProject() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        List<Project> projectCollection = lg.getCurrentEdition().getProjectList();
        for (Project p : projectCollection) {
            if (p.getName().equals(project.getName())) {
                ctx.addMessage("newProject:nome", new FacesMessage("Não pode inserir uma projecto com nome repetido."));
                return null;
            }
        }
        projectfacade.addProject(project, lg.getCurrentEdition());
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

    public List<Project> getAllProjectOpen() {
        return projectfacade.findAllOpenByEdition(currentDay/*, lg.getLoggedUser().getApUserId()*/);
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

    public ApUser[] getSelectedStudentsToSubcribeInProject() {
        return selectedStudentsToSubcribeInProject;
    }

    public void setSelectedStudentsToSubcribeInProject(ApUser[] selectedStudentsToSubcribeInProject) {
        this.selectedStudentsToSubcribeInProject = selectedStudentsToSubcribeInProject;
    }

    public void subscribeStudentToProject() {
        projectfacade.subscribeApUsersToProject(selectedStudentsToSubcribeInProject, lg.getSelectedProject());
    }

    public void deleteProject(Project project) {
        try {
            projectfacade.deleteProject(lg.getCurrentEdition(), project);
        } catch (InvalidDeleteEdition ex) {
            Logger.getLogger(ProjetController.class.getName()).log(Level.SEVERE, null, ex);
            error = ex.getMessage();
        }
    }

    public String prepareProject(Project project) {
        lg.setSelectedProject(project);
        return "PrivateProject";
    }

}
