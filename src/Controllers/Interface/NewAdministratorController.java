package Controllers.Interface;

/**
 *
 * @author xorigin
 */
public interface NewAdministratorController extends Controller {
    
    void registerAdministrator(String name, String nationalID, String address, String email, String Role,
                                 String phoneNumber);
    
    boolean isValidName(String name);
    
    boolean isValidNationalID(String nationalID);
    
    boolean isValidAddress(String address);
    
    boolean isValidEmail(String email);
    
    boolean isValidPhoneNumber(String phoneNumber);
    
}
