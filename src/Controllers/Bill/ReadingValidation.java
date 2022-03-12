package Controllers.Bill;

import Models.Database.Bill;
import Models.Database.Enum.Column;
import java.sql.SQLException;

/**
 *
 * @author xorigin
 */
public class ReadingValidation {
    
    private final Bill billDB;
    
    public ReadingValidation(){
    
        billDB = new Bill();
    }
    
    public boolean isValid(int currentReading, String meterCode) throws SQLException{
    
        return currentReading >= (int) billDB.getBillConstructionInfo(Column.CurrentReading, meterCode);
    }
    
}
