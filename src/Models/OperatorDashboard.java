package Models;

import Models.Database.Bill;
import Models.Database.CollectedMoney;
import Models.Database.Customer;
import Models.Database.ORM.SQLiteAdapter;
import Models.Enum.Table;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import Models.Interface.IOperatorDashboard;

/**
 *
 * @author xorigin
 */
public class OperatorDashboard implements IOperatorDashboard {
    
    private final Bill billTable;
    private final CollectedMoney collectedMoneyTable;
    private final Customer customerTable;
    
    public OperatorDashboard(){
    
        this.billTable = new Bill(new SQLiteAdapter(Table.Bill));
        this.collectedMoneyTable = new CollectedMoney(new SQLiteAdapter(Table.CollectedMoney));
        this.customerTable = new Customer(new SQLiteAdapter(Table.Customer));
    }
    
    @Override
    public void payBill(String meterCode){
    
        this.billTable.payBill(meterCode);
    }
    
    @Override
    public void collectPayment(double moneyValue){
    
        this.collectedMoneyTable.collectPayment(moneyValue);
    }
    
    @Override
    public List<Map<Enum, Object>> getAllBillsOfMeterCode(String meterCode) throws SQLException{
    
        return this.billTable.getAllBillsOfMeterCode(meterCode);
    }
    
    @Override
    public List<Map<Enum, Object>> getAllBillsOfRegion(String governmentCode) throws SQLException{
    
        return this.billTable.getAllBillsOfRegion(governmentCode);
    }
    
    @Override
    public boolean[] isMeterCodeExists_Active(String meterCode) throws SQLException{
    
        return this.customerTable.isMeterCodeExists_Active(meterCode);
    }
    
    @Override
    public void toggleActivation(String meterCode) throws SQLException{
    
        this.customerTable.toggleActivation(meterCode);
    }
    
    @Override
    public void deleteCustomer(String meterCode){
    
        this.customerTable.delete(meterCode);
    }
    
    @Override
    public int getNumOfUnpaidBills(String meterCode) throws SQLException{
    
        return this.billTable.getNumOfUnpaidBills(meterCode);
    }
    
    @Override
    public Map<Enum, Object> getLastBillInfo(List<Enum> fields, String meterCode) throws SQLException{
    
        return this.billTable.getLastBillInfo(fields, meterCode);
    }
    
}
