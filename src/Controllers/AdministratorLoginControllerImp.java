package Controllers;

import Controllers.Email.SendEmail;
import Models.AppDate.EmailConfigImp;
import Models.Enum.Column;
import java.util.Arrays;
import Controllers.Interface.AdministratorLoginController;
import Models.DbContext;
import Views.View;

/**
 *
 * @author xorigin
 */
public class AdministratorLoginControllerImp implements AdministratorLoginController {
    
    private final View view;
    private final DbContext dbContext;
    
    public AdministratorLoginControllerImp(View view, DbContext dbContext){
    
        this.view = view;
        this.dbContext = dbContext;
        
        this.startView();
    }
    
    @Override
    public final void startView(){
    
        this.view.setController(this);
        this.view.setVisible(true);
    }

    @Override
    public boolean isValidAccount(String ID, String password) {
        
        return this.dbContext.getAdministratorModel().isValidAccount(ID, password);
    }

    @Override
    public boolean isAdministratorExists(String ID) {
        
        return this.dbContext.getAdministratorModel().isAdministratorExists(ID);
    }

    @Override
    public void generateNewPassword(String ID) {
        
        String password = new AdministratorDataGenerator().generatePassword();
        
        this.dbContext.getAdministratorModel().update(Arrays.asList(Column.Password), Arrays.asList(password), ID);
        
        String email = (String) this.dbContext.getAdministratorModel().getInfo(Arrays.asList(Column.Email), ID).get(Column.Email);
        
        this.sendNewPasswordEmail(email, password);
    }

    @Override
    public String getRole(String ID) {
        
        return (String) this.dbContext.getAdministratorModel().getInfo(Arrays.asList(Column.Role), ID).get(Column.Role);
    }

    private void sendNewPasswordEmail(String email, String password){
    
        String messageSubject = "Password changed successfully";
        String messageText = "Your new Password is " + password + " .";
            
        SendEmail.setDefaultConfig(new EmailConfigImp()).send(email, messageSubject, messageText);
    }
    
}
