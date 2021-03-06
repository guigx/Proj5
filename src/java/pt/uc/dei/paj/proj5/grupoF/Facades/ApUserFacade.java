/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.Facades;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pt.uc.dei.paj.proj5.grupoF.Entity.ApUser;
import pt.uc.dei.paj.proj5.grupoF.Entity.Edition;
import pt.uc.dei.paj.proj5.grupoF.Entity.Project;
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

    public Edition getApUserByEdition(String name) throws UserNotFoundException {
        try {
            Edition u = (Edition) em.createNamedQuery("ApUser.findByEdition").setParameter("edition", name).getSingleResult();

            return u;
        } catch (NoResultException ex) {
            Logger.getLogger(ApUserFacade.class.getName()).log(Level.SEVERE, "Erro na procura de utilizador por email.", ex);
            throw new UserNotFoundException();
        }
    }

    public void createApUser(ApUser apuser, String password, Edition edition) {
        apuser.setRegisterDate(new Date());
        create(apuser);
        apuser.setEdition(edition);
        edition.getUserList().add(apuser);
        edit(apuser);
        em.merge(edition);
    }

    public void updateApUser(ApUser user) {
        this.edit(user);
    }

    public List<ApUser> listStudentsProject(Edition edition, Project project) {

        Query q = em.createNamedQuery("ApUser.findByEditionNoProject");

        q.setParameter("edition", edition);
        q.setParameter("project", project);
        return (List<ApUser>) q.getResultList();
    }

    public List<ApUser> apuserByEdition(Edition edition) {
        Query q = em.createNamedQuery("ApUser.findByEdition");
        q.setParameter("edition", edition);
        return (List<ApUser>) q.getResultList();
    }
}
