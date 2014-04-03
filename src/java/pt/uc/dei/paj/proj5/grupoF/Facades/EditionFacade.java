/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.Facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pt.uc.dei.paj.proj5.grupoF.Entity.Edition;

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
    
    public boolean createEdition(String name, int scale){
        this.create(new Edition(name, scale));
        return true;
    }

    /**
     * search if the name already exist
     * @param name - name of edition
     * @return 
     */
    public Edition findByName(String name) {
        TypedQuery<Edition> q = em.createNamedQuery("Edition.getEditionByName", Edition.class);
        q.setParameter("name", name);
        try {
            return q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
