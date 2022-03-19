package Controllers.Email;

import Models.AppDate.IEmail;
import javax.mail.MessagingException;
import javax.mail.Transport;

/**
 *
 * @author xorigin
 */
public class SendEmail {
    
    private final EmailMessage emailMessage;
    
    public SendEmail(String personalName, IEmail emailConfig){
        
        this.emailMessage = new EmailMessage(personalName, emailConfig);
    }
    
    public void send(String recepientEmail, String messageSubject, String messageText){
    
        try {

            Transport.send(this.emailMessage.generateMessage(recepientEmail, messageSubject, messageText));

        } catch (MessagingException exception) {
            
            System.out.println("-----------------------------------");
            System.out.println("Mail subject : " + messageSubject);
            System.out.println("Mail text : \n" + messageText);
            System.out.println("----------------------------------------------------------");
//            System.out.println(exception);
        }
    
    }
    
}