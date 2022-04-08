package Controllers;

import Controllers.Email.SendEmail;
import Models.AppData.EmailConfigImp;
import Models.Enum.Column;
import java.util.Arrays;
import Controllers.Interface.CustomerLoginController;
import Models.DbContext;
import Views.View;

/**
 *
 * @author xorigin
 */
public class CustomerLoginControllerImp implements CustomerLoginController{
    
    private final View view;
    private final DbContext dbContext;
    
    public CustomerLoginControllerImp(View view, DbContext dbContext){
    
        this.view = view;
        this.dbContext = dbContext;
        
        this.registerInView();
    }
    
    @Override
    public final void registerInView(){
    
        if(this.view != null)
            this.view.setController(this);
    }
    
    @Override
    public boolean isValidAccount(String meterCode, String password){
    
        return this.dbContext.getCustomerModel().isValidAccount(meterCode, password);
    }
    
    @Override
    public boolean isMeterCodeExists(String meterCode){
    
        return this.dbContext.getCustomerModel().isMeterCodeExists(meterCode);
    }
    
    @Override
    public void generateNewPassword(String meterCode){
    
        String password = new CustomerDataGenerator().generatePassword();
        
        this.dbContext.getCustomerModel().update(Arrays.asList(Column.Password), Arrays.asList(password), meterCode);
        
        String email = (String) this.dbContext.getCustomerModel().getInfo(Arrays.asList(Column.Email), meterCode).get(Column.Email);
        
        this.sendNewPasswordEmail(email, password);
    }
    
    private void sendNewPasswordEmail(String email, String password){
    
        String messageSubject = "Password changed successfully";
        String messageText = "Your new Password is " + password + " .";
            
        SendEmail.setDefaultConfig(new EmailConfigImp()).send(email, messageSubject, messageText);
    }
}
