package Models.Database.ORM;

import Models.Database.ORM.Utilities.ImageConverter;
import Models.Database.ORM.Utilities.Debugger;
import Models.AppDate.ConnectionStringImp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author xorigin
 */
public class QueryExecutor {
    
    static void execute(String query, Queue<String> imagesPaths){
        
        if(query.isBlank())
            throw new UnsupportedOperationException("Query is Empty!");
            
        PreparedStatement preparedStatement = prepareQuery(query, imagesPaths);

        try {
            
            // Determine the type of Query to execute
            switch(query.split(" ")[0].toLowerCase()){

                case "insert":
                    Debugger.printQuery(query, true);
                    preparedStatement.execute();
                    break;
                case "update":
                    Debugger.printQuery(query, true);
                    preparedStatement.executeUpdate();
                    break;
                case "delete":
                    Debugger.printQuery(query, true);
                    preparedStatement.execute();
                    break;
                default:
                    throw new UnsupportedOperationException("Invalid Query Syntax!");
            }
            
        } catch(SQLException ex){
            System.out.println(ex);
        }
        
        new Resource(preparedStatement).close();       
    }
    
    
    public static ResultSet executeSelectQuery(SelectQuery selectQuery){
    
        String query = selectQuery.toString();
        Debugger.printQuery(query, true);
        
        if(query.isBlank() || !query.toLowerCase().contains("select"))
            throw new UnsupportedOperationException("Invalid Query!");
        
        PreparedStatement preparedStatement = prepareQuery(query, new LinkedList<>());
        ResultSet resultSet = null;
        
        try {

            resultSet = preparedStatement.executeQuery();
            
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        
        return resultSet;
    }
    
    
    private static PreparedStatement prepareQuery(String query, Queue<String> imagesPaths){
    
        // Lazy connection to SQLite
        Connection connection = DatabaseConnection.getInstance(new ConnectionStringImp());
        PreparedStatement preparedStatement = null;

        try {
        
            preparedStatement = connection.prepareStatement(query);

            if(query.contains(" ? ")){

                int iterator = 1;

                while(!imagesPaths.isEmpty()){

                    preparedStatement.setBytes(iterator++, ImageConverter.readImage(imagesPaths.remove()));
                }
            }
            
        } catch (SQLException ex){
            System.out.println(ex);
        }
           
        return preparedStatement;
    }
    
}
