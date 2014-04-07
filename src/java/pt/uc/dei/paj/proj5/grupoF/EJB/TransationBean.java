/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.uc.dei.paj.proj5.grupoF.EJB;

import java.io.Serializable;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pt.uc.dei.paj.proj5.grupoF.Facades.EditionFacade;
import pt.uc.dei.paj.proj5.grupoF.Facades.EvaluationFacade;

/**
 *
 * @author Grupo F
 */
@Stateful
@RequestScoped
public class TransationBean  implements Serializable {
    @Inject
    private EditionFacade editionfacade;
    @Inject
    private EvaluationFacade evaluationfacade;
    
    
    public void deleteEditionTransation(Long selectedEdition) {
        if (evaluationfacade.findEvaluationByIdEdition(selectedEdition) == 0) {
            editionfacade.deleteEdition(selectedEdition);
        }
    }
}
