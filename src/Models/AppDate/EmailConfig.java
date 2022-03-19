package Models.AppDate;

import Models.Enum.Name;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import org.json.simple.parser.ParseException;

/**
 *
 * @author xorigin
 */
public class EmailConfig implements IEmail{
    
    private final JSONReader jsonReader;
    private final Map<Object, Object> emailMap;
    
    public EmailConfig() throws FileNotFoundException, IOException, ParseException{
    
        this.jsonReader = new JSONReader(new ConfigPath().get());
        
        this.emailMap = (Map) this.jsonReader.getMap(Name.EmailConfigurations);
    }
    
    @Override
    public String get(Enum field){
    
        return (String) this.emailMap.get(field.name());
    }
    
}
