package Models.Database;

import Models.Interface.IAdapter;
import Models.Enum.Column;
import Models.Enum.Table;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import Models.Database.ORM.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author xorigin
 */
public class Administrator {
    
    private final IAdapter administratorTable;
    private SelectQuery selectQuery;
    private ResultSet resultSet;
    private Resource resource;
    
    public Administrator(IAdapter adapter){
    
        this.administratorTable = adapter;
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
        
        administratorTable.insert(fields, values);
    }
    
    
    public void update(List<Enum> fields, List<Object> values, String ID){
    
        administratorTable.update(fields, values, administratorTable.Where(Column.ID, "=", ID));
    }
    
    
    public void delete(String ID){
    
        administratorTable.delete(administratorTable.Where(Column.ID, "=", ID));
    }
    
    
    public boolean isAdministratorExists(String ID) throws SQLException{

        selectQuery = new SelectBuilder(Arrays.asList(administratorTable.Aggregate("count", "", Column.ID)),
                                        Table.Administrator)
                                        .where(Column.ID, "=", ID).build();

        resultSet = QueryExecutor.executeSelectQuery(selectQuery);

        resource = new Resource(resultSet);
        
        if(!resource.isResultSetEmpty()){

            boolean result = (resultSet.getInt(1) == 0);
            
            resource.close();
            
            return result;
        }

        resource.close();
        
        return false;
    }
    
    
    public int getNumOfSpecificRole(Enum role) throws SQLException{

        selectQuery = new SelectBuilder(Arrays.asList(administratorTable.Aggregate("count", "", Column.Role)),
                                        Table.Administrator)
                                        .where(Column.Role, "=", role.name()).build();

        resultSet = QueryExecutor.executeSelectQuery(selectQuery);

        resource = new Resource(resultSet);
        
        if(!resource.isResultSetEmpty()){

            int result = resultSet.getInt(1);
            
            resource.close();
            
            return result;
        }

        resource.close();
        
        return 0;
    }
    
    
    public Map<Enum, Object> getInfo(List<Enum> fields, String ID) throws SQLException{
        
        Map<Enum, Object> info = new HashMap<>();
        
        selectQuery = new SelectBuilder(Arrays.asList(fields),
                                        Table.Administrator)
                                        .where(Column.ID, "=", ID).build();
        
        resultSet = QueryExecutor.executeSelectQuery(selectQuery);

        resource = new Resource(resultSet);
        
        if(!resource.isResultSetEmpty()){

            for(Enum field : fields){
            
                info.put(field, resultSet.getObject(field.name()));
            }
            
            resource.close();
            
            return info;
        }
        
        resource.close();

        return info;
    }
    
    
    public boolean isValidAccount(String ID, String password) throws SQLException {
    
        if(!isAdministratorExists(ID))
            return false;
        
        selectQuery = new SelectBuilder(Arrays.asList(Column.Password),
                                        Table.Administrator)
                                        .where(Column.ID, "=", ID).build();

        resultSet = QueryExecutor.executeSelectQuery(selectQuery);

        resource = new Resource(resultSet);
        
        if(!resource.isResultSetEmpty()){
            
            boolean result = resultSet.getString(Column.Password.name()).equals(password);
            
            resource.close();
            
            return result;
        }

        resource.close();
        
        return false;
    }
    
}