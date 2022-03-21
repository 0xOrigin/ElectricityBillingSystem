package Controllers;

import Controllers.Interface.IOperatorDashboardController;
import Models.IDbContext;
import Views.IView;

/**
 *
 * @author xorigin
 */
public class OperatorDashboardController implements IOperatorDashboardController{
    
    private final IView view;
    private final IDbContext dbContext;
    private final String ID;
    
    public OperatorDashboardController(IView view, IDbContext dbContext, String loggedinID){
    
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
