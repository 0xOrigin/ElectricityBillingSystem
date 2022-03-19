package Controllers.Bill;

import Models.Database.Bill;
import Models.Enum.Column;
import Models.Database.ORM.SQLiteAdapter;
import Models.Enum.Table;
import java.sql.SQLException;
import java.util.Arrays;

/**
 *
 * @author xorigin
 */
public class ReadingValidation {
    
    private final Bill billDB;
    
    public ReadingValidation(){
    
        this.billDB = new Bill(new SQLiteAdapter(Table.Bill));
    }
    
    public boolean isValid(int currentReading, String meterCode) throws SQLException{
    
        return currentReading >= (int) billDB.getLastBillInfo(Arrays.asList(Column.CurrentReading), meterCode).get(Column.CurrentReading);
    }
    
}
