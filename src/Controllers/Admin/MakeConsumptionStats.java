package Controllers.Admin;

import Models.Database.Bill;
import Models.Database.Enum.ConsumptionStat;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author xorigin
 */
public class MakeConsumptionStats {
    
    private final Map<Enum, Object> statInfo;
    
    public MakeConsumptionStats(String governmentCode) throws SQLException{
    
        statInfo = new Bill().getConsumptionStatforRegion(governmentCode);    
    }
    
    public Map<Enum, Object> make(){
    
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
