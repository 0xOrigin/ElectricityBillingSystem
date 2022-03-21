package Controllers;

import Controllers.Email.SendEmail;
import Controllers.Interface.ICustomerLoginController;
import Models.AppDate.EmailConfig;
import Models.Enum.Column;
import Models.IDbContext;
import Views.IView;
import java.util.Arrays;

/**
 *
 * @author xorigin
 */
public class CustomerLoginController implements ICustomerLoginController{
    
    private final IView view;
    private final IDbContext dbContext;
    
    public CustomerLoginController(IView view, IDbContext dbContext){
    
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
    public boolean isValidAccount(String meterCode, String password){
    
        return this.dbContext.getCustomerModel().isValidAccount(meterCode, password);
    }
    
    @Override
    public boolean isMeterCodeExists(String meterCode){
    
        return this.dbContext.getCustomerModel().isMeterCodeExists_Active(meterCode)[0];
    }
    
    @Override
    public void generateNewPassword(String meterCode){
    
        String password = new Generator().generateCustomerPassword();
        
        this.dbContext.getCustomerModel().update(Arrays.asList(Column.Password), Arrays.asList(password), meterCode);
        
        String email = (String) this.dbContext.getCustomerModel().getInfo(Arrays.asList(Column.Email), meterCode).get(Column.Email);
        
        this.sendNewPasswordEmail(email, password);
    }
    
    private void sendNewPasswordEmail(String email, String password){
    
        String messageSubject = "Password changed successfully";
        String messageText = "Your new Password is " + password + " .";
            
        new SendEmail("Electricity Company", new EmailConfig()).send(email, messageSubject, messageText);
    }
}
