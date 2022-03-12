package Models.Database.ORM;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author xorigin
 */
public class Resource {
    
    private final Connection connection;
    private final Statement statement;
    private final ResultSet resultSet;
    
    
    public Resource(ResultSet resource) throws SQLException{
    
        this.resultSet = resource;
        this.statement = resource.getStatement();
        this.connection = resource.getStatement().getConnection();
    }
    
    
    public Resource(Statement resource) throws SQLException{
    
        this.resultSet = null;
        this.statement = resource;
        this.connection = resource.getConnection();
    }
    
    
    public boolean isResultSetEmpty() throws SQLException{
    
        if(resultSet == null || resultSet.isClosed())
            System.out.println("Empty ResultSet!");
        
        return resultSet == null || resultSet.isClosed();
    }
    
    
    public void close() throws SQLException{
    
        this.statement.close();
        this.connection.close();
    }
    
}
