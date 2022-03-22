package Models.Database.ORM;

import Models.Database.ORM.Utilities.Debugger;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author xorigin
 */
public class Resource {
    
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    
    
    public Resource(ResultSet resource){
    
        this.resultSet = resource;
        
        try {
            
            this.statement = resource.getStatement();
            this.connection = resource.getStatement().getConnection();
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
    public Resource(Statement resource){
    
        this.resultSet = null;
        this.statement = resource;
        
        try {
            
            this.connection = resource.getConnection();
        
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
    }
    
    
    public boolean isResultSetEmpty(){
    
        try {
            
            if(this.resultSet == null || this.resultSet.isClosed())
                Debugger.emptyResultSet(true);
            
            return this.resultSet == null || this.resultSet.isClosed();
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return false;
    }
    
    
    public void close(){
    
        try {
            
            this.statement.close();
            this.connection.close();
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}
