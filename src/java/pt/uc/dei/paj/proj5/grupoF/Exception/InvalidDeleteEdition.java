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
public class InvalidDeleteEdition extends Exception {

    public InvalidDeleteEdition() {
        super("Esta ediçao contem avaliações.");
    }

}
