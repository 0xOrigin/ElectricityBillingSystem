package Models.AppDate;

import Models.Enum.AppData;
import java.util.Map;

/**
 *
 * @author xorigin
 */
public class ConnectionStringImp implements ConnectionString {
    
    private final JSONReader jsonReader;
    private final Map<Object, Object> connMap;
    
    public ConnectionStringImp(){
    
        this.jsonReader = new JSONReader(new ConfigPath().get());
        
        this.connMap = (Map) this.jsonReader.getMap(AppData.ConnectionStrings);
    }
    
    @Override
    public String getClassName(){
    
        return (String) this.connMap.get(AppData.ClassName.name());
    }
    
    @Override
    public String getConnectionPath(){
    
        return (String) this.connMap.get(AppData.ClassPath.name()) + ":" + (String) this.connMap.get(AppData.DatabasePath.name());
    }
}
