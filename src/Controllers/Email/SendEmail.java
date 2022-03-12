package Controllers.Email;

import javax.mail.MessagingException;
import javax.mail.Transport;

/**
 *
 * @author xorigin
 */
public class SendEmail {
    
    private final EmailMessage emailMessage;
    
    public SendEmail(String personalName, String senderEmail, String senderPassword, String host, String port){
        
        this.emailMessage = new EmailMessage(personalName, senderEmail, senderPassword, host, port);
    }
    
    public void send(String recepientEmail, String messageSubject, String messageText){
    
        try {

            Transport.send(this.emailMessage.generateMessage(recepientEmail, messageSubject, messageText));

        } catch (MessagingException exception) {
            System.out.println(exception);
        }
    
    }
    
}