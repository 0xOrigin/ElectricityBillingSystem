package Controllers;

import Controllers.Email.SendEmail;
import Controllers.Interface.AdministratorController;
import Controllers.Interface.Controller;
import Models.DbContext;
import Models.Enum.ActivationState;
import Models.Enum.Role;
import Views.View;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author xorigin
 */
public class AdministratorControllerImp implements AdministratorController{
    
    protected final View view;
    protected final DbContext dbContext;
    protected final String ID;
    protected final DataValidator validator;
    
    public AdministratorControllerImp(View view, DbContext dbContext, String loggedinID){
    
        this.view = view;
        this.dbContext = dbContext;
        this.ID = loggedinID;
        this.validator = new DataValidator();
        
        this.registerInView();
    }
    
    @Override
    public final void registerInView(){
    
        if(this.view != null)
            this.view.setController(this);
    }
    
    @Override
    public final void registerInView(Controller controller){
    
        this.view.setController(controller);
    }
    
    @Override
    public List<Map<Enum, Object>> getAllBillsOfRegion(String governmentCode){
    
        return this.dbContext.getBillModel().getAllBillsOfRegion(governmentCode);
    }
    
    @Override
    public String getLoggedInID(){
    
        return this.ID;
    }
    
    @Override
    public boolean isValidAddress(String address) {
        
        return this.validator.isValidAddress(address);
    };
    
    @Override 
    public boolean isValidEmail(String email) {
        
        return this.validator.isValidEmail(email);
    }

    @Override
    public boolean isValidPhoneNumber(String phoneNumber) {
        
        return this.validator.isValidPhoneNumber(phoneNumber);
    }
    
    
    public void updateAdmin(List<Enum> fields,List<Object> values,String ID){
    

        this.dbContext.getAdministratorModel().update(fields,values, ID);
        
    }
    @Override
    public Map<Enum, Object> getInfo(List<Enum> fields, String identifier) {
        
        return this.dbContext.getAdministratorModel().getInfo(fields, identifier);
       
    }
    
    @Override 
    public int getNumOfRegisteredInRole(String role) {
        return this.dbContext.getAdministratorModel().getNumOfRegisteredInRole(Role.valueOf(role));
    }
    
       
}
