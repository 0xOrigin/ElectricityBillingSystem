package Models.Interface;

/**
 *
 * @author xorigin
 */
public interface IAdministratorLogin extends IModel {
    
    boolean isValidAccount(String ID, String password);
    
    boolean isAdministratorExists(String ID);
    
    String getRole(String ID);
    
    void updateAdministratorPassword(String password, String ID);
    
    String getEmail(String ID);
    
}
