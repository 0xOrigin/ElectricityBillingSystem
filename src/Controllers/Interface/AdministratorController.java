package Controllers.Interface;

import java.util.List;
import java.util.Map;

/**
 *
 * @author xorigin
 */
public interface AdministratorController extends Controller{
    
    String getLoggedInID();
    
    String getTargetID();
    
    void registerInView(Controller controller);
    
    List<Map<Enum, Object>> getAllBillsOfRegion(String governmentCode);
      
    String getRole(String ID) ;
    
    boolean deleteAdmin(String ID); 
    
    boolean deleteOperator(String ID); 
    
    boolean deleteAdministrator(String ID , String role); 
    
    boolean isValidAddress(String address);
    
    boolean isValidEmail(String email);
    
    boolean isValidPhoneNumber(String phoneNumber);
    
    public void updateAdmin(List<Enum> fields,List<Object> values,String ID);
    
    public Map<Enum, Object> getInfo(List<Enum> fields, String ID);
    
    public int getNumOfRegisteredInRole(String role);
    
}
