package Models.Database.ORM;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author 0xOrigin
 */
public class DatabaseConnection {
    
    // Applying Singleton pattern
    private static Connection uniqueInstance;
    
    private DatabaseConnection(){
    
    }
    
    public static Connection getInstance() throws SQLException{
    
        if(uniqueInstance == null || uniqueInstance.isClosed())
            uniqueInstance = new DatabaseConnection().getConnection();
        
        return uniqueInstance;
    }
    
    private Connection getConnection(){
        
        Connection connection = null; 
        
        try {
            
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:EBS.db");
            
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