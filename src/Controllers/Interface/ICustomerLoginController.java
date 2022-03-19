package Controllers.Interface;

/**
 *
 * @author xorigin
 */
public interface ICustomerLoginController extends IController {
    
    boolean isValidAccount(String meterCode, String password);
    
    boolean isMeterCodeExists(String meterCode);
    
    void generateNewPassword(String meterCode);
    
}
