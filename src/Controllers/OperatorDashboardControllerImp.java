package Controllers;

import Controllers.Interface.OperatorDashboardController;
import Models.DbContext;
import Views.View;

/**
 *
 * @author xorigin
 */
public class OperatorDashboardControllerImp extends AdministratorControllerImp implements OperatorDashboardController{
    
    
    public OperatorDashboardControllerImp(View view, DbContext dbContext, String loggedinID){
    
        super(view, dbContext, loggedinID);
        
        this.registerInView(this);
    }
    
    
}
