package Controllers.Interface;

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
    
    String getLoggedinMeterCode();
}
