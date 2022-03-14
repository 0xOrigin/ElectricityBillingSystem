package Controllers.Email;

import Models.Interface.IEmail;
import java.io.UnsupportedEncodingException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author xorigin
 */
class EmailMessage {
    
    private final EmailConfiguration emailConfiguration;
    private final String personalName;
    
    EmailMessage(String personalName, IEmail emailConfig) {
        
        this.personalName = personalName;
        this.emailConfiguration = new EmailConfiguration(emailConfig);
    }
    
    Message generateMessage(String recepientEmail, String messageSubject, String messageText) {
    
        Message message = new MimeMessage(this.emailConfiguration.getSession());
        
        try {
            
            message.setFrom(new InternetAddress(this.emailConfiguration.getSenderEmail(), this.personalName));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepientEmail));
            message.setSubject(messageSubject);
            message.setText(messageText); 
            
        } catch (MessagingException | UnsupportedEncodingException exception) {
            System.out.println(exception);
        }
    
        return message;
    }
    
}
