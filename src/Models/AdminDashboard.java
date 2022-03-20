package Models;

import Models.Database.Administrator;
import Models.Database.Bill;
import Models.Database.CollectedMoney;
import Models.Database.Customer;
import Models.Database.ORM.SQLiteAdapter;
import Models.Enum.Table;
import java.util.List;
import java.util.Map;
import Models.Interface.IAdminDashboard;

/**
 *
 * @author xorigin
 */
public class AdminDashboard implements IAdminDashboard {
    
    private final Administrator administratorTable;
    private final Bill billTable;
    private final CollectedMoney collectedMoneyTable;
    private final Customer customerTable;
    
    public AdminDashboard(){
    
        this.administratorTable = new Administrator(new SQLiteAdapter(Table.Administrator));
        this.billTable = new Bill(new SQLiteAdapter(Table.Bill));
        this.collectedMoneyTable = new CollectedMoney(new SQLiteAdapter(Table.CollectedMoney));
        this.customerTable = new Customer(new SQLiteAdapter(Table.Customer));
    }
    
    @Override
    public List<Map<Enum, Object>> getAllBillsOfRegion(String governmentCode){
    
        return this.billTable.getAllBillsOfRegion(governmentCode);
    }
    
    @Override
    public Map<Enum, Object> getConsumptionStatforRegion(String governmentCode){

        return this.billTable.getConsumptionStatforRegion(governmentCode);
    }
    
    @Override
    public String getTotalCollected(){
    
        return this.collectedMoneyTable.getTotalCollected();
    }
    
    @Override
    public void addCustomer(
            
            String name, String nationalID, String address, String email, String governmentCode, 
            String phoneNumber, String gender, String dateOfBirth, String typeOfUse, String meterCode, 
            String password, String activation, String dateOfContract, String propertyOwnershipContract 
    ){
    
        this.customerTable.insert(name, nationalID, address, email, governmentCode, phoneNumber, gender, dateOfBirth, typeOfUse, meterCode, password, activation, dateOfContract, propertyOwnershipContract);
    }
    
    @Override
    public void updateCustomer(List<Enum> fields, List<Object> values, String meterCode){
    
        this.customerTable.update(fields, values, meterCode);
    }
    
    @Override
    public void deleteCustomer(String meterCode){
    
        this.customerTable.delete(meterCode);
    }
    
    @Override
    public void addAdministrator(
            
            String name, String nationalID, String address, String email, String phoneNumber, String gender,
            String dateOfBirth, String ID, String password, String role, String dateOfContract 
    ){
    
        this.administratorTable.insert(name, nationalID, address, email, phoneNumber, gender, dateOfBirth, ID, password, role, dateOfContract);
    }
    
    @Override
    public void updateAdministrator(List<Enum> fields, List<Object> values, String ID){
    
        this.administratorTable.update(fields, values, ID);
    }
    
    @Override
    public void deleteAdministrator(String ID){
    
        this.administratorTable.delete(ID);
    }
    
    @Override
    public boolean isCustomerNationalIdExists(String nationalId){
    
        return this.customerTable.isNationalIdExists(nationalId);
    }
    
    @Override
    public boolean isAdministratorNationalIdExists(String nationalId){
    
        return this.administratorTable.isNationalIdExists(nationalId);
    }
    
    @Override
    public boolean isAdministratorExists(String ID){
    
        return this.administratorTable.isAdministratorExists(ID);
    }

    @Override
    public boolean[] isMeterCodeExists_Active(String meterCode){
    
        return this.customerTable.isMeterCodeExists_Active(meterCode);
    }
    
    @Override
    public int getNumOfSpecificRole(Enum role){
        
        return this.administratorTable.getNumOfSpecificRole(role);
    }
    
    @Override
    public int getNumOfUnpaidBills(String meterCode){
    
        return this.billTable.getNumOfUnpaidBills(meterCode);
    }
    
}
