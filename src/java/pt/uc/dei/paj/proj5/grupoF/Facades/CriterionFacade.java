/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.uc.dei.paj.proj5.grupoF.Facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pt.uc.dei.paj.proj5.grupoF.Entity.Criterion;
import pt.uc.dei.paj.proj5.grupoF.Entity.Edition;

/**
 *
 * @author Guilherme Pereira
 */
@Stateless
public class CriterionFacade extends AbstractFacade<Criterion> {
    @PersistenceContext(unitName = "Proj5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CriterionFacade() {
        super(Criterion.class);
    }
    
     public boolean createCriterion(String question,Edition edition ) {
        this.create(new Criterion(question,edition));
        return true;
    }
    
    
}
