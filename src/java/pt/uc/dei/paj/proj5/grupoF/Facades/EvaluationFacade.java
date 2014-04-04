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
import javax.persistence.TypedQuery;
import pt.uc.dei.paj.proj5.grupoF.Entity.Evaluation;

/**
 *
 * @author Guilherme Pereira
 */
@Stateless
public class EvaluationFacade extends AbstractFacade<Evaluation> {

    @PersistenceContext(unitName = "Proj5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EvaluationFacade() {
        super(Evaluation.class);
    }

    //create new evaluation from a project
    public void createEvaluation() {

        
        
    }

    public List <Evaluation> findEvaluationByIdEdition(long id_edition) {
        TypedQuery<Evaluation> q = em.createNamedQuery("Evaluation.findByIdEdition", Evaluation.class);
        q.setParameter("id_edition", id_edition);
        try {
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;   //enviar para pagina de erro com informacao de que 
            //nao existe objecto com este valor
        }
    }
}
