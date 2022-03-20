package Models;

import Models.Database.Bill;
import Models.Database.Customer;
import Models.Database.ORM.SQLiteAdapter;
import Models.Enum.Column;
import Models.Enum.Table;
import java.util.List;
import java.util.Map;
import Models.Interface.ICustomerDashboard;
import java.util.Arrays;

/**
 *
 * @author xorigin
 */
public class CustomerDashboard implements ICustomerDashboard {
    
    private final Bill billTable;
    private final Customer customerTable;
    
    public CustomerDashboard(){
    
        this.billTable = new Bill(new SQLiteAdapter(Table.Bill));
        this.customerTable = new Customer(new SQLiteAdapter(Table.Customer));
    }
    
    @Override
    public void releaseNewBill(String meterCode, String releaseDate, int currentReading, int consumption, double moneyValue, int tariff){
    
        this.billTable.releaseNewBill(meterCode, releaseDate, currentReading, consumption, moneyValue, tariff);
    }

    @Override
    public void complainAboutBill(String complain, int billNumber){
    
        this.billTable.complainAboutBill(complain, billNumber);
    }

    @Override
    public List<Map<Enum, Object>> getAllBillsOfMeterCode(String meterCode){
    
        return this.billTable.getAllBillsOfMeterCode(meterCode);
    }
    
    @Override
    public Map<Enum, Object> getLastBillInfo(List<Enum> fields, String meterCode){
    
        return this.billTable.getLastBillInfo(fields, meterCode);
    }
    
    @Override
    public int getNumOfUnpaidBills(String meterCode){
    
        return this.billTable.getNumOfUnpaidBills(meterCode);
    }
    
    @Override
    public String getActivationState(String meterCode){
    
        return (String) this.customerTable.getInfo(Arrays.asList(Column.Activation), meterCode).get(Column.Activation);
    }
    
    @Override
    public String getTypeOfUse(String meterCode){
    
        return (String) this.customerTable.getInfo(Arrays.asList(Column.TypeOfUse), meterCode).get(Column.TypeOfUse);
    }
}
