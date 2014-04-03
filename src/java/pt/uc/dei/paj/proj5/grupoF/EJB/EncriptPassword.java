/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.uc.dei.paj.proj5.grupoF.EJB;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import pt.uc.dei.paj.proj5.grupoF.Entity.ApUser;


/**
 *
 * @author Grupo F
 */
@Stateless
public class EncriptPassword {

    /**
     * Creates a new instance of EncriptPassword
     */
    public EncriptPassword() {
    }
        public static String md5(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(pass.getBytes());
            BigInteger bigInt = new BigInteger(1,digest);
            return bigInt.toString(16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ApUser.class.getName()).log(Level.SEVERE, "Erro a encriptar password.", ex);
            return null;
        }
    }
}
