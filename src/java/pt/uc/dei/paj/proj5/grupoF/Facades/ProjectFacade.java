/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.Facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pt.uc.dei.paj.proj5.grupoF.Entity.ApUser;
import pt.uc.dei.paj.proj5.grupoF.Entity.Edition;
import pt.uc.dei.paj.proj5.grupoF.Entity.Project;

/**
 *
 * @author Guilherme Pereira
 */
@Stateless
public class ProjectFacade extends AbstractFacade<Project> {

    @PersistenceContext(unitName = "Proj5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProjectFacade() {
        super(Project.class);
    }

    public List<Project> projectsOfAnEdition(Long idEdition) {
        Edition edition = em.find(Edition.class, idEdition);
        return edition.getProjectList();
    }

    public void addProject(Project p, Edition edition) {
        edition.getProjectList().add(p);
        p.setEdition(edition);
        this.create(p);
        em.merge(edition);
    }

    public void updateProject(Project p) {
        this.edit(p);
    }

    public void subscribeApUsersToProject(ApUser[] apUsers, Project project) {

        for (int i = 0; i < apUsers.length; i++) {
            project.getApuserList().add(apUsers[i]);
            em.merge(project);

            apUsers[i].getProjectList().add(project);
            em.merge(apUsers[i]);
            //apUserFacade.edit(apUsers[i]);
            //projectFacade.updateProject(project);
            System.out.println("adicionado cenassasssssssssssssssss");
        }

    }
    /*
     public ArrayList<ApUser> getStudentsSubscribedInProject(Project p) {
     ArrayList<ApUser> users = em.find(Project.class, p)
     }
     */
}
