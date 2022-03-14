package Controllers.Bill;

import Models.Database.Bill;
import Models.Enum.Column;
import Models.Interface.IAdapter;
import java.sql.SQLException;
import java.util.Arrays;

/**
 *
 * @author xorigin
 */
class ReadingValidation {
    
    private final Bill billDB;
    
    ReadingValidation(IAdapter adapter){
    
        this.billDB = new Bill(adapter);
    }
    
    boolean isValid(int currentReading, String meterCode) throws SQLException{
    
        return currentReading >= (int) billDB.getBillInfo(Arrays.asList(Column.CurrentReading), meterCode).get(Column.CurrentReading);
    }
    
}
