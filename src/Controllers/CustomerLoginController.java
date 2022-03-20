package Controllers;

import Controllers.Email.SendEmail;
import Controllers.Interface.ICustomerLoginController;
import Models.AppDate.EmailConfig;
import Models.Interface.ICustomerLogin;
import Models.Interface.IModel;
import Views.IView;
import java.io.IOException;
import org.json.simple.parser.ParseException;

/**
 *
 * @author xorigin
 */
public class CustomerLoginController implements ICustomerLoginController{
    
    private final IView view;
    private final ICustomerLogin model;
    
    public CustomerLoginController(IView view, IModel model){
    
        this.view = view;
        this.model = (ICustomerLogin) model;
        
        this.start();
    }
    
    @Override
    public final void start(){
    
        this.view.setController(this);
        this.view.setVisible(true);
    }
    
    @Override
    public boolean isValidAccount(String meterCode, String password){
    
        return this.model.isValidAccount(meterCode, password);
    }
    
    @Override
    public boolean isMeterCodeExists(String meterCode){
    
        return this.model.isMeterCodeExists(meterCode);
    }
    
    @Override
    public void generateNewPassword(String meterCode){
    
        String password = new Generator().generateCustomerPassword();
        
        this.model.updateCustomerPassword(password, meterCode);
        
        String email = this.model.getEmail(meterCode);
        
        this.sendNewPasswordEmail(email, password);
    }
    
    private void sendNewPasswordEmail(String email, String password){
    
        String messageSubject = "Password changed successfully";
        String messageText = "Your new Password is " + password + " .";
        
        try {
            
            new SendEmail("Electricity Company", new EmailConfig()).send(email, messageSubject, messageText);

        } catch (ParseException | IOException ex) {
            System.out.println(ex);
        } 
    }
}
