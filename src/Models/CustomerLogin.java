package Models;

import Models.Database.Customer;
import Models.Database.ORM.SQLiteAdapter;
import Models.Enum.Column;
import Models.Enum.Table;
import Models.Interface.ICustomerLogin;
import java.sql.SQLException;
import java.util.Arrays;

/**
 *
 * @author xorigin
 */
public class CustomerLogin implements ICustomerLogin {
    
    private final Customer customerTable;
    
    public CustomerLogin(){
    
        this.customerTable = new Customer(new SQLiteAdapter(Table.Customer));
    }
    
    @Override
    public boolean isValidAccount(String meterCode, String password){
    
        try {
            
            return this.customerTable.isValidAccount(meterCode, password);
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return false;
    }
    
    @Override
    public boolean isMeterCodeExists(String meterCode){
    
        try {
            
            return this.customerTable.isMeterCodeExists_Active(meterCode)[0];
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return false;
    }
    
    
    @Override
    public void updateCustomerPassword(String password, String meterCode){
    
        this.customerTable.update(Arrays.asList(Column.Password), Arrays.asList(password), meterCode);
    }
    
    @Override
    public String getEmail(String meterCode){
    
        try {
            
            return (String) this.customerTable.getInfo(Arrays.asList(Column.Email), meterCode).get(Column.Email);
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return "";
    }
}
