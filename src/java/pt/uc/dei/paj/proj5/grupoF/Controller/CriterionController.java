/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.uc.dei.paj.proj5.grupoF.Controller;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pt.uc.dei.paj.proj5.grupoF.EJB.LoggedUser;
import pt.uc.dei.paj.proj5.grupoF.Entity.Criterion;
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
    @Inject
    private LoggedUser logged;
    

    /**
     * Creates a new instance of CriterionController
     */
    public CriterionController() {
    }

    public String createCriterion() {
        if (criterionfacade.createCriterion(name, logged.getCurrentEdition())) {
            System.out.println("logged "+ logged.getCurrentEdition());
            return "Criterion.xhtml";   //pagina com a definicao de criterios. 
                                        //Podemos atribuir mensagem de erro
        }
        return null; //podemos devolver pagina de erro a informar que o criterio nao foi criado
    }
    
    

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLogged(LoggedUser logged) {
        this.logged = logged;
    }

    public LoggedUser getLogged() {
        return logged;
    }

    public void setCriterionfacade(CriterionFacade criterionfacade) {
        this.criterionfacade = criterionfacade;
    }

    public CriterionFacade getCriterionfacade() {
        return criterionfacade;
    }
    
     public List<Criterion> getAllCriterion() {
        return criterionfacade.findAll();
    }
    
}
