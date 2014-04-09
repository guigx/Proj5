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
import pt.uc.dei.paj.proj5.grupoF.Entity.ApUser;
import pt.uc.dei.paj.proj5.grupoF.Entity.Evaluation;
import pt.uc.dei.paj.proj5.grupoF.Entity.Project;

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

    public int findEvaluationByIdEdition(long id_edition) {
        TypedQuery<Evaluation> q = em.createNamedQuery("Evaluation.findByIdEdition", Evaluation.class);
        q.setParameter("id_edition", id_edition);
        return q.getResultList().size();
    }

    public List<Evaluation> findStudentProjectEvaluation(ApUser student, Project project) {
        TypedQuery<Evaluation> q = em.createNamedQuery("Evaluation.findStudentProject", Evaluation.class);
        q.setParameter("apUserId", student.getApUserId()).setParameter("projectId", project.getId());
        return q.getResultList();
    }
}
