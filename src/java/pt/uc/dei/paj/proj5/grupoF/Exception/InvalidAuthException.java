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
public class InvalidAuthException extends Exception {

    public InvalidAuthException() {
        super("Utilizador não registado.");
    }

    public InvalidAuthException(String what_caused) {
        super(what_caused);
    }

}
