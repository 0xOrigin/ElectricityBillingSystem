package Controllers;

import Controllers.Interface.AdministratorController;
import Controllers.Interface.Controller;
import Models.DbContext;
import Views.View;
import java.util.List;
import java.util.Map;
import Models.Enum.Role; 

/**
 *
 * @author xorigin
 */
public class AdministratorControllerImp implements AdministratorController{
    
    protected final View view;
    protected final DbContext dbContext;
    protected final String ID;
    
    public AdministratorControllerImp(View view, DbContext dbContext, String loggedinID){
    
        this.view = view;
        this.dbContext = dbContext;
        this.ID = loggedinID;
        
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
    
    // my code goes here 
    
    private boolean areThereUnPaidBills(String meterCode){
        
        return (dbContext.getBillModel().getNumOfUnpaidBills(meterCode) > 0) ; 
    }
    
    @Override
    public boolean deleteCustomer(String meterCode){
        
        if(!areThereUnPaidBills(meterCode)){
            dbContext.getCustomerModel().delete(meterCode);
            return true ; 
        }
        else
            return false ; 
    }
    
    private int getAdministratorsCount(){
        
        return (dbContext.getAdministratorModel().getNumOfRegisteredInRole(Role.Admin) + 
                dbContext.getAdministratorModel().getNumOfRegisteredInRole(Role.Operator)) ; 
    }
    
    @Override
    public boolean deleteAdmin(String ID){
      
       if(getAdministratorsCount() == 1){
           return false ;
       }
       
       else{
           dbContext.getAdministratorModel().delete(ID);
           return true ;
       }
        
    }
    
    
}
