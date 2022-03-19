package Controllers;

import Controllers.Email.SendEmail;
import Controllers.Interface.IAdministratorLoginController;
import Models.AppDate.EmailConfig;
import Models.Interface.IAdministratorLogin;
import Models.Interface.IModel;
import Views.Interface.IAdministratorLoginView;
import Views.Interface.IView;
import java.io.IOException;
import org.json.simple.parser.ParseException;

/**
 *
 * @author xorigin
 */
public class AdministratorLoginController implements IAdministratorLoginController {
    
    private final IAdministratorLoginView view;
    private final IAdministratorLogin model;
    
    public AdministratorLoginController(IView view, IModel model){
    
        this.view = (IAdministratorLoginView) view;
        this.model = (IAdministratorLogin) model;
        
        this.start();
    }
    
    @Override
    public final void start(){
    
        this.view.setController(this);
        this.view.setVisible(true);
    }

    @Override
    public boolean isValidAccount(String ID, String password) {
        
        return this.model.isValidAccount(ID, password);
    }

    @Override
    public boolean isAdministratorExists(String ID) {
        
        return this.model.isAdministratorExists(ID);
    }

    @Override
    public void generateNewPassword(String ID) {
        
        String password = new Generator().generateAdministratorPassword();
        
        this.model.updateAdministratorPassword(password, ID);
        
        String email = this.model.getEmail(ID);
        
        this.sendNewPasswordEmail(email, password);
    }

    @Override
    public String getRole(String ID) {
        
        return this.model.getRole(ID);
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
