package Models.Database;

import Models.Enum.Column;
import Models.Enum.Table;
import java.util.Arrays;
import java.sql.ResultSet;
import java.sql.SQLException;
import Models.Database.ORM.*;
import Models.Database.ORM.Adapter;

/**
 *
 * @author xorigin
 */
public class CollectedMoney{
    
    private final Adapter collectedMoneyModel;
    private SelectQuery selectQuery;
    private ResultSet resultSet;
    private Resource resource;
    
    public CollectedMoney(Adapter adapter){

        this.collectedMoneyModel = adapter;
    }
    
    public void collectPayment(double moneyValue){
    
        this.collectedMoneyModel.update(Arrays.asList(Column.TotalCollected),
                                   Arrays.asList(Column.TotalCollected.name() + "+" + moneyValue),
                                   "1 == 1");
    }
    
    public double getTotalCollected(){
    
        double totalCollected = 0.0;
        
        this.selectQuery = new SelectBuilder(Arrays.asList(Column.TotalCollected), Table.CollectedMoney).build();
        
        this.resultSet = QueryExecutor.executeSelectQuery(this.selectQuery);
        this.resource = new Resource(this.resultSet);

        try { 
        
            if(!this.resource.isResultSetEmpty())
                totalCollected = this.resultSet.getDouble(Column.TotalCollected.name());
            
        } catch(SQLException ex){
            System.out.println(ex);
        } finally {
            this.resource.close();
        }
        
        return totalCollected;
    }
    
}