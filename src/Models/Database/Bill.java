package Models.Database;

import Models.Enum.Table;
import Models.Enum.Column;
import Models.Enum.ConsumptionStat;
import Models.Interface.IAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import Models.Database.ORM.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author xorigin
 */
public class Bill {
    
    private final IAdapter billTable;
    private SelectQuery selectQuery;
    private ResultSet resultSet;
    private Resource resource;
    
    public Bill(IAdapter adapter){
    
        this.billTable = adapter;
    }
    
    public void insert(
            
            String governmentCode, String meterCode, int tariff, int lastReading, int currentReading, int consumption,
            double moneyValue, String status, String releaseDate
    ){
    
        List<Enum> fields = Arrays.asList(Column.GovernmentCode, Column.MeterCode, Column.Tariff, Column.LastReading,
                                          Column.CurrentReading, Column.Consumption, Column.MoneyValue, Column.Status,
                                          Column.ReleaseDate);
        
        List<Object> values = Arrays.asList(governmentCode, meterCode, tariff, lastReading, currentReading, consumption,
                                            moneyValue, status, releaseDate);
        
        billTable.insert(fields, values);
    }
    
    
    public void payBill(String meterCode){
    
        selectQuery = new SelectBuilder(Arrays.asList(Column.Status), Table.Bill)
                                        .where(Column.MeterCode, "=", meterCode)
                                        .operator("and")
                                        .where(Column.Status, "=", "Unpaid")
                                        .orderBy(Column.Num, "ASC")
                                        .limit("1")
                                        .build();
        
        billTable.update(Arrays.asList(Column.Status), Arrays.asList("Paid"), selectQuery.toString());
    }
    
    
    public void releaseNewBill(String meterCode, String releaseDate, int currentReading, int tariff, double moneyValue) throws SQLException{
        
        int lastReading = (int) getBillConstructionInfo(Column.CurrentReading, meterCode);
        
        insert((String) getBillConstructionInfo(Column.GovernmentCode, meterCode), meterCode,
               tariff, lastReading, currentReading,
               getConsumption(lastReading, currentReading), moneyValue, "Unpaid",
               releaseDate);
    }
    
    private int getConsumption(int lastReading, int currentReading) throws SQLException{
        
        return currentReading - lastReading;
    }
    
