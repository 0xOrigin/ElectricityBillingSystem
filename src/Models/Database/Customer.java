package Models.Database;

import Models.Enum.Column;
import Models.Enum.Table;
import Models.Enum.ActivationState;
import Models.Database.ORM.IAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import Models.Database.ORM.*;
import Models.Enum.PaymentState;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author xorigin
 */
public class Customer extends ModelUtility {
    
    private final IAdapter customerTable;
    private SelectQuery selectQuery;
    private ResultSet resultSet;
    private Resource resource;
    
    public Customer(IAdapter adapter){
    
        super(adapter);
        this.customerTable = adapter;
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
        
        Bill billDB = new Bill(new SQLiteAdapter(Table.Bill));
        
        // For example 03/2022
        Matcher matcher = Pattern.compile("(\\d\\d\\/\\d\\d\\d\\d)").matcher(dateOfContract);
        
        String releaseDate = (matcher.find() ? matcher.group(1) : dateOfContract.substring(0, 7));
        
        billDB.insert(governmentCode, meterCode, 0, 0, 0, 0, 0.0, PaymentState.Paid.name(), releaseDate);
    }
    
    
    public void update(List<Enum> fields, List<Object> values, String meterCode){
    
        customerTable.update(fields, values, customerTable.Where(Column.MeterCode, "=", meterCode));
    }
    
    
    public void delete(String meterCode){
    
        customerTable.delete(customerTable.Where(Column.MeterCode, "=", meterCode));
    }
    
    
    public void toggleActivation(String meterCode) throws SQLException{
    
        String currentState = (String) getInfo(Arrays.asList(Column.Activation), meterCode).get(Column.Activation);
        
        currentState = (currentState.equals(ActivationState.Active.name()) ? 
                        ActivationState.Inactive.name() : ActivationState.Active.name());
            
        customerTable.update(Arrays.asList(Column.Activation), Arrays.asList(currentState),
                             customerTable.Where(Column.MeterCode, "=", meterCode));
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

        resource.close();
        
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
            
            if(!meterStatus[0])
                meterStatus[1] = false;
            else
                meterStatus[1] = (resultSet.getString(Column.Activation.name()).equals(ActivationState.Active.name()));
            
            resource.close();
            
            return meterStatus;
        }

        resource.close();
        
        return meterStatus;
    }
    
    
    public boolean isValidAccount(String meterCode, String password) throws SQLException{
    
        if(!isMeterCodeExists_Active(meterCode)[0])
            return false;
        
        selectQuery = new SelectBuilder(Arrays.asList(Column.Password),
                                        Table.Customer)
                                        .where(Column.MeterCode, "=", meterCode)
                                        .build();

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
