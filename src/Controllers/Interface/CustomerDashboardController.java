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
    
    String getMeterCode();
    
    void toggleActivation(String meterCode);
    
    boolean deleteCustomer(String meterCode);
    
    boolean isValidAddress(String address);
    
    boolean isValidEmail(String email);
    
    boolean isValidPhoneNumber(String phoneNumber);
    
    public void updateCustomer(List<Enum> fields,List<Object> values,String meterCode);
    
    public Map<Enum, Object> getInfo(List<Enum> fields, String MeterCode);
    
}
