package Models.Database;

import Models.Enum.Table;
import Models.Enum.Column;
import Models.Enum.ConsumptionStat;
import Models.Database.ORM.IAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import Models.Database.ORM.*;
import Models.Enum.PaymentState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author xorigin
 */
public class Bill{
    
    private final IAdapter billTable;
    private SelectQuery selectQuery;
    private ResultSet resultSet;
    private Resource resource;
    
    public Bill(IAdapter adapter){
    
        this.billTable = adapter;
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
        
        billTable.insert(fields, values);
    }
    
    
    public void payBill(String meterCode){
    
        selectQuery = new SelectBuilder(Arrays.asList(Column.Num), Table.Bill)
                                        .where(Column.MeterCode, "=", meterCode)
                                        .operator("and")
                                        .where(Column.Status, "=", PaymentState.Unpaid.name())
                                        .orderBy(Column.Num, "ASC")
                                        .limit("1")
                                        .build();
        
        billTable.update(Arrays.asList(Column.Status), Arrays.asList(PaymentState.Paid.name()), billTable.Where(Column.Num, "in", selectQuery.toString()));
    }
    
    
    public void releaseNewBill(String meterCode, String releaseDate, int currentReading, int consumption, double moneyValue, int tariff){
        
        Map<Enum, Object> info = getLastBillInfo(Arrays.asList(Column.CurrentReading, Column.GovernmentCode), meterCode);
        
        int lastReading = (int) info.get(Column.CurrentReading);
        
        insert((String) info.get(Column.GovernmentCode), meterCode,
               tariff, lastReading, currentReading, consumption,
               moneyValue, PaymentState.Unpaid.name(), releaseDate);
    }
    
    
    public void complainAboutBill(String complain, int billNumber){
    
        billTable.update(Arrays.asList(Column.Complain),
                         Arrays.asList(complain),
                         billTable.Where(Column.Num, "=", billNumber));
    }
    
    
    public List<Map<Enum, Object>> getAllBillsOfMeterCode(String meterCode){
    
        Map<Enum, Object> billInfo;
        List<Map<Enum, Object>> billsContainer = new ArrayList<>();
        
        List<Enum> fields = Arrays.asList(Column.Num, Column.GovernmentCode, Column.Tariff, Column.LastReading,
                                         Column.CurrentReading, Column.Consumption, Column.MoneyValue, Column.Status,
                                         Column.ReleaseDate);
        
        selectQuery = new SelectBuilder(Arrays.asList(fields),
                                        Table.Bill)
                                        .where(Column.MeterCode, "=", meterCode)
                                        .orderBy(Column.Num, "ASC")
                                        .build();
        
        resultSet = QueryExecutor.executeSelectQuery(selectQuery);
        resource = new Resource(resultSet);

        if(!resource.isResultSetEmpty()){

            try {
                
                while(resultSet.next()){

                    billInfo = new HashMap<>();

                    for(Enum field : fields){

                        billInfo.put(field, resultSet.getObject(field.name()));
                    }

                    billsContainer.add(billInfo);
                }
            
            } catch(SQLException ex){
                System.out.println(ex);
            }
        }

        resource.close();
        return billsContainer;
    }
    
    
    public List<Map<Enum, Object>> getAllBillsOfRegion(String governmentCode){
    
        Map<Enum, Object> billInfo;
        List<Map<Enum, Object>> billsContainer = new ArrayList<>();
        
        List<Enum> fields = Arrays.asList(Column.Num, Column.MeterCode, Column.Tariff, Column.LastReading,
                                         Column.CurrentReading, Column.Consumption, Column.MoneyValue, Column.Status,
                                         Column.ReleaseDate);
        
        selectQuery = new SelectBuilder(Arrays.asList(fields),
                                        Table.Bill)
                                        .where(Column.GovernmentCode, "=", governmentCode)
                                        .orderBy(Column.Num, "ASC")
                                        .build();
        
        resultSet = QueryExecutor.executeSelectQuery(selectQuery);
        resource = new Resource(resultSet);

        if(!resource.isResultSetEmpty()){

            try {
                
                while(resultSet.next()){

                    billInfo = new HashMap<>();

                    for(Enum field : fields){

                        billInfo.put(field, resultSet.getObject(field.name()));
                    }

                    billsContainer.add(billInfo);
                }
                
            } catch(SQLException ex){
                System.out.println(ex);
            }
        }

        resource.close();
        return billsContainer;
    }
    
    
    public int getNumOfUnpaidBills(String meterCode){
    
        selectQuery = new SelectBuilder(Arrays.asList(billTable.Aggregate("count", "", Column.Status)),
                                        Table.Bill)
                                        .where(Column.MeterCode, "=", meterCode)
                                        .operator("and")
                                        .where(Column.Status, "=", PaymentState.Unpaid.name())
                                        .build();

        resultSet = QueryExecutor.executeSelectQuery(selectQuery);
        resource = new Resource(resultSet);

        if(!resource.isResultSetEmpty()){

            try {
                
                int result = resultSet.getInt(1);

                resource.close();
                return result;
                
            } catch(SQLException ex){
                System.out.println(ex);
            }
        }

        resource.close();
        return 0;
    }
    
    
    public Map<Enum, Object> getConsumptionStatforRegion(String governmentCode){
    
        Map<Enum, Object> statInfo = new HashMap<>();
        
        selectQuery = new SelectBuilder(Arrays.asList(billTable.Aggregate("sum", "", Column.Consumption)),
                                        Table.Bill)
                                        .where(Column.GovernmentCode, "=", governmentCode)
                                        .build();
        
        resultSet = QueryExecutor.executeSelectQuery(selectQuery);
        resource = new Resource(resultSet);

        if(!resource.isResultSetEmpty()){

            try {
            
                statInfo.put(ConsumptionStat.SumOfConsumptions, resultSet.getInt(1));

            } catch(SQLException ex){
                System.out.println(ex);
            }
        }
        
        resource.close();

        
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

            try {
            
                statInfo.put(ConsumptionStat.ActualNumberOfConsumers, resultSet.getInt(1));

            } catch(SQLException ex){
                System.out.println(ex);
            }
        }

        resource.close();
        return statInfo;
    }
    
    
    public Map<Enum, Object> getLastBillInfo(List<Enum> fields, String meterCode){
    
        Map<Enum, Object> info = new HashMap<>();
        
        selectQuery = new SelectBuilder(Arrays.asList(fields), Table.Bill)
                                        .where(Column.MeterCode, "=", meterCode)
                                        .orderBy(Column.Num, "DESC")
                                        .limit("1")
                                        .build();
        
        resultSet = QueryExecutor.executeSelectQuery(selectQuery);
        resource = new Resource(resultSet);

        if(!resource.isResultSetEmpty()){

            try {
            
                for(Enum field : fields){

                    info.put(field, resultSet.getObject(field.name()));
                }

            } catch(SQLException ex){
                System.out.println(ex);
            }
        }

        resource.close();
        return info;
    }
}
