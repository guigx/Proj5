/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.EJB;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Carlos
 */
@Named
@RequestScoped
public class SendEmail {

    private SimpleEmail email;

    public SendEmail() {
    }

    @PostConstruct
    private void Init() {
        email = new SimpleEmail();
        config();
    }

    public void config() {
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
//mail.setSmtpPort(587);
        email.setDebug(true);
        email.setAuthentication("acertarorumo@gmail.com", "managedbean");
        email.setSSLOnConnect(true);

    }

    public void sendEMail(String from, String subject, String msg, String to) {
        try {
            email.setFrom(from);
            email.setSubject(subject);
            email.setMsg(msg);
            email.addTo(to);
            email.setTLS(true);
            email.send();
            System.out.println("Mail sent!");
        } catch (EmailException e) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
}
