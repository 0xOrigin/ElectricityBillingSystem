package Models.Interface;

import java.sql.SQLException;

/**
 *
 * @author xorigin
 */
public interface ICustomerLogin extends IModel {
    
    boolean isValidAccount(String meterCode, String password);
    
    boolean isMeterCodeExists(String meterCode);
    
    void updateCustomerPassword(String password, String meterCode);
    
    String getEmail(String meterCode);
}
