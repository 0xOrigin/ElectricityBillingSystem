package Controllers.Email;

import Models.Enum.AppData;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import Models.AppDate.EmailConfig;

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

    private EmailConfiguration(EmailConfig emailConfig) {
        
        this.senderMail = emailConfig.get(AppData.SenderEmail);
        this.senderPassword = emailConfig.get(AppData.SenderPassword);
        this.host = emailConfig.get(AppData.Host);
        this.port = emailConfig.get(AppData.Port);
        
        createSession();
    }
    
    
    private EmailConfiguration(String senderMail, String senderPassword, String host, String port) {
        
        this.senderMail = senderMail;
        this.senderPassword = senderPassword;
        this.host = host;
        this.port = port;
        
        createSession();
    }
    
    static EmailConfiguration setDefaultConfig(EmailConfig emailConfig){
    
        return new EmailConfiguration(emailConfig);
    }
    
    static EmailConfiguration setFullConfig(String senderMail, String senderPassword, String host, String port){
    
        return new EmailConfiguration(senderMail, senderPassword, host, port);
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