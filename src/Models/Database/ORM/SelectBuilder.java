package Models.Database.ORM;

import Models.Database.ORM.Utilities.DML;
import java.util.List;

/**
 *
 * @author xorigin
 */
public class SelectBuilder extends DML {
    
    private final String table;
    private final List<Object> fields;
    private String where = "", orderBy = "", limit = "";
    
    public SelectBuilder(List<Object> fields, Enum table){
        
        this.table = table.name();
        this.fields = fields;
    }
    
    
    
    public SelectBuilder where(Enum field, String operator, Object value){
        
        this.where += super.Where(field, operator, value);
        return this;
    }
    
    public SelectBuilder operator(String operator){
    
        this.where += super.Operator(operator);
        return this;
    }
    
    public SelectBuilder controlPrecedence(String character){
        
        this.where += super.ControlPrecedence(character);
        return this;
    }
    
    public SelectBuilder orderBy(Enum orderBy, String typeOfOrder){
        
        this.orderBy = orderBy.name() + " " + typeOfOrder.toUpperCase();
        return this;
    }
    
    public SelectBuilder limit(String limit){
        
        this.limit = limit;
        return this;
    }
    
    public SelectQuery build(){
    
        return new SelectQuery(this);
    }
    
    
    public String getTableName(){
        
        return this.table;
    }
    
    public List<Object> getFields(){
        
        return this.fields;
    }
    
    
    public String getWhereCondition(){
        
        return this.where;
    }
    
    
    public String getOrderBy(){
        
        return this.orderBy;
    }
    
    
    public String getLimit(){
        
        return this.limit;
    }
    
}
