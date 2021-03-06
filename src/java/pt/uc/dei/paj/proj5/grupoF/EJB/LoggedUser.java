/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.EJB;

import java.io.Serializable;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import pt.uc.dei.paj.proj5.grupoF.Entity.ApAdmin;
import pt.uc.dei.paj.proj5.grupoF.Entity.ApUser;
import pt.uc.dei.paj.proj5.grupoF.Entity.Edition;
import pt.uc.dei.paj.proj5.grupoF.Entity.Project;

/**
 *
 * @author Grupo F
 */
@Named
@SessionScoped
@Stateful
public class LoggedUser implements Serializable {

    private static final long serialVersionUID = 1L;
    private ApUser loggedUser;
    private ApAdmin loggedAdmin;
    private Edition currentEdition;
    private Project selectedProject;

    public LoggedUser() {
    }

    public ApAdmin getLoggedAdmin() {
        return loggedAdmin;
    }

    public void setLoggedAdmin(ApAdmin loggedAdmin) {
        this.loggedAdmin = loggedAdmin;
    }

    public ApUser getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(ApUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    public Edition getCurrentEdition() {
        return currentEdition;
    }

    public void setCurrentEdition(Edition currentEdition) {
        this.currentEdition = currentEdition;
    }

    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

}
