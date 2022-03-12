package Models.Database;

import Models.Database.Enum.Column;
import Models.Database.Enum.Table;
import Models.Database.Enum.ActivationState;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import Models.Database.ORM.*;

/**
 *
 * @author xorigin
 */
public class Customer {
    
    private final SQLiteAdapter customerTable;
    private SelectQuery selectQuery;
    private ResultSet resultSet;
    private Resource resource;
    
    public Customer(){
    
        this.customerTable = new SQLiteAdapter(Table.Customer);
    }
    
    public void insert(
            
            String name, String nationalID, String address, String email, String governmentCode, 
            String phoneNumber, String gender, String dateOfBirth, String typeOfUse, String meterCode, 
            String password, String activation, String dateOfContract, String propertyOwnershipContract 
    ){
        
        List<Enum> fields = Arrays.asList(Column.Name, Column.NationalID, Column.Address, Column.Email, Column.GovernmentCode,
                                          Column.PhoneNumber, Column.Gender, Column.DateOfBirth, Column.TypeOfUse, Column.MeterCode,
                                          Column.Password, Column.Activation, Column.DateOfContract, Column.PropertyOwnershipContract);
        
        List<Object> values = Arrays.asList(name, nationalID, address, email, governmentCode, phoneNumber, gender, dateOfBirth,
                                            typeOfUse, meterCode, password, activation, dateOfContract, propertyOwnershipContract);
        
        customerTable.insert(fields, values);
        
        Bill billDB = new Bill();
        billDB.insert(governmentCode, meterCode, 0, 0, 0, 0, 0.0, "Paid", dateOfContract.substring(8, 15));
    }
    
    
    public void update(List<Enum> fields, List<Object> values, String meterCode){
    
        customerTable.update(fields, values, customerTable.Where(Column.MeterCode, "=", meterCode));
    }
    
    
    public void delete(String meterCode){
    
        customerTable.delete(customerTable.Where(Column.MeterCode, "=", meterCode));
    }
    
    
    public void toggleActivation(String meterCode) throws SQLException{
    
        String currentState = getInfo(Column.Activation, meterCode);
        
        currentState = (currentState.equals(ActivationState.Active.name()) ? 
                        ActivationState.Inactive.name() : ActivationState.Active.name());
            
        customerTable.update(Arrays.asList(Column.Activation), Arrays.asList(currentState), meterCode);
    }
    
    
    public String getInfo(Enum field, String meterCode) throws SQLException{
    
        selectQuery = new SelectBuilder(Arrays.asList(field),
                                        Table.Customer)
                                        .where(Column.MeterCode, "=", meterCode).build();
        
        resultSet = QueryExecutor.executeSelectQuery(selectQuery);

        resource = new Resource(resultSet);
        
        if(!resource.isResultSetEmpty()){

            String result = resultSet.getString(field.name());
            
            resource.close();
            
            return result;
        }

        return "";
    }
    
    
    public int getNumOfCustomers() throws SQLException{
    
        selectQuery = new SelectBuilder(Arrays.asList(customerTable.Aggregate("count", "", Column.MeterCode)),
                                        Table.Customer)
                                        .build();
        
        resultSet = QueryExecutor.executeSelectQuery(selectQuery);

        resource = new Resource(resultSet);
        
        if(!resource.isResultSetEmpty()){

            int result = resultSet.getInt(1);
            
            resource.close();
            
            return result;
        }

        return 0;
    }
    
    
    public boolean[] isMeterCodeExists_Active(String meterCode) throws SQLException{
        
        boolean[] meterStatus = new boolean[2];
        
        selectQuery = new SelectBuilder(Arrays.asList(customerTable.Aggregate("count", "", Column.MeterCode), Column.Activation),
                                        Table.Customer)
                                        .where(Column.MeterCode, "=", meterCode).build();
        
        resultSet = QueryExecutor.executeSelectQuery(selectQuery);

        resource = new Resource(resultSet);
        
        if(!resource.isResultSetEmpty()){
            
            meterStatus[0] = (resultSet.getInt(1) == 1);
            meterStatus[1] = (resultSet.getString(Column.Activation.name()).equals(ActivationState.Active.name()));
            
            resource.close();
            
            return meterStatus;
        }

        return meterStatus;
    }
    
    
}
