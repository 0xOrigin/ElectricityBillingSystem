package Controllers;

import Controllers.Interface.AdministratorController;
import Controllers.Interface.Controller;
import Models.DbContext;
import Views.View;
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
    public String getTargetID(){
    
        return this.targetID;
    }
    
}
