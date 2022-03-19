package Models;

import Models.Database.Administrator;
import Models.Database.ORM.SQLiteAdapter;
import Models.Enum.Column;
import Models.Enum.Table;
import Models.Interface.IAdministratorLogin;
import java.sql.SQLException;
import java.util.Arrays;

/**
 *
 * @author xorigin
 */
public class AdministratorLogin implements IAdministratorLogin {
    
    private final Administrator administratorTable;
    
    public AdministratorLogin(){
    
        this.administratorTable = new Administrator(new SQLiteAdapter(Table.Administrator));
    }
    
    @Override
    public boolean isValidAccount(String ID, String password){
    
        try {
            
            return this.administratorTable.isValidAccount(ID, password);
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return false;
    }
    
    @Override
    public boolean isAdministratorExists(String ID){
    
        try {
            
            return this.administratorTable.isAdministratorExists(ID);
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return false;
    }
    
    @Override
    public String getRole(String ID){
    
        try {
            
            return (String) this.administratorTable.getInfo(Arrays.asList(Column.Role), ID).get(Column.Role);
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return "";
    }
    
    @Override
    public void updateAdministratorPassword(String password, String ID){
    
        this.administratorTable.update(Arrays.asList(Column.Password), Arrays.asList(password), ID);
    }
    
    @Override
    public String getEmail(String ID){
    
        try {
            
            return (String) this.administratorTable.getInfo(Arrays.asList(Column.Email), ID).get(Column.Email);
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return "";
    }
    
}
