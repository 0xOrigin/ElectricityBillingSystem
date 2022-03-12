package Controllers.Email;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 *
 * @author xorigin
 */
class EmailConfiguration {
    
    private final String senderMail;
    private final String senderPassword;
    private final String host;
    private final String port;
    private Session session;

    EmailConfiguration(String senderEmail, String senderPassword, String host, String port) {
        
        this.senderMail = senderEmail;
        this.senderPassword = senderPassword;
        this.host = host;
        this.port = port;
        
        createSession();
    }
    
    private void createSession(){
       
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        
        properties.put("mail.smtp.host", this.host);
        properties.put("mail.smtp.port", this.port);

        this.session = Session.getInstance(properties, new Authenticator(){

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderMail, senderPassword);
            }
            
        }); 
    
    }
    
    Session getSession(){
    
        return this.session;
    }
    
    String getSenderEmail(){
    
        return this.senderMail;
    }
     
}