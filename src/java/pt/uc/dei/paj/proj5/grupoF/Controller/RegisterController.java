/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.Controller;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import pt.uc.dei.paj.proj5.grupoF.EJB.EncriptPassword;
import pt.uc.dei.paj.proj5.grupoF.EJB.LoggedUser;
import pt.uc.dei.paj.proj5.grupoF.Entity.ApUser;
import pt.uc.dei.paj.proj5.grupoF.Entity.Edition;
import pt.uc.dei.paj.proj5.grupoF.Exception.UserNotFoundException;
import pt.uc.dei.paj.proj5.grupoF.Facades.ApUserFacade;
import pt.uc.dei.paj.proj5.grupoF.Facades.EditionFacade;

/**
 *
 * @author Grupo F
 */
@Named
@ConversationScoped
public class RegisterController implements Serializable {

    @Inject
    private Conversation conversation;
    private ApUser apuser;
    @Inject
    private LoggedUser lg;
    @Inject
    private ApUserFacade ejbUser;
    @Inject
    private EditionFacade editionFacade;
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private String editionname;

    private Edition edition;

    /**
     * Creates a new instance of EditionController
     */
    public RegisterController() {
    }

    public String createNewUser() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        if (!ejbUser.emailExists(apuser.getEmail())) {
            if (apuser.getPassword().equals(confirmPassword)) {
                //Encrypt password
                apuser.setPassword(EncriptPassword.md5(apuser.getPassword()));
//                ejbUser.createApUser(apuser, confirmPassword, edition);
                try {
                    ApUser loggedUser = ejbUser.getApUserByEmail(apuser.getEmail());
                    lg.setLoggedUser(loggedUser);
                    conversation.end();
                    return "Admin.xhtml?faces-redirect=true";
                } catch (UserNotFoundException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Erro na autenticação de utilizador.", ex);
                    return "Login.xhtml?faces-redirect=true";
                }
            }
            ctx.addMessage("newUser:password", new FacesMessage("As passwords não coincidem."));
            ctx.addMessage("newUser:confirmpassword", new FacesMessage("As passwords não coincidem."));
            return null;
        }
        ctx.addMessage("newUser:email", new FacesMessage("Esse email já está registado."));
        return null;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public EditionFacade getEditionFacade() {
        return editionFacade;
    }

    public void setEditionFacade(EditionFacade editionFacade) {
        this.editionFacade = editionFacade;
    }

    public String getName() {
        System.out.println("EDITION!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + name);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ApUserFacade getEjbUser() {
        return ejbUser;
    }

    public void setEjbUser(ApUserFacade ejbUser) {
        this.ejbUser = ejbUser;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public ApUser getApuser() {
        return apuser;
    }

    public void setApuser(ApUser apuser) {
        this.apuser = apuser;
    }

    public LoggedUser getLg() {
        return lg;
    }

    public void setLg(LoggedUser lg) {
        this.lg = lg;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public String goToRegister() {
        conversation.begin();
        return "Register";
    }

    public String goToLogin() {
        conversation.end();
        return "Login";
    }

    public List<Edition> getAllEdition() {
        return editionFacade.findAll();
    }

    public String getEditionname() {
        return editionname;
    }

    public void setEditionname(String editionname) {
        this.editionname = editionname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
