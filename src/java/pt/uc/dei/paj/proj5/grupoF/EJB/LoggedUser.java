/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.EJB;

import java.io.Serializable;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import pt.uc.dei.paj.proj5.grupoF.Entity.ApUser;

/**
 *
 * @author Grupo F
 */
@Stateful
@SessionScoped
public class LoggedUser implements Serializable {

    private static final long serialVersionUID = 1L;
    private ApUser loggedUser;

    public LoggedUser() {
    }

    public ApUser getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(ApUser loggedUser) {
        this.loggedUser = loggedUser;
    }

}