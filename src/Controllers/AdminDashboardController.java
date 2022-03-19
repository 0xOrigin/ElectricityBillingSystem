package Controllers;

import Controllers.Interface.IAdminDashboardController;
import Models.Interface.IAdminDashboard;
import Models.Interface.IModel;
import Views.Interface.IAdminDashboardView;
import Views.Interface.IView;

/**
 *
 * @author xorigin
 */
public class AdminDashboardController implements IAdminDashboardController{
    
    private final IAdminDashboardView view;
    private final IAdminDashboard model;
    private final String ID;
    
    public AdminDashboardController(IView view, IModel model, String loggedinID){
    
        this.view = (IAdminDashboardView) view;
        this.model = (IAdminDashboard) model;
        this.ID = loggedinID;
        
        this.start();
    }
    
    @Override
    public final void start(){
    
        this.view.setController(this);
        this.view.setVisible(true);
    }
}
