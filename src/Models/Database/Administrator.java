package Models.Database;

import Models.Database.ORM.IAdapter;
import Models.Enum.Column;
import Models.Enum.Table;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import Models.Database.ORM.*;

/**
 *
 * @author xorigin
 */
public class Administrator extends ModelUtility{
    
    private final IAdapter administratorModel;
    private SelectQuery selectQuery;
    private ResultSet resultSet;
    private Resource resource;
    
    public Administrator(IAdapter adapter){
    
        super(adapter);
        this.administratorModel = adapter;
    }
    
    public void insert(
            
            String name, String nationalID, String address, String email, String phoneNumber, String gender,
            String dateOfBirth, String ID, String password, String role, String dateOfContract 
    ){
        
        List<Enum> fields = Arrays.asList(Column.Name, Column.NationalID, Column.Address, Column.Email,
                                          Column.PhoneNumber, Column.Gender, Column.DateOfBirth, Column.ID,
                                          Column.Password, Column.Role, Column.DateOfContract);
        
        List<Object> values = Arrays.asList(name, nationalID, address, email, phoneNumber, gender,
                                                dateOfBirth, ID, password, role, dateOfContract);
        
        this.administratorModel.insert(fields, values);
    }
    
    
    public void update(List<Enum> fields, List<Object> values, String ID){
    
        this.administratorModel.update(fields, values, this.administratorModel.Where(Column.ID, "=", ID));
    }
    
    
    public void delete(String ID){
    
        this.administratorModel.delete(this.administratorModel.Where(Column.ID, "=", ID));
    }
    
    
    public boolean isAdministratorExists(String ID){

        boolean isExists = false;
        
        this.selectQuery = new SelectBuilder(Arrays.asList(this.administratorModel.Aggregate("count", "", Column.ID)),
                                        Table.Administrator)
                                        .where(Column.ID, "=", ID).build();

        this.resultSet = QueryExecutor.executeSelectQuery(this.selectQuery);
        this.resource = new Resource(this.resultSet);

        try { 
        
            if(!this.resource.isResultSetEmpty())
                isExists = (this.resultSet.getInt(1) == 1);
            
        } catch(SQLException ex){
            System.out.println(ex);
        } finally {
            this.resource.close();
        }
        
        return isExists;
    }
    
    
    public int getNumOfRegisteredInRole(Enum role){

        int numOfRegisteredInRole = 0;
        
        this.selectQuery = new SelectBuilder(Arrays.asList(this.administratorModel.Aggregate("count", "", Column.Role)),
                                        Table.Administrator)
                                        .where(Column.Role, "=", role.name()).build();

        this.resultSet = QueryExecutor.executeSelectQuery(this.selectQuery);
        this.resource = new Resource(this.resultSet);

        try { 
        
            if(!this.resource.isResultSetEmpty())
                numOfRegisteredInRole = this.resultSet.getInt(1);
            
        } catch(SQLException ex){
            System.out.println(ex);
        } finally {
            this.resource.close();
        }
        
        return numOfRegisteredInRole;
    }
    
     
    public boolean isValidAccount(String ID, String password){

        if(!this.isAdministratorExists(ID))
            return false;
        
        return super.isPasswordMatch(ID, password);
    }
    
}