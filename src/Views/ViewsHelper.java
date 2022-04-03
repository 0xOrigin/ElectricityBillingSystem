package Views;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author xorigin
 */
public class ViewsHelper {
    
    public static String[] getSortedEnumValues(Enum[] enumValues){
        
        Arrays.sort(enumValues, Comparator.comparing(Enum::name));
        
        return getEnumValues(enumValues);
    }
    
    public static String[] getEnumValues(Enum[] enumValues){
    
        String[] names = new String[enumValues.length];
        
        int iterator = 0;
        for(Enum name : enumValues)
            names[iterator++] = underscoreReplacer(name.name());
        
        return names;
    }
    
    private static String underscoreReplacer(String value){
    
        return value.replaceAll("_", " ");
    }
}
