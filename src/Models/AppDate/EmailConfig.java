package Models.AppDate;

import Models.Enum.AppData;
import java.util.Map;

/**
 *
 * @author xorigin
 */
public class EmailConfig implements IEmail{
    
    private final JSONReader jsonReader;
    private final Map<Object, Object> emailMap;
    
    public EmailConfig(){
    
        this.jsonReader = new JSONReader(new ConfigPath().get());
        
        this.emailMap = (Map) this.jsonReader.getMap(AppData.EmailConfigurations);
    }
    
    @Override
    public String get(Enum field){
    
        return (String) this.emailMap.get(field.name());
    }
    
}