    public void complainAboutBill(String complain, int billNumber){
    
        billTable.update(Arrays.asList(Column.Complain),
                         Arrays.asList(complain),
                         billTable.Where(Column.Num, "=", billNumber));
    }
    
    
    public List<Map<Enum, Object>> getAllBillsOfMeterCode(String meterCode) throws SQLException{
    
        Map<Enum, Object> billInfo;
        List<Map<Enum, Object>> billsContainer = new ArrayList<>();
        
        List<Object> fields = Arrays.asList(Column.Num, Column.GovernmentCode, Column.Tariff, Column.LastReading,
                                         Column.CurrentReading, Column.Consumption, Column.MoneyValue, Column.Status,
                                         Column.ReleaseDate);
        
        selectQuery = new SelectBuilder(fields,
                                        Table.Bill)
                                        .where(Column.MeterCode, "=", meterCode)
                                        .orderBy(Column.Num, "ASC")
                                        .build();
        
        resultSet = QueryExecutor.executeSelectQuery(selectQuery);
        
        resource = new Resource(resultSet);
        
        if(!resource.isResultSetEmpty()){
        
            while(resultSet.next()){
                
                billInfo = new HashMap<>();
                
                billInfo.put(Column.Num, resultSet.getInt(Column.Num.name()));
                billInfo.put(Column.GovernmentCode, resultSet.getString(Column.GovernmentCode.name()));
                billInfo.put(Column.Tariff, resultSet.getInt(Column.Tariff.name()));
                billInfo.put(Column.LastReading, resultSet.getInt(Column.LastReading.name()));
                billInfo.put(Column.CurrentReading, resultSet.getInt(Column.CurrentReading.name()));
                billInfo.put(Column.Consumption, resultSet.getInt(Column.Consumption.name()));
                billInfo.put(Column.MoneyValue, resultSet.getDouble(Column.MoneyValue.name()));
                billInfo.put(Column.Status, resultSet.getString(Column.Status.name()));
                billInfo.put(Column.ReleaseDate, resultSet.getString(Column.ReleaseDate.name()));

                billsContainer.add(billInfo);
            }
            
            resource.close();
        }
    
        return billsContainer;
    }
    
    
    public List<Map<Enum, Object>> getAllBillsOfRegion(String governmentCode) throws SQLException{
    
        Map<Enum, Object> billInfo;
        List<Map<Enum, Object>> billsContainer = new ArrayList<>();
        
        List<Object> fields = Arrays.asList(Column.Num, Column.MeterCode, Column.Tariff, Column.LastReading,
                                         Column.CurrentReading, Column.Consumption, Column.MoneyValue, Column.Status,
                                         Column.ReleaseDate);
        
        selectQuery = new SelectBuilder(fields,
                                        Table.Bill)
                                        .where(Column.GovernmentCode, "=", governmentCode)
                                        .orderBy(Column.Num, "ASC")
                                        .build();
        
        resultSet = QueryExecutor.executeSelectQuery(selectQuery);
        
        resource = new Resource(resultSet);
        
        if(!resource.isResultSetEmpty()){
        
            while(resultSet.next()){
                
                billInfo = new HashMap<>();
                
                billInfo.put(Column.Num, resultSet.getInt(Column.Num.name()));
                billInfo.put(Column.MeterCode, resultSet.getString(Column.MeterCode.name()));
                billInfo.put(Column.Tariff, resultSet.getInt(Column.Tariff.name()));
                billInfo.put(Column.LastReading, resultSet.getInt(Column.LastReading.name()));
                billInfo.put(Column.CurrentReading, resultSet.getInt(Column.CurrentReading.name()));
                billInfo.put(Column.Consumption, resultSet.getInt(Column.Consumption.name()));
                billInfo.put(Column.MoneyValue, resultSet.getDouble(Column.MoneyValue.name()));
                billInfo.put(Column.Status, resultSet.getString(Column.Status.name()));
                billInfo.put(Column.ReleaseDate, resultSet.getString(Column.ReleaseDate.name()));

                billsContainer.add(billInfo);
            }
            
            resource.close();
        }
    
        return billsContainer;
    }
    
    
    public int getNumOfUnpaidBills(String meterCode) throws SQLException{
    
        selectQuery = new SelectBuilder(Arrays.asList(billTable.Aggregate("count", "", Column.Status)),
                                        Table.Bill)
                                        .where(Column.MeterCode, "=", meterCode)
                                        .operator("and")
                                        .where(Column.Status, "=", "Unpaid")
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
    
    
    public Map<Enum, Object> getConsumptionStatforRegion(String governmentCode) throws SQLException{
    
        Map<Enum, Object> statInfo = new HashMap<>();
        
        selectQuery = new SelectBuilder(Arrays.asList(billTable.Aggregate("sum", "", Column.Consumption)),
                                        Table.Bill)
                                        .where(Column.GovernmentCode, "=", governmentCode)
                                        .build();
        
        resultSet = QueryExecutor.executeSelectQuery(selectQuery);
        
        resource = new Resource(resultSet);
        
        if(!resource.isResultSetEmpty()){
        
            statInfo.put(ConsumptionStat.SumOfConsumptions, resultSet.getInt(1));
            
            resource.close();
        }
    
        
        selectQuery = new SelectBuilder(Arrays.asList(billTable.Aggregate("count", "distinct", Column.MeterCode)),
                                        Table.Bill)
                                        .where(Column.GovernmentCode, "=", governmentCode)
                                        .operator("and")
                                        .controlPrecedence("(")
                                        .where(Column.LastReading, "!=", 0)
                                        .operator("or")
                                        .where(Column.CurrentReading, "!=", 0)
                                        .controlPrecedence(")")
                                        .build();
        
        resultSet = QueryExecutor.executeSelectQuery(selectQuery);
        
        resource = new Resource(resultSet);
        
        if(!resource.isResultSetEmpty()){
        
            statInfo.put(ConsumptionStat.ActualNumberOfConsumers, resultSet.getInt(1));
            
            resource.close();
        }
        
        return statInfo;
    }
    
    
    public Object getBillConstructionInfo(Enum field, String meterCode) throws SQLException {
    
        selectQuery = new SelectBuilder(Arrays.asList(field), Table.Bill)
                                        .where(Column.MeterCode, "=", meterCode)
                                        .orderBy(Column.Num, "DESC")
                                        .limit("1")
                                        .build();
        
        resultSet = QueryExecutor.executeSelectQuery(selectQuery);
        
        resource = new Resource(resultSet);
        
        if(!resource.isResultSetEmpty()){

            Object result = resultSet.getObject(field.name());
            
            resource.close();
            
            return result;
        }

        return 0;
    }
}
