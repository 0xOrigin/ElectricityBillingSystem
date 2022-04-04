package Controllers.Interface;

import java.util.List;
import java.util.Map;

/**
 *
 * @author xorigin
 */
public interface CustomerDashboardController extends Controller {
    
    String getActivationState();
    
    String getLastReading();
    
    boolean isValidReading(int currentReading);
    
    String[] getLastReleaseDate();
    
    void releaseNewBill(int currentReading, String releaseDate);
        
    List<Map<Enum, Object>> getAllBillsOfMeterCode();
    
    void complainAboutBill(String complain, int billNumber);
    
    boolean isValidComplaint(String complaint);
    
    String getLoggedinMeterCode();
    
    String getTargetMeterCode();
    
}
