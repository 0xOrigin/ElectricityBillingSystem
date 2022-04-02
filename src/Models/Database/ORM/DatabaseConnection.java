package Models.Database.ORM;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Models.AppDate.ConnectionString;

/**
 *
 * @author 0xOrigin
 */
class DatabaseConnection {
    
    // Singleton pattern
    private static Connection uniqueInstance;
    
    private DatabaseConnection(){
    
    }
    
    static Connection getInstance(ConnectionString connStrings){
    
        try {
            
            if(uniqueInstance == null || uniqueInstance.isClosed())
                uniqueInstance = new DatabaseConnection().getConnection(connStrings);
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return uniqueInstance;
    }
    
    private Connection getConnection(ConnectionString connStrings){
        
        Connection connection = null; 
        
        try {
            
            Class.forName(connStrings.getClassName());
            connection = DriverManager.getConnection(connStrings.getConnectionPath());
            
            try(PreparedStatement preparedStatement = connection.prepareStatement("PRAGMA foreign_keys = ON;");){
                
                preparedStatement.execute();
            
            } catch (SQLException exception){
                
                System.out.println(exception.toString());
            
            }
            
        } catch (ClassNotFoundException | SQLException exception) {
            
            System.out.println(exception.toString());
            
        }

        return connection;
    }
       
}