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

    
    public List<Criterion> findAllCriterionByEdition(Edition edition) {
        TypedQuery<Criterion> q = em.createNamedQuery("Criterion.findAllByEdition", Criterion.class);
        q.setParameter("edition", edition);
        try {
            return q.getResultList(); // envia os criterios para tabela para atrubuir notas
        } catch (Exception e) {
            e.printStackTrace();
            return null;   //enviar para pagina de erro com informacao de que 
            //nao existe objecto com este valor
        }
    }
    
//    public void deleteCriterion(int id_criterion) {//tem de se verificar se temos algum criterio desta edicao preenchido
//        this.remove(find(id_criterion));
//    }

}
