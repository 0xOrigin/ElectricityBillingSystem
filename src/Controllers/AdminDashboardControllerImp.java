package Controllers;

import Controllers.Bill.MakeConsumptionStats;
import Controllers.Interface.AdminDashboardController;
import Models.DbContext;
import Views.View;
import java.util.Map;

/**
 *
 * @author xorigin
 */
public class AdminDashboardControllerImp extends AdministratorControllerImp implements AdminDashboardController{
    
    
    public AdminDashboardControllerImp(View view, DbContext dbContext, String loggedinID){
    
        super(view, dbContext, loggedinID);
        
        this.registerInView(this);
    }
    
    public AdminDashboardControllerImp(View view, DbContext dbContext){
    
        super(view, dbContext, "");
        
        this.registerInView(this);
    }
    
    @Override
    public String getTotalCollectedMoney(){
    
        return String.format("%.2f", this.dbContext.getCollectedMoneyModel().getTotalCollected());
    }
    
    @Override
    public Map<Enum, Object> getConsumptionStatOfRegion(String governmentCode){
    
        return new MakeConsumptionStats(this.dbContext, governmentCode).make();
    }
    
}
