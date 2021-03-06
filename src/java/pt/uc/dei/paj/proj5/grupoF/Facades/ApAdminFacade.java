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
import pt.uc.dei.paj.proj5.grupoF.Entity.ApAdmin;
import pt.uc.dei.paj.proj5.grupoF.Exception.InvalidAuthException;
import pt.uc.dei.paj.proj5.grupoF.Exception.UserNotFoundException;

/**
 *
 * @author Guilherme Pereira
 */
@Stateless
public class ApAdminFacade extends AbstractFacade<ApAdmin> {

    @PersistenceContext(unitName = "Proj5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ApAdminFacade() {
        super(ApAdmin.class);
    }

    /**
     * Searchs for a user by email.
     *
     * @param email
     * @return The user found.
     * @throws UserNotFoundException
     */
    public ApAdmin getApAdminByEmail(String email) throws UserNotFoundException {
        try {
            ApAdmin u = (ApAdmin) em.createNamedQuery("ApAdmin.findByEmail").setParameter("email", email).getSingleResult();
            return u;
        } catch (NoResultException ex) {
            Logger.getLogger(ApUserFacade.class.getName()).log(Level.SEVERE, "Erro na procura de utilizador por email.", ex);
            throw new UserNotFoundException();
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
    public ApAdmin validAuthenticationApadmin(String email, String password) throws InvalidAuthException, UserNotFoundException {
        ApAdmin apadmin = getApAdminByEmail(email);
        System.out.println("apadmin pass------------ " + apadmin.getEmail());
        System.out.println("apadmin pass------------ " + apadmin.getPassword());
        if (password.equals(apadmin.getPassword())) {
            System.out.println("tododoooooooooooooooooooooo");
            return apadmin;
        } else {
            Logger.getLogger(ApAdminFacade.class.getName()).log(Level.SEVERE, "Erro na autenticação de utilizador.");
            throw new InvalidAuthException("Password inválida.");
        }
    }

}
