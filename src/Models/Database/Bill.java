package Models.Database;

import Models.Enum.Table;
import Models.Enum.Column;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import Models.Database.ORM.*;
import Models.Enum.PaymentState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import Models.Database.ORM.Adapter;
import Models.ModelExceptionHandler;

/**
 *
 * @author xorigin
 */
public class Bill{
    
    private final Adapter billModel;
    private SelectQuery selectQuery;
    private ResultSet resultSet;
    private Resource resource;
    
    public Bill(Adapter adapter){
    
        this.billModel = adapter;
    }
    
    void insert(
            
            String governmentCode, String meterCode, int tariff, int lastReading, int currentReading, int consumption,
            double moneyValue, String status, String releaseDate
    ){
    
        List<Enum> fields = Arrays.asList(Column.GovernmentCode, Column.MeterCode, Column.Tariff, Column.LastReading,
                                          Column.CurrentReading, Column.Consumption, Column.MoneyValue, Column.Status,
                                          Column.ReleaseDate);
        
        List<Object> values = Arrays.asList(governmentCode, meterCode, tariff, lastReading, currentReading, consumption,
                                            moneyValue, status, releaseDate);
        
        this.billModel.insert(fields, values);
    }
    
    
    public void payBill(String meterCode){
    
        this.selectQuery = new SelectBuilder(Arrays.asList(Column.Num), Table.Bill)
                                        .where(Column.MeterCode, "=", meterCode)
                                        .operator("and")
                                        .where(Column.Status, "=", PaymentState.Unpaid.name())
                                        .orderBy(Column.Num, "ASC")
                                        .limit("1")
                                        .build();
        
        this.billModel.update(Arrays.asList(Column.Status), Arrays.asList(PaymentState.Paid.name()), this.billModel.Where(Column.Num, "in", selectQuery.toString()));
    }
    
    
    public void releaseNewBill(String meterCode, String releaseDate, int currentReading, int consumption, double moneyValue, int tariff){
        
        Map<Enum, Object> info = this.getLastBillInfo(Arrays.asList(Column.CurrentReading, Column.GovernmentCode), meterCode);
        
        int lastReading = (int) info.get(Column.CurrentReading);
        
        this.insert((String) info.get(Column.GovernmentCode), meterCode,
                    tariff, lastReading, currentReading, consumption,
                    moneyValue, PaymentState.Unpaid.name(), releaseDate);
    }
    
    
    public void complainAboutBill(String complain, int billNumber){
    
        this.billModel.update(Arrays.asList(Column.Complain),
                              Arrays.asList(complain),
                              this.billModel.Where(Column.Num, "=", String.valueOf(billNumber)));
    }
    
    
    public List<Map<Enum, Object>> getAllBillsOfMeterCode(String meterCode){
        
        List<Enum> fields = Arrays.asList(Column.Num, Column.GovernmentCode, Column.Tariff, Column.LastReading,
                                         Column.CurrentReading, Column.Consumption, Column.MoneyValue, Column.Status,
                                         Column.ReleaseDate);
        
        this.selectQuery = new SelectBuilder(Arrays.asList(fields),
                                        Table.Bill)
                                        .where(Column.MeterCode, "=", meterCode)
                                        .orderBy(Column.Num, "ASC")
                                        .build();
        
        return this.getBills(fields, this.selectQuery);
    }
    
    
    public List<Map<Enum, Object>> getAllBillsOfRegion(String governmentCode){

        List<Enum> fields = Arrays.asList(Column.Num, Column.MeterCode, Column.Tariff, Column.LastReading,
                                         Column.CurrentReading, Column.Consumption, Column.MoneyValue, Column.Status,
                                         Column.ReleaseDate);
        
        this.selectQuery = new SelectBuilder(Arrays.asList(fields),
                                        Table.Bill)
                                        .where(Column.GovernmentCode, "=", governmentCode)
                                        .orderBy(Column.Num, "ASC")
                                        .build();
        
        return this.getBills(fields, this.selectQuery);
    }
    
