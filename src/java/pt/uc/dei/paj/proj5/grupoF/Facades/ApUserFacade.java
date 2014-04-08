/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.Facades;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import pt.uc.dei.paj.proj5.grupoF.Entity.ApUser;
import pt.uc.dei.paj.proj5.grupoF.Entity.Edition;
import pt.uc.dei.paj.proj5.grupoF.Exception.InvalidAuthException;
import pt.uc.dei.paj.proj5.grupoF.Exception.UserNotFoundException;

/**
 *
 * @author Grupo F
 */
@Stateless
public class ApUserFacade extends AbstractFacade<ApUser> {

    @PersistenceContext(unitName = "Proj5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ApUserFacade() {
        super(ApUser.class);
    }

    public ApUser getApUserByEmail(String email) throws UserNotFoundException {
        try {

            ApUser u = (ApUser) em.createNamedQuery("ApUser.findByEmail").setParameter("email", email).getSingleResult();
            System.out.println("u!!!!!!!!!!!!!!!!!!!!" + u);
            return u;
        } catch (NoResultException ex) {
            Logger.getLogger(ApUserFacade.class.getName()).log(Level.SEVERE, "Erro na procura de utilizador por email.", ex);
            throw new UserNotFoundException();
        }
    }

    public boolean emailExists(String email) {
        try {
            getApUserByEmail(email);
            return true;
        } catch (UserNotFoundException ex) {
            return false;
        }
    }

    /**
     * Checks if a email and a password inserted by a user are a valid
     * authentication to enter the application.
     *
     * @param email
     * @param password
     * @return The authenticated user.
     * @throws InvalidAuthException
     * @throws UserNotFoundException
     */
    public ApUser validAuthenticationApuser(String email, String password) throws InvalidAuthException, UserNotFoundException {
        ApUser apuser = getApUserByEmail(email);
        if (password.equals(apuser.getPassword())) {
            return apuser;
        } else {
            Logger.getLogger(ApUserFacade.class.getName()).log(Level.SEVERE, "Erro na autenticação de utilizador.");
            throw new InvalidAuthException("Password inválida.");
        }
    }

    
//    public boolean


    public void createApUser(ApUser apuser, String confirmPassword, Edition edition) {
        create(apuser);
        apuser.setEdition(edition);
        edition.getUserList().add(apuser);
        edit(apuser);
        em.merge(edition);

    }
}
