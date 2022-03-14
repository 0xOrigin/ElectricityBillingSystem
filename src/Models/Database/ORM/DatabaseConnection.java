package Models.Database.ORM;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Models.Interface.IConnection;

/**
 *
 * @author 0xOrigin
 */
public class DatabaseConnection {
    
    // Singleton pattern
    private static Connection uniqueInstance;
    
    private DatabaseConnection(){
    
    }
    
    public static Connection getInstance(IConnection connStrings) throws SQLException{
    
        if(uniqueInstance == null || uniqueInstance.isClosed())
            uniqueInstance = new DatabaseConnection().getConnection(connStrings);
        
        return uniqueInstance;
    }
    
    private Connection getConnection(IConnection connStrings){
        
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