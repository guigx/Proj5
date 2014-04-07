/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.Controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import pt.uc.dei.paj.proj5.grupoF.EJB.EncriptPassword;
import pt.uc.dei.paj.proj5.grupoF.EJB.LoggedUser;
import pt.uc.dei.paj.proj5.grupoF.Entity.ApAdmin;
import pt.uc.dei.paj.proj5.grupoF.Entity.ApUser;
import pt.uc.dei.paj.proj5.grupoF.Exception.InvalidAuthException;
import pt.uc.dei.paj.proj5.grupoF.Exception.UserNotFoundException;
import pt.uc.dei.paj.proj5.grupoF.Facades.ApAdminFacade;
import pt.uc.dei.paj.proj5.grupoF.Facades.ApUserFacade;

/**
 *
 * @author Grupo F
 */
@Named
@RequestScoped
public class UserController {

    @Inject
    private ApUserFacade ejbUser;
    @Inject
    private ApAdminFacade ejbAdmin;
    @Inject
    private LoggedUser lg;
    private ApUser apuser;
    private ApAdmin apadmin;
    private String confirmPassword;
    private String email;
    private String password;

    public UserController() {
    }

    public ApAdmin getAdmin() {
        if (apadmin == null) {
            apadmin = new ApAdmin();
        }
        return apadmin;
    }

    public ApUser getApuser() {
        if (apuser == null) {
            apuser = new ApUser();
        }
        return apuser;
    }

    public ApAdminFacade getEjbAdmin() {
        return ejbAdmin;
    }

    public void setEjbAdmin(ApAdminFacade ejbAdmin) {
        this.ejbAdmin = ejbAdmin;
    }

    public ApAdmin getApadmin() {
        return apadmin;
    }

    public void setApadmin(ApAdmin apadmin) {
        this.apadmin = apadmin;
    }

    public ApUserFacade getEjbUser() {
        return ejbUser;
    }

    public void setEjbUser(ApUserFacade ejbUser) {
        this.ejbUser = ejbUser;
    }

    public LoggedUser getLg() {
        return lg;
    }

    public void setLg(LoggedUser lg) {
        this.lg = lg;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    /**
     * Checks if the email and password inserted are valid authentication.
     *
     * @return The next page if the authentication is valid, null otherwise.
     */
    public String userLoginAdmin() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        //Encrypt password
        password = EncriptPassword.md5(password);
        System.out.println("pass - " + password);
        try {
            ApAdmin loggedAdmin = ejbAdmin.validAuthenticationApadmin(email, password);
            lg.setLoggedAdmin(loggedAdmin);
            return "/AdminPrincipal?faces-redirect=true";
        } catch (InvalidAuthException | UserNotFoundException ex) {
            ctx.addMessage("admin", new FacesMessage("Email ou password inválidos."));
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Checks if the email and password inserted are valid authentication.
     *
     * @return The next page if the authentication is valid, null otherwise.
     */
    public String userLoginUser() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        //Encrypt password
        password = EncriptPassword.md5(password);
        try {
            ApUser loggedUser = ejbUser.validAuthenticationApuser(email, password);
            lg.setLoggedUser(loggedUser);
            return "/novo?faces-redirect=true";
        } catch (InvalidAuthException | UserNotFoundException ex) {
            ctx.addMessage("login", new FacesMessage("Email ou password inválidos."));
            return null;
        }
    }

    /**
     * Creates a new user. Checks if the email inserted is valid. If the user is
     * correctly created, is logged in the application.
     *
     * @return The next page if the user is created, null otherwise.
     */
    public String createNewUser() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        if (!ejbUser.emailExists(apuser.getEmail())) {
            if (apuser.getPassword().equals(confirmPassword)) {
                //Encrypt password
                apuser.setPassword(EncriptPassword.md5(apuser.getPassword()));
                ejbUser.create(apuser);
                try {
                    ApUser loggedUser = ejbUser.getApUserByEmail(apuser.getEmail());
                    lg.setLoggedUser(loggedUser);
                    return "/Principal?faces-redirect=true";
                } catch (UserNotFoundException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Erro na autenticação de utilizador.", ex);
                    return "/Login?faces-redirect=true";
                }
            }
            ctx.addMessage("newUser:password", new FacesMessage("As passwords não coincidem."));
            ctx.addMessage("newUser:confirmpassword", new FacesMessage("As passwords não coincidem."));
            return null;
        }
        ctx.addMessage("newUser:email", new FacesMessage("Esse email já está registado."));
        return null;
    }
}
