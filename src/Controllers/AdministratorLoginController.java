package Controllers;

import Controllers.Email.SendEmail;
import Controllers.Interface.IAdministratorLoginController;
import Models.AppDate.EmailConfig;
import Models.Enum.Column;
import Models.IDbContext;
import Views.IView;
import java.util.Arrays;

/**
 *
 * @author xorigin
 */
public class AdministratorLoginController implements IAdministratorLoginController {
    
    private final IView view;
    private final IDbContext dbContext;
    
    public AdministratorLoginController(IView view, IDbContext dbContext){
    
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
        
        String password = new Generator().generateAdministratorPassword();
        
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
            
        new SendEmail("Electricity Company", new EmailConfig()).send(email, messageSubject, messageText);
    }
    
}
