/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.uc.dei.paj.proj5.grupoF.Controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pt.uc.dei.paj.proj5.grupoF.Entity.Edition;
import pt.uc.dei.paj.proj5.grupoF.Facades.CriterionFacade;

/**
 *
 * @author Grupo F
 */
@Named
@RequestScoped
public class CriterionController {
    @Inject
    private CriterionFacade criterionfacade;
    private String name;
    private Edition edition;

    /**
     * Creates a new instance of CriterionController
     */
    public CriterionController() {
    }

    public String createCriterion() {
        if (criterionfacade.createCriterion(name, edition)) {
            return "paginaqualquer";   //pagina com a definicao de criterios. 
            //Podemos atribuir mensagem de erro
        }
        return null; //podemos devolver pagina de erro a informar que o criterio nao foi criado
    }
}
