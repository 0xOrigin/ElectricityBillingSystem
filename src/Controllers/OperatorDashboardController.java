package Controllers;

import Controllers.Interface.IOperatorDashboardController;
import Models.Interface.IModel;
import Models.Interface.IOperatorDashboard;
import Views.Interface.IOperatorDashboardView;
import Views.Interface.IView;

/**
 *
 * @author xorigin
 */
public class OperatorDashboardController implements IOperatorDashboardController{
    
    private final IOperatorDashboardView view;
    private final IOperatorDashboard model;
    private final String ID;
    
    public OperatorDashboardController(IView view, IModel model, String loggedinID){
    
        this.view = (IOperatorDashboardView) view;
        this.model = (IOperatorDashboard) model;
        this.ID = loggedinID;
        
        this.start();
    }
    
    @Override
    public final void start(){
    
        this.view.setController(this);
        this.view.setVisible(true);
    }
    
}
