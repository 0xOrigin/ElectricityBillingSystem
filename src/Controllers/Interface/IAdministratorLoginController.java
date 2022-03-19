package Controllers.Interface;

/**
 *
 * @author xorigin
 */
public interface IAdministratorLoginController extends IController{
    
    boolean isValidAccount(String ID, String password);
    
    boolean isAdministratorExists(String ID);
    
    void generateNewPassword(String ID);
    
    String getRole(String ID);
    
}
