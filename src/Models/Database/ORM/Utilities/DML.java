package Models.Database.ORM.Utilities;

/**
 *
 * @author xorigin
 */
public abstract class DML {
    
    protected DML(){
    
    }
    
    public String Where(Enum field, String operator, Object value){
    
        String statement = " ( " + field.name() + " " + operator + " ";
        
        if(value instanceof String && !((String) value).toLowerCase().contains("select"))
            statement += "\'" + value + "\'";
        else if(((String) value).toLowerCase().contains("select"))
            statement += "( " + value + " )";
        else
            statement += value;
        
        return statement + " )";
    }
    
    public String Aggregate(String functionName, String optionalAttribute, Enum field){
    
        return functionName.toUpperCase() + "(" + optionalAttribute.toUpperCase() + " " + field.name() + ")";
    } 
    
    public String ControlPrecedence(String character){
    
        return character;
    }
    
    public String Operator(String operator){
    
        return " " + operator.toUpperCase() + " ";
    }
    
}
