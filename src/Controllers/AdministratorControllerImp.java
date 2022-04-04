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
    
    // my code goes here 
    
    @Override
    public String getTargetID(){
    
        return this.targetID;
    }
    
    
    private int getAdministratorsCount(){
        
        return dbContext.getAdministratorModel().getNumOfRegisteredInRole(Role.Admin) ; 
    }
    
    @Override
    public String deleteAdmin(String ID){
        
       String message = "" ; 
       if(getAdministratorsCount() == 1)
           message = "Can't Preform The Deletion, Only One Admin Exists" ; 
       
       else{
       
           dbContext.getAdministratorModel().delete(ID);
           message = "Administrator Has Deleted Successfully" ; 
       }
       return message ; 
    }
    
   
}
