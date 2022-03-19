package Models.Interface;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author xorigin
 */
public interface IAdminDashboard extends IModel {
    
    Map<Enum, Object> getConsumptionStatforRegion(String governmentCode) throws SQLException;
    
    List<Map<Enum, Object>> getAllBillsOfRegion(String governmentCode) throws SQLException;
    
    String getTotalCollected() throws SQLException;
    
    void addCustomer(
            
            String name, String nationalID, String address, String email, String governmentCode, 
            String phoneNumber, String gender, String dateOfBirth, String typeOfUse, String meterCode, 
            String password, String activation, String dateOfContract, String propertyOwnershipContract 
    );
    
    void updateCustomer(List<Enum> fields, List<Object> values, String meterCode);
    
    void deleteCustomer(String meterCode);
    
    public void addAdministrator(
            
            String name, String nationalID, String address, String email, String phoneNumber, String gender,
            String dateOfBirth, String ID, String password, String role, String dateOfContract 
    );
    
    void updateAdministrator(List<Enum> fields, List<Object> values, String ID);
    
    void deleteAdministrator(String ID);
    
    boolean isCustomerNationalIdExists(String nationalId) throws SQLException;
    
    boolean isAdministratorNationalIdExists(String nationalId) throws SQLException;
    
    boolean isAdministratorExists(String ID) throws SQLException;
    
    boolean[] isMeterCodeExists_Active(String meterCode) throws SQLException;
    
    int getNumOfSpecificRole(Enum role) throws SQLException;
    
    int getNumOfUnpaidBills(String meterCode) throws SQLException;
    
}
