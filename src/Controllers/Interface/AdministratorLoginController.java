package Controllers.Interface;

/**
 *
 * @author xorigin
 */
public interface AdministratorLoginController extends Controller{
    
    boolean isValidAccount(String ID, String password);
    
    boolean isAdministratorExists(String ID);
    
    void generateNewPassword(String ID);
    
    String getRole(String ID);
    
}
