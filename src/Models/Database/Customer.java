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
import Models.EBS_DbContext;
import Models.Enum.PaymentState;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author xorigin
 */
public class Customer extends ModelUtility{
    
    private final IAdapter customerModel;
    private SelectQuery selectQuery;
    private ResultSet resultSet;
    private Resource resource;
    
    public Customer(IAdapter adapter){
    
        super(adapter);
        this.customerModel = adapter;
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
        
        this.customerModel.insert(fields, values);
        this.insertFirstCustomerBill(governmentCode, meterCode, dateOfContract);
    }
    
    private void insertFirstCustomerBill(String governmentCode, String meterCode, String dateOfContract){
                
        new EBS_DbContext().getBillModel()
            .insert(governmentCode, meterCode, 0, 0, 0, 0, 0.0, PaymentState.Paid.name(), this.getReleaseDate(dateOfContract));
    }
    
    private String getReleaseDate(String dateOfContract){
    
        // For example 03/2022
        Matcher matcher = Pattern.compile("(\\d\\d\\/\\d\\d\\d\\d)").matcher(dateOfContract);
        
        return (matcher.find() ? matcher.group(1) : dateOfContract.substring(0, 7));
    }
    
    public void update(List<Enum> fields, List<Object> values, String meterCode){
    
        this.customerModel.update(fields, values, this.customerModel.Where(Column.MeterCode, "=", meterCode));
    }
    
    
    public void delete(String meterCode){
    
        this.customerModel.delete(this.customerModel.Where(Column.MeterCode, "=", meterCode));
    }
    
        
    public void toggleActivation(String meterCode){
    
        String currentActivationState = (String) super.getInfo(Arrays.asList(Column.Activation), meterCode).get(Column.Activation);
        
        currentActivationState = (currentActivationState.equals(ActivationState.Active.name()) ? 
                        ActivationState.Inactive.name() : ActivationState.Active.name());
            
        this.customerModel.update(Arrays.asList(Column.Activation), Arrays.asList(currentActivationState),
                             this.customerModel.Where(Column.MeterCode, "=", meterCode));
    }
    
    
    public int getNumOfCustomers(){
    
        int numOfCustomers = 0;
        
        this.selectQuery = new SelectBuilder(Arrays.asList(this.customerModel.Aggregate("count", "", Column.MeterCode)),
                                        Table.Customer)
                                        .build();
        
        this.resultSet = QueryExecutor.executeSelectQuery(this.selectQuery);
        this.resource = new Resource(this.resultSet);

        try { 
        
            if(!this.resource.isResultSetEmpty())
                numOfCustomers = this.resultSet.getInt(1);
            
        } catch(SQLException ex){
            System.out.println(ex);
        } finally {
            this.resource.close();
        }
        
        return numOfCustomers;
    }
    
    
    public boolean isMeterCodeExists(String meterCode){
        
        boolean isExists = false;
        
        this.selectQuery = new SelectBuilder(Arrays.asList(this.customerModel.Aggregate("count", "", Column.MeterCode)),
                                             Table.Customer)
                                            .where(Column.MeterCode, "=", meterCode).build();
        
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
    
    
    public boolean isMeterCodeActive(String meterCode){
    
        boolean isActive = false;
        
        if(!this.isMeterCodeExists(meterCode))
            return false;
        
        this.selectQuery = new SelectBuilder(Arrays.asList(Column.Activation),
                                             Table.Customer)
                                            .where(Column.MeterCode, "=", meterCode).build();
        
        this.resultSet = QueryExecutor.executeSelectQuery(this.selectQuery);
        this.resource = new Resource(this.resultSet);

        try { 
        
            if(!this.resource.isResultSetEmpty())
                isActive = (this.resultSet.getString(Column.Activation.name()).equals(ActivationState.Active.name()));
            
        } catch(SQLException ex){
            System.out.println(ex);
        } finally {
            this.resource.close();
        }
        
        return isActive;
    }
    
    
    public boolean isValidAccount(String meterCode, String password){

        if(!this.isMeterCodeExists(meterCode))
            return false;
        
        return super.isPasswordMatch(meterCode, password);
    }
    
}
