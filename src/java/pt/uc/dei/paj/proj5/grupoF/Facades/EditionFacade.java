/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.Facades;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pt.uc.dei.paj.proj5.grupoF.Entity.Edition;
import pt.uc.dei.paj.proj5.grupoF.Entity.Project;
import pt.uc.dei.paj.proj5.grupoF.Exception.InvalidDeleteEdition;

/**
 *
 * @author Guilherme Pereira
 */
@Stateless
public class EditionFacade extends AbstractFacade<Edition> {

    @PersistenceContext(unitName = "Proj5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EditionFacade() {
        super(Edition.class);
    }

    public void createEdition(String name, int scale) {
        Edition newEd = new Edition();
        newEd.setName(name);
        System.out.println("NEW EDITION-------NAME" + newEd.getName());
        newEd.setScale(scale);
        System.out.println("NEW EDITION-------Scale" + newEd.getScale());
        newEd.setYearEdition(new Date());
        this.create(newEd);

    }

    /**
     * search if the name already exist
     *
     * @param name - name of edition
     * @return
     */
    public Edition findByName(String name) {
        Query q = em.createNamedQuery("Edition.findByName", Edition.class);
        q.setParameter("name", name);
        try {
            return (Edition) q.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;   //enviar para pagina de erro com informacao de que
            //nao existe objecto com este valor
        }
    }

    public boolean containEvaluation(Edition edition) {
        System.out.println("edition--------------------------------" + edition.getName());
        for (Project p : edition.getProjectList()) {
            if (!p.getEvaluationList().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public void deleteEdition(Edition edition) throws InvalidDeleteEdition {
        if (!containEvaluation(edition)) {
            this.remove(em.merge(edition));
        } else {
            throw new InvalidDeleteEdition();
        }
    }

}
