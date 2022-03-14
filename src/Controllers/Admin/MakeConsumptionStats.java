package Controllers.Admin;

import Models.Database.Bill;
import Models.Enum.ConsumptionStat;
import Models.Interface.IAdapter;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author xorigin
 */
class MakeConsumptionStats {
    
    private final Map<Enum, Object> statInfo;
    
    MakeConsumptionStats(IAdapter adapter, String governmentCode) throws SQLException{
    
        this.statInfo = new Bill(adapter).getConsumptionStatforRegion(governmentCode);    
    }
    
    Map<Enum, Object> make(){
    
        int actualNumOfConsumers = (int) statInfo.get(ConsumptionStat.ActualNumberOfConsumers);
        double averageConsumption;
        
        if(actualNumOfConsumers != 0)
            averageConsumption = (int) statInfo.get(ConsumptionStat.SumOfConsumptions) / (double) actualNumOfConsumers;
        else
            averageConsumption = 0.0;
        
        
        statInfo.put(ConsumptionStat.AverageConsumption, averageConsumption);
    
        return statInfo;
    }
    
}
