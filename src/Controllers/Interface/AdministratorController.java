package Controllers.Interface;

import java.util.List;
import java.util.Map;

/**
 *
 * @author xorigin
 */
public interface AdministratorController extends Controller{
    
    String getLoggedInID();
    
    void registerInView(Controller controller);
    
    List<Map<Enum, Object>> getAllBillsOfRegion(String governmentCode);
    
    boolean isValidAddress(String address);
    
    boolean isValidEmail(String email);
    
    boolean isValidPhoneNumber(String phoneNumber);
    
    public void updateAdmin(List<Enum> fields,List<Object> values,String ID);
    
    public Map<Enum, Object> getInfo(List<Enum> fields, String ID);
    
    public int getNumOfRegisteredInRole(String role);
    
    
}
