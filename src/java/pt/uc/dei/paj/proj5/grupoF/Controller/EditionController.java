/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.Controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pt.uc.dei.paj.proj5.grupoF.Entity.Criterion;
import pt.uc.dei.paj.proj5.grupoF.Entity.Edition;
import pt.uc.dei.paj.proj5.grupoF.Facades.EditionFacade;

/**
 *
 * @author Grupo F
 */
@Named
@RequestScoped

public class EditionController {

    @Inject
    private EditionFacade editionfacade;
    private String name;
    private int scale;
    private List<Criterion> criterionList;
    private Edition edition;

    /**
     * Creates a new instance of EditionController
     */
    public EditionController() {
    }
    
    @PostConstruct
    public void initEdition(){
        this.edition=new Edition();
    }

    public String createEdition() {
        if (editionfacade.findByName(name) == null) {
            editionfacade.createEdition(name, scale);
            return "AdminPrincipal"; 
        }
        return null;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Edition> getAllEdition() {
        return editionfacade.findAll();
    }

    public EditionFacade getEditionfacade() {
        return editionfacade;
    }

    public void setEditionfacade(EditionFacade editionfacade) {
        this.editionfacade = editionfacade;
    }

    public List<Criterion> getCriterionList() {
        return criterionList;
    }

    public void setCriterionList(List<Criterion> criterionList) {
        this.criterionList = criterionList;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

}
