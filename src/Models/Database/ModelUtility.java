package Models.Database;

import Models.Database.ORM.QueryExecutor;
import Models.Database.ORM.Resource;
import Models.Database.ORM.SelectBuilder;
import Models.Database.ORM.SelectQuery;
import Models.Enum.Column;
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
    
    private final IAdapter tableInstance;
    private final Enum primaryKey;
    private SelectQuery selectQuery;
    private ResultSet resultSet;
    private Resource resource;
    
    
    protected ModelUtility(IAdapter adapter, Enum primaryKeyColumnName){
    
        this.tableInstance = adapter;
        this.primaryKey = primaryKeyColumnName;
    }
    
    
    public Map<Enum, Object> getInfo(List<Enum> fields, String identifier){
        
        Map<Enum, Object> info = new HashMap<>();
        
        selectQuery = new SelectBuilder(Arrays.asList(fields),
                                        this.tableInstance.getTableName())
                                        .where(this.primaryKey, "=", identifier).build();
        
        resultSet = QueryExecutor.executeSelectQuery(selectQuery);
        resource = new Resource(resultSet);

        if(!resource.isResultSetEmpty()){

            try {
            
                for(Enum field : fields){

                    info.put(field, resultSet.getObject(field.name()));
                }

                resource.close();
                return info;
                
            } catch(SQLException ex){
                System.out.println(ex);
            }
        }

        resource.close();
        return info;
    }
    
    
    public boolean isNationalIdExists(String nationalId){
    
        selectQuery = new SelectBuilder(Arrays.asList(this.tableInstance.Aggregate("count", "", Column.NationalID)),
                                        this.tableInstance.getTableName())
                                        .where(Column.NationalID, "=", nationalId)
                                        .build();
        
        resultSet = QueryExecutor.executeSelectQuery(selectQuery);
        resource = new Resource(resultSet);

        if(!resource.isResultSetEmpty()){

            try {
                
                boolean result = (resultSet.getInt(1) == 1);

                resource.close();
                return result;
            
            } catch(SQLException ex){
                System.out.println(ex);
            }
        }

        resource.close();
        return false;
    }
    
}
