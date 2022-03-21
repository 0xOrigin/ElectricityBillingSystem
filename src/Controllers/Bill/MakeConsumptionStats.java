package Controllers.Bill;

import Models.Enum.ConsumptionStat;
import Models.IDbContext;
import java.util.Map;

/**
 *
 * @author xorigin
 */
public class MakeConsumptionStats {
    
    private final Map<Enum, Object> statInfo;
    
    public MakeConsumptionStats(IDbContext dbContext, String governmentCode){
    
        this.statInfo = dbContext.getBillModel().getConsumptionStatforRegion(governmentCode);    
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
