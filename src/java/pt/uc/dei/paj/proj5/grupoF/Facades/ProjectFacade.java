/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.Facades;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pt.uc.dei.paj.proj5.grupoF.Entity.ApUser;
import pt.uc.dei.paj.proj5.grupoF.Entity.Edition;
import pt.uc.dei.paj.proj5.grupoF.Entity.Project;
import pt.uc.dei.paj.proj5.grupoF.Exception.InvalidDeleteEdition;

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

    public List<Project> findAllOpenByEdition(Date currentDay/*, long ApUserId*/) {
        TypedQuery<Project> q = em.createNamedQuery("Project.findByProjectOpen", Project.class);
        q.setParameter("currentDay", currentDay);
//        q.setParameter("ApUserId", ApUserId);
        try {
            return q.getResultList(); // envia os criterios para tabela para atrubuir notas
        } catch (Exception e) {
            e.printStackTrace();
            return null;   //enviar para pagina de erro com informacao de que
            //nao existe objecto com este valor
        }
    }

    public void updateProject(Project p) {
        this.edit(p);
    }

    public void subscribeApUsersToProject(ApUser[] apUsers, Project project) {

        for (int i = 0; i < apUsers.length; i++) {
            apUsers[i].getProjectList().add(project);
            em.merge(apUsers[i]);
            project.getApuserList().add(apUsers[i]);
        }
        em.merge(project);
    }

    public void deleteProject(Edition edition, Project project) throws InvalidDeleteEdition {
        if (!project.getEvaluationList().isEmpty()) {
            throw new InvalidDeleteEdition();
        } else {
            edition.getProjectList().remove(project);
            remove(project);
            em.merge(edition);
        }
    }

    public List<Project> closeProjectEdition() {
        TypedQuery<Project> q = em.createNamedQuery("Project.findByProjectClose", Project.class);
        q.setParameter("currentDay", new Date());
        return q.getResultList();
    }

    public List<Project> openProjectEdition() {
        TypedQuery<Project> q = em.createNamedQuery("Project.findByProjectOpen", Project.class);
        q.setParameter("currentDay", new Date());
        return q.getResultList();
    }
}
