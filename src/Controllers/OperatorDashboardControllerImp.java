package Controllers;

import Controllers.Interface.OperatorDashboardController;
import Models.DbContext;
import Views.View;

/**
 *
 * @author xorigin
 */
public class OperatorDashboardControllerImp implements OperatorDashboardController{
    
    private final View view;
    private final DbContext dbContext;
    private final String ID;
    
    public OperatorDashboardControllerImp(View view, DbContext dbContext, String loggedinID){
    
        this.view = view;
        this.dbContext = dbContext;
        this.ID = loggedinID;
        
        this.startView();
    }
    
    @Override
    public final void startView(){
    
        this.view.setController(this);
        this.view.setVisible(true);
    }
    
    @Override
    public String getLoggedInID(){
    
        return this.ID;
    }
}
