/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.EJB;

import java.io.Serializable;
import javax.ejb.Stateful;
import javax.inject.Inject;
import pt.uc.dei.paj.proj5.grupoF.Facades.EvaluationFacade;

/**
 *
 * @author Grupo F
 */
@Stateful
public class Check implements Serializable {

//    @Inject
//    private EditionFacade editionfacade;
    @Inject
    private EvaluationFacade evaluationfacade;
//    @Inject
//    private ProjectFacade projectFacade;
    @Inject
    private LoggedUser lg;

    public boolean checked() {
        return evaluationfacade.findEvaluationByIdEdition(lg.getCurrentEdition().getId()) == 0;

    }

    public EvaluationFacade getEvaluationfacade() {
        return evaluationfacade;
    }

}