    private List<Map<Enum, Object>> getBills(List<Enum> fields, SelectQuery selectQuery){
    
        Map<Enum, Object> billInfo;
        List<Map<Enum, Object>> billsContainer = new ArrayList<>();
        
        this.resultSet = QueryExecutor.executeSelectQuery(selectQuery);
        this.resource = new Resource(this.resultSet);
        
        try { 
        
            if(!this.resource.isResultSetEmpty()){
                
                while(this.resultSet.next()){

                    billInfo = new HashMap<>();

                    for(Enum field : fields)
                        billInfo.put(field, this.resultSet.getObject(field.name()));
                    
                    billsContainer.add(billInfo);
                }
            }
            
        } catch(SQLException ex){
            ModelExceptionHandler.handle(ex, true);
        } finally {
            this.resource.close();
        }
        
        return billsContainer;
    }
    
    public int getNumOfUnpaidBills(String meterCode){
    
        int numOfUnpaidBills = 0;
        
        this.selectQuery = new SelectBuilder(Arrays.asList(this.billModel.Aggregate("count", "", Column.Status)),
                                             Table.Bill)
                                            .where(Column.MeterCode, "=", meterCode)
                                            .operator("and")
                                            .where(Column.Status, "=", PaymentState.Unpaid.name())
                                            .build();

        this.resultSet = QueryExecutor.executeSelectQuery(this.selectQuery);
        this.resource = new Resource(this.resultSet);

        try { 
        
            if(!this.resource.isResultSetEmpty())
                numOfUnpaidBills = this.resultSet.getInt(1);
            
        } catch(SQLException ex){
            ModelExceptionHandler.handle(ex, true);
        } finally {
            this.resource.close();
        }
        
        return numOfUnpaidBills;
    }
    
  
    public int getSumOfConsumptionsForRegion(String governmentCode){
    
        int sumOfConsumptions = 0;
        
        this.selectQuery = new SelectBuilder(Arrays.asList(this.billModel.Aggregate("sum", "", Column.Consumption)),
                                        Table.Bill)
                                        .where(Column.GovernmentCode, "=", governmentCode)
                                        .build();
        
        this.resultSet = QueryExecutor.executeSelectQuery(this.selectQuery);
        this.resource = new Resource(this.resultSet);
        
        try { 
        
            if(!this.resource.isResultSetEmpty())
                sumOfConsumptions = this.resultSet.getInt(1);
            
        } catch(SQLException ex){
            ModelExceptionHandler.handle(ex, true);
        } finally {
            this.resource.close();
        }
        
        return sumOfConsumptions;
    }
    
    
    public int getActualNumberOfConsumersForRegion(String governmentCode){
    
        int actualNumberOfConsumers = 0;
        
        this.selectQuery = new SelectBuilder(Arrays.asList(this.billModel.Aggregate("count", "distinct", Column.MeterCode)),
                                        Table.Bill)
                                        .where(Column.GovernmentCode, "=", governmentCode)
                                        .operator("and")
                                        .controlPrecedence("(")
                                        .where(Column.LastReading, "!=", 0)
                                        .operator("or")
                                        .where(Column.CurrentReading, "!=", 0)
                                        .controlPrecedence(")")
                                        .build();
        
        this.resultSet = QueryExecutor.executeSelectQuery(this.selectQuery);
        this.resource = new Resource(this.resultSet);
        
        try { 
        
            if(!this.resource.isResultSetEmpty())
                actualNumberOfConsumers = this.resultSet.getInt(1);
            
        } catch(SQLException ex){
            ModelExceptionHandler.handle(ex, true);
        } finally {
            this.resource.close();
        }
        
        return actualNumberOfConsumers;
    }
    
    
    public Map<Enum, Object> getLastBillInfo(List<Enum> fields, String meterCode){
    
        Map<Enum, Object> lastBillInfo = new HashMap<>();
        
        this.selectQuery = new SelectBuilder(Arrays.asList(fields), Table.Bill)
                                        .where(Column.MeterCode, "=", meterCode)
                                        .orderBy(Column.Num, "DESC")
                                        .limit("1")
                                        .build();
        
        this.resultSet = QueryExecutor.executeSelectQuery(this.selectQuery);
        this.resource = new Resource(this.resultSet);

        try { 
        
            if(!this.resource.isResultSetEmpty())
                for(Enum field : fields)
                    lastBillInfo.put(field, this.resultSet.getObject(field.name()));
            
        } catch(SQLException ex){
            ModelExceptionHandler.handle(ex, true);
        } finally {
            this.resource.close();
        }

        return lastBillInfo;
    }
    
}
