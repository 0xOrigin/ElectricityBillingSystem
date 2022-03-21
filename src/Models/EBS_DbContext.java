package Models;

import Models.Database.Administrator;
import Models.Database.Bill;
import Models.Database.CollectedMoney;
import Models.Database.Customer;
import Models.Database.ORM.SQLiteAdapter;
import Models.Enum.Column;
import Models.Enum.Table;

/**
 *
 * @author xorigin
 */
public class EBS_DbContext implements IDbContext{
    
    private final Administrator administratorModel;
    private final Bill billModel;
    private final CollectedMoney collectedMoneyModel;
    private final Customer customerModel;
    
    
    public EBS_DbContext(){
    
        this.administratorModel = new Administrator(new SQLiteAdapter(Table.Administrator, Column.ID));
        
        this.billModel = new Bill(new SQLiteAdapter(Table.Bill, Column.Num));
        
        this.collectedMoneyModel = new CollectedMoney(new SQLiteAdapter(Table.CollectedMoney, Column.TotalCollected));
        
        this.customerModel = new Customer(new SQLiteAdapter(Table.Customer, Column.MeterCode));
    }
    
    @Override
    public Administrator getAdministratorModel(){
    
        return this.administratorModel;
    }
    
    @Override
    public Bill getBillModel(){
    
        return this.billModel;
    }
    
    @Override
    public CollectedMoney getCollectedMoneyModel(){
    
        return this.collectedMoneyModel;
    }
    
    @Override
    public Customer getCustomerModel(){
    
        return this.customerModel;
    }
    
}
