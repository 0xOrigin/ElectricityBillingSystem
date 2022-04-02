package Controllers.Interface;

/**
 *
 * @author xorigin
 */
public interface CustomerLoginController extends Controller {
    
    boolean isValidAccount(String meterCode, String password);
    
    boolean isMeterCodeExists(String meterCode);
    
    void generateNewPassword(String meterCode);
    
}
