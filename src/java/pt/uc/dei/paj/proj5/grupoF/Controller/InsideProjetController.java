/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.Controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;
import pt.uc.dei.paj.proj5.grupoF.Entity.ApUser;
import pt.uc.dei.paj.proj5.grupoF.Entity.Project;

/**
 *
 * @author Grupo F
 */
@Named
@RequestScoped
public class InsideProjetController {

    private Project SelectedProj;
    private List<ApUser> studentListInProject;

    public InsideProjetController() {
    }

    @PostConstruct
    public void init() {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        setSelectedProj((Project) flash.get("project"));
    }

    public Project getSelectedProj() {
        return SelectedProj;
    }

    public void setSelectedProj(Project SelectedProj) {
        this.SelectedProj = SelectedProj;
    }

    public List<ApUser> getStudentListInProject() {
        return studentListInProject;
    }

    public void setStudentListInProject(List<ApUser> studentListInProject) {
        this.studentListInProject = studentListInProject;
    }

}
