package Models.AppDate;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.util.Map;

/**
 *
 * @author xorigin
 */
class JSONReader {

    private final Object parsedFile;
    private final JSONObject json;
    
    
    JSONReader(String filePath) throws IOException, ParseException {
        
        this.parsedFile = new JSONParser().parse(new FileReader(filePath));
        this.json = (JSONObject) this.parsedFile;
    }
    
    
    Object getValue(Enum name){
        
        return json.get(name.name());
    }
    
    
    Map<Object, Object> getMap(Enum name){
        
        Map map = (Map) json.get(name.name());
        Map<Object, Object> data = new HashMap<>();
        
        Iterator<Map.Entry> iterator = map.entrySet().iterator();
        
        while(iterator.hasNext()){
        
            Map.Entry pair = iterator.next();

            data.put(pair.getKey(), pair.getValue());
        }
        
        return data;
    }
}
