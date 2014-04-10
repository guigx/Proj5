/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.Facades;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pt.uc.dei.paj.proj5.grupoF.Entity.ApUser;
import pt.uc.dei.paj.proj5.grupoF.Entity.Criterion;
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

    public List<Project> noSubmitedProject(ApUser student) {
        List<Project> noSubmited = new ArrayList<>();
        for (Project project : student.getProjectList()) {
            if (findStudentProjectEvaluation(student, project).isEmpty()) {
                noSubmited.add(project);
            }
        }
        return noSubmited;
    }

    public List<Project> submitedProject(ApUser student) {
        List<Project> submited = new ArrayList<>();
        for (Project project : student.getProjectList()) {
            if (!findStudentProjectEvaluation(student, project).isEmpty()) {
                submited.add(project);
            }
        }
        return submited;
    }

    /**
     * Create a list of evaluations from that student to a defined project
     *
     * @param student
     * @param p (project)
     * @return Evaluation's list
     */
    public List<Evaluation> studentEvaluationsSetCriteria(ApUser student, Project p) {
        if (findStudentProjectEvaluation(student, p).isEmpty()) {
            List<Evaluation> studentEvaluations = new ArrayList<>();
            for (Criterion c : student.getEdition().getCriterionList()) {
                Evaluation evaluation = new Evaluation();
                evaluation.setCriterion(c);
                evaluation.setRating(0);
                evaluation.setApUser(student);
                evaluation.setProject(p);
                studentEvaluations.add(evaluation);
            }
            return studentEvaluations;
        } else {
            return findStudentProjectEvaluation(student, p);
        }
    }

    /**
     * Persit the List of Evaluations from 1student about 1project
     *
     * @param envaluationList
     */
    public void evaluationsSubmit(List<Evaluation> envaluationList) {
        for (Evaluation e : envaluationList) {
            create(e);
            e.getProject().getEvaluationList().add(e);
            e.getApUser().getEvaluationList().add(e);
            e.getCriterion().getEvaluationList().add(e);
            em.merge(e.getProject());
            em.merge(e.getApUser());
            em.merge(e.getCriterion());
        }

    }

}
