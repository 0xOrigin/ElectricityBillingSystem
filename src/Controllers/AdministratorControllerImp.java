package Controllers;

import Controllers.Interface.AdministratorController;
import Controllers.Interface.Controller;
import Models.DbContext;
import Models.Enum.Column;
import Views.View;
import java.util.List;
import java.util.Map;
import Models.Enum.Role; 
import java.util.Arrays;

/**
 *
 * @author xorigin
 */
public class AdministratorControllerImp implements AdministratorController{
    
    protected final View view;
    protected final DbContext dbContext;
    protected final String ID;
    protected String targetID = "";
    
    public AdministratorControllerImp(View view, DbContext dbContext, String loggedinID){
    
        this.view = view;
        this.dbContext = dbContext;
        this.ID = loggedinID;
        
        this.registerInView();
    }
    
    public AdministratorControllerImp(View view, DbContext dbContext, String loggedinID, String targetID){
    
        this(view, dbContext, loggedinID);
        this.targetID = targetID;
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
    public String getRole(String ID) {
        
        return (String) this.dbContext.getAdministratorModel().getInfo(Arrays.asList(Column.Role), ID).get(Column.Role);
    }
    
    @Override
    public String getTargetID(){
    
        return this.targetID;
    }
    
    
    private int getAdminsCount(){
        
        return this.dbContext.getAdministratorModel().getNumOfRegisteredInRole(Role.Admin) ; 
    }
    
    private int getOperatorsCount(){
        
        return this.dbContext.getAdministratorModel().getNumOfRegisteredInRole(Role.Operator) ; 
    }
    
    @Override
    public boolean deleteAdmin(String ID){
        
       if(getAdminsCount() == 1)
           return false ;
       
       else{
      
           this.dbContext.getAdministratorModel().delete(ID);
           return true ; 
       }
    }
    
    @Override
    public boolean deleteOperator(String ID){
        
       if(getOperatorsCount() == 1)
           return false ; 
       
       else{
       
           this.dbContext.getAdministratorModel().delete(ID);
           return true ;  
       }
    }
    
    @Override
    public boolean deleteAdministrator(String ID , String role){
        
        if(role.equals(Role.Admin.name()))
            return this.deleteAdmin(ID ); 
        else
            return this.deleteOperator(ID); 
    }
   
}
