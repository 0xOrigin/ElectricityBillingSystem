package Controllers;

import Controllers.Interface.IOperatorDashboardController;
import Models.Interface.IModel;
import Models.Interface.IOperatorDashboard;
import Views.IView;

/**
 *
 * @author xorigin
 */
public class OperatorDashboardController implements IOperatorDashboardController{
    
    private final IView view;
    private final IOperatorDashboard model;
    private final String ID;
    
    public OperatorDashboardController(IView view, IModel model, String loggedinID){
    
        this.view = view;
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
