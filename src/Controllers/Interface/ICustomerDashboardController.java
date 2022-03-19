package Controllers.Interface;

/**
 *
 * @author xorigin
 */
public interface ICustomerDashboardController extends IController {
    
    String getActivationState();
    
    String getLastReading();
    
    boolean isValidReading(int currentReading);
    
    String[] getLastReleaseDate();
    
    void releaseNewBill(int currentReading, String releaseDate);
}
