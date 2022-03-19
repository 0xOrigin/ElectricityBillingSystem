package Models.Database;

import Models.Database.ORM.QueryExecutor;
import Models.Database.ORM.Resource;
import Models.Database.ORM.SelectBuilder;
import Models.Database.ORM.SelectQuery;
import Models.Enum.Column;
import Models.Enum.Table;
import Models.Database.ORM.IAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author xorigin
 */
abstract class ModelUtility {
    
    private final Enum tableName;
    private final IAdapter tableInstance;
    private SelectQuery selectQuery;
    private ResultSet resultSet;
    private Resource resource;
    
    
    protected ModelUtility(IAdapter adapter){
    
        this.tableInstance = adapter;
        this.tableName = adapter.getTableName();
    }
    
    
    public Map<Enum, Object> getInfo(List<Enum> fields, String identifier) throws SQLException{
        
        Map<Enum, Object> info = new HashMap<>();
        
        Enum columnName = (this.tableName == Table.Administrator ? Column.ID : Column.MeterCode);
        
        selectQuery = new SelectBuilder(Arrays.asList(fields),
                                        this.tableName)
                                        .where(columnName, "=", identifier).build();
        
        resultSet = QueryExecutor.executeSelectQuery(selectQuery);

        resource = new Resource(resultSet);
        
        if(!resource.isResultSetEmpty()){

            for(Enum field : fields){
            
                info.put(field, resultSet.getObject(field.name()));
            }
            
            resource.close();
            
            return info;
        }
        
        resource.close();

        return info;
    }
    
    
    public boolean isNationalIdExists(String nationalId) throws SQLException{
    
        selectQuery = new SelectBuilder(Arrays.asList(this.tableInstance.Aggregate("count", "", Column.NationalID)),
                                        this.tableName)
                                        .where(Column.NationalID, "=", nationalId)
                                        .build();
        
        resultSet = QueryExecutor.executeSelectQuery(selectQuery);

        resource = new Resource(resultSet);
        
        if(!resource.isResultSetEmpty()){
            
            boolean result = (resultSet.getInt(1) == 1);
            
            resource.close();
            
            return result;
        }

        resource.close();
        
        return false;
    }
    
}
