package Controllers.Bill;

import Models.Enum.Column;
import java.util.Arrays;
import Models.DbContext;

/**
 *
 * @author xorigin
 */
public class ReadingValidator {
    
    private final DbContext dbContext;
    
    public ReadingValidator(DbContext dbContext){
    
        this.dbContext = dbContext;
    }
    
    public boolean validate(int currentReading, String meterCode){
    
        return currentReading >= (int) this.dbContext.getBillModel().getLastBillInfo(Arrays.asList(Column.CurrentReading), meterCode).get(Column.CurrentReading);
    }
    
}
