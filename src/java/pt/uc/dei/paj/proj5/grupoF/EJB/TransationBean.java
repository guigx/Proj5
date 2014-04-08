/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.EJB;

import java.io.Serializable;
import javax.ejb.Stateful;
import javax.inject.Inject;
import pt.uc.dei.paj.proj5.grupoF.Facades.ApUserFacade;
import pt.uc.dei.paj.proj5.grupoF.Facades.EditionFacade;
import pt.uc.dei.paj.proj5.grupoF.Facades.EvaluationFacade;
import pt.uc.dei.paj.proj5.grupoF.Facades.ProjectFacade;

/**
 *
 * @author Grupo F
 */
@Stateful
public class TransationBean implements Serializable {

    @Inject
    private EditionFacade editionfacade;
    @Inject
    private EvaluationFacade evaluationfacade;
    @Inject
    private ProjectFacade projectFacade;
    @Inject
    private ApUserFacade apUserFacade;

    public void deleteEditionTransation(Long selectedEdition) {
        if (evaluationfacade.findEvaluationByIdEdition(selectedEdition) == 0) {
            editionfacade.deleteEdition(selectedEdition);
        }
    }

}
