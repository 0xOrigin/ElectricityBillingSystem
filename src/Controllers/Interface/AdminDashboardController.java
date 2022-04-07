package Controllers.Interface;

import java.util.Map;

/**
 *
 * @author xorigin
 */
public interface AdminDashboardController extends AdministratorController{
    
    
    String getTotalCollectedMoney();
    boolean isValidName(String name);
    
    boolean isValidNationalID(String nationalID);
    
    boolean isValidAddress(String address);
    
    boolean isValidEmail(String email);
    
    boolean isValidPhoneNumber(String phoneNumber);
    Map<Enum, Object> getConsumptionStatOfRegion(String governmentCode);

    public void registerAdministrator(String name, String nationalID, String address,
            String email, String Role,String phoneNumber);

    
    
}
