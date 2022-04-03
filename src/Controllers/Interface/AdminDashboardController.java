package Controllers.Interface;

import java.util.Map;

/**
 *
 * @author xorigin
 */
public interface AdminDashboardController extends AdministratorController{
    
    
    String getTotalCollectedMoney();
    
    Map<Enum, Object> getConsumptionStatOfRegion(String governmentCode);
    
}
