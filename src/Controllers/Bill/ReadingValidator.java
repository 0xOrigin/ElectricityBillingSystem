package Controllers.Bill;

import Models.Enum.Column;
import Models.IDbContext;
import java.util.Arrays;

/**
 *
 * @author xorigin
 */
public class ReadingValidator {
    
    private final IDbContext dbContext;
    
    public ReadingValidator(IDbContext dbContext){
    
        this.dbContext = dbContext;
    }
    
    public boolean validate(int currentReading, String meterCode){
    
        return currentReading >= (int) this.dbContext.getBillModel().getLastBillInfo(Arrays.asList(Column.CurrentReading), meterCode).get(Column.CurrentReading);
    }
    
}
