package Models.Database;

import Models.Enum.Column;
import Models.Enum.Table;
import Models.Interface.IAdapter;
import java.util.Arrays;
import java.sql.ResultSet;
import java.sql.SQLException;
import Models.Database.ORM.*;

/**
 *
 * @author xorigin
 */
public class CollectedMoney {
    
    private final IAdapter collectedMoneyTable;
    private SelectQuery selectQuery;
    private ResultSet resultSet;
    private Resource resource;
    
    public CollectedMoney(IAdapter adapter){

        this.collectedMoneyTable = adapter;
    }
    
    public void collectPayment(double moneyValue){
    
        collectedMoneyTable.update(Arrays.asList(Column.TotalCollected),
                                   Arrays.asList(Column.TotalCollected.name() + "+" + moneyValue),
                                   "1 == 1");
    }
    
    public String getTotalCollected() throws SQLException{
    
        selectQuery = new SelectBuilder(Arrays.asList(Column.TotalCollected), Table.CollectedMoney).build();
        resultSet = QueryExecutor.executeSelectQuery(selectQuery);
        
        resource = new Resource(resultSet);
        
        if(!resource.isResultSetEmpty()){
            
            String result = String.format("%,.2f", resultSet.getDouble(Column.TotalCollected.name()));
            
            resource.close();
            
            return result;
        }
    
        return "";
    }
    
}