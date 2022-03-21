package Controllers;

import Controllers.Interface.IAdminDashboardController;
import Models.IDbContext;
import Views.IView;

/**
 *
 * @author xorigin
 */
public class AdminDashboardController implements IAdminDashboardController{
    
    private final IView view;
    private final IDbContext dbContext;
    private final String ID;
    
    public AdminDashboardController(IView view, IDbContext dbContext, String loggedinID){
    
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
}
