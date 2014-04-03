/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.Exception;

/**
 *
 * @author Mar
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("Utilizador não encontrado.");
    }

    public UserNotFoundException(String what_caused) {
        super(what_caused);
    }

}
