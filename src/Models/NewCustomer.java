package Models;

import Models.Database.Customer;
import Models.Database.ORM.SQLiteAdapter;
import Models.Enum.Table;
import Models.Interface.INewCustomer;
import java.sql.SQLException;

/**
 *
 * @author xorigin
 */
public class NewCustomer implements INewCustomer {
    
    private final Customer customerTable;
    
    public NewCustomer(){
    
        this.customerTable = new Customer(new SQLiteAdapter(Table.Customer));
    }
    
    @Override
    public void insert(
            
            String name, String nationalID, String address, String email, String governmentCode, 
            String phoneNumber, String gender, String dateOfBirth, String typeOfUse, String meterCode, 
            String password, String activation, String dateOfContract, String propertyOwnershipContract 
    ){
    
        this.customerTable.insert(name, nationalID, address, email, governmentCode, phoneNumber, gender, dateOfBirth, typeOfUse, meterCode, password, activation, dateOfContract, propertyOwnershipContract);
    }
    
    @Override
    public boolean isNationalIdExists(String nationalId) throws SQLException{
    
        return this.customerTable.isNationalIdExists(nationalId);
    }

    @Override
    public int getNumOfCustomers() throws SQLException{
        
        return this.customerTable.getNumOfCustomers();
    }
    
}
