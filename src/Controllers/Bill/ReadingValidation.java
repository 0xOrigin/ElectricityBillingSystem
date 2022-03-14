package Controllers.Bill;

import Models.Database.Bill;
import Models.Enum.Column;
import Models.Interface.IAdapter;
import java.sql.SQLException;

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
    
        return currentReading >= (int) billDB.getBillConstructionInfo(Column.CurrentReading, meterCode);
    }
    
}
