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
    
    // my code goes here     
    
    String getRole(String ID) ;
    boolean deleteAdmin(String ID); 
    boolean deleteOperator(String ID); 
    boolean deleteAdministrator(String ID , String role); 
     
}
