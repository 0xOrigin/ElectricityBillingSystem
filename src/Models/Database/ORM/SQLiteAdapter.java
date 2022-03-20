package Models.Database.ORM;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 *
 * @author xorigin
 */
public class SQLiteAdapter extends DML implements IAdapter {
    
    private final Enum table;
    
    public SQLiteAdapter(Enum table){
    
        this.table = table;
    }    
    
    
    @Override
    public void insert(List<Enum> fields, List<Object> values){
    
        if(checkEquality(fields.size(), values.size())){
        
            String query = "INSERT INTO " + this.table.name() + " (" + fields.toString().replaceAll("[\\[\\]]", "") + ")" +
                           " VALUES "  + "(" + processValues(values) + ")";
            
            QueryExecutor.execute(query, getImagesPaths(values));
        }
    }
    
    
    @Override
    public void update(List<Enum> fields, List<Object> values, String where){
        
        if(checkEquality(fields.size(), values.size())){
            
            String query = "UPDATE " + this.table.name() + " SET";
            
            for(int index = 0; index < fields.size(); index++)
                query = query.concat(" " + fields.get(index).name() + "=" + processValues(Arrays.asList(values.get(index))) + ",");

            query = query.substring(0, query.length()-1).concat(" WHERE " + where);

            QueryExecutor.execute(query, getImagesPaths(values));
        }
    }
    
    
    @Override
    public void delete(String where){
        
        String query = "DELETE FROM " + this.table.name() + " WHERE " + where;
        
        QueryExecutor.execute(query, new LinkedList<>());
    }
    
    @Override
    public Enum getTableName(){
    
        return this.table;
    }
    
    
    private static String processValues(List<Object> values){
        
        int size = values.size();
        String valuesString = "";
        
        for(Object value : values)
            valuesString = valuesString.concat((isFile(value) ? " ? " : (value instanceof String && !((String) value).matches(".*[+|-|\\*].*") ? ("\'" + value + "\'") : value)) + (size-- == 1 ? "" : ", "));
        
        return valuesString;
    }
    
    
    private static boolean isFile(Object path){
    
        return new File(path.toString()).isFile();
    }
    
    private static boolean checkEquality(int fieldsSize, int valuesSize){
        
        if(fieldsSize != valuesSize)
            throw new UnsupportedOperationException("Check number of passed fields or values");
        else
            return true;
    }
    
    
    private static Queue<String> getImagesPaths(List<Object> values){
        
        Queue<String> imagesPaths = new LinkedList<>();
        
        for(Object value : values)
            if(isFile(value))
                imagesPaths.add((String) value);
        
        return imagesPaths;
    }
}