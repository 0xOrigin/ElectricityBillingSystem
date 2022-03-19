package Models.Interface;

import java.sql.SQLException;

/**
 *
 * @author xorigin
 */
public interface INewCustomer extends IModel {
    
    void insert(
            
            String name, String nationalID, String address, String email, String governmentCode, 
            String phoneNumber, String gender, String dateOfBirth, String typeOfUse, String meterCode, 
            String password, String activation, String dateOfContract, String propertyOwnershipContract 
    );
    
    boolean isNationalIdExists(String nationalId) throws SQLException;
    
    int getNumOfCustomers() throws SQLException;
}
