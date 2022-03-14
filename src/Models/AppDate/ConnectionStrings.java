package Models.AppDate;

import Models.Enum.Name;
import Models.Interface.IConnection;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import org.json.simple.parser.ParseException;

/**
 *
 * @author xorigin
 */
public class ConnectionStrings implements IConnection {
    
    private final JSONReader jsonReader;
    private final Map<Object, Object> connMap;
    
    public ConnectionStrings() throws FileNotFoundException, IOException, ParseException{
    
        this.jsonReader = new JSONReader(new ConfigPath().get());
        
        this.connMap = (Map) this.jsonReader.getMap(Name.ConnectionStrings);
    }
    
    @Override
    public String getClassName(){
    
        return (String) this.connMap.get(Name.ClassName.name());
    }
    
    @Override
    public String getConnectionPath(){
    
        return (String) this.connMap.get(Name.ClassPath.name()) + ":" + (String) this.connMap.get(Name.DatabasePath.name());
    }
}
