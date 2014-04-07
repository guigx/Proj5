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

}
