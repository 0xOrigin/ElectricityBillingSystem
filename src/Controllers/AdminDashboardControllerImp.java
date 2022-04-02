package Controllers;

import Controllers.Interface.AdminDashboardController;
import Models.DbContext;
import Views.View;

/**
 *
 * @author xorigin
 */
public class AdminDashboardControllerImp implements AdminDashboardController{
    
    private final View view;
    private final DbContext dbContext;
    private final String ID;
    
    public AdminDashboardControllerImp(View view, DbContext dbContext, String loggedinID){
    
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
    
    public String getLoggedInID(){
    
        return this.ID;
    }
}
