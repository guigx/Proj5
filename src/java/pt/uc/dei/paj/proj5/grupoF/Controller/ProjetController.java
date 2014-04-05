/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.Controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
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
@RequestScoped
public class ProjetController {

    @Inject
    private ProjectFacade projectfacade;
    private String name;
    private Project project;
    @Inject
    private LoggedUser lg;

    public ProjetController() {
    }

    @PostConstruct
    public void initProject() {
        this.project = new Project();
    }

//    public String createPlaylist() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        List<Project> projectCollection = lg.getLoggedUser().getAllProject();
//
//        for (Project p : projectCollection) {
//            if (p.getName().equals(project.getName())) {
//                ctx.addMessage("newPlaylist:nome", new FacesMessage("Não pode inserir uma playlist com nome repetido."));
//                return null;
//            }
//        }
//
//        playlist.setApUser(lg.getLoggedUser());
//        this.ejbPlaylist.create(playlist);
//        //refresh the user
////        lg.getLoggedUser().getPlaylistCollection().add(playlist);
////        this.ejbUser.edit(lg.getLoggedUser());
//        return "/playlist/PlayListPrivat?faces-redirect=true";
//    }
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
