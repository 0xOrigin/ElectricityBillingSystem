package Controllers.Bill;

import Models.Enum.ConsumptionStat;
import Models.IDbContext;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author xorigin
 */
public class MakeConsumptionStats {
    
    private final IDbContext dbContext;
    private final String governmentCode;
    
    public MakeConsumptionStats(IDbContext dbContext, String governmentCode){
    
        this.dbContext = dbContext;
        this.governmentCode = governmentCode;
    }
    
    public Map<Enum, Object> make(){
    
        int actualNumOfConsumers = this.dbContext.getBillModel().getActualNumberOfConsumersForRegion(this.governmentCode);
        
        int sumOfConsumptions = this.dbContext.getBillModel().getSumOfConsumptionsForRegion(this.governmentCode);
        
        double averageConsumption;
        
        if(actualNumOfConsumers != 0)
            averageConsumption = sumOfConsumptions / (double) actualNumOfConsumers;
        else
            averageConsumption = 0.0;
        
        Map<Enum, Object> statInfo = new HashMap<>();
        
        statInfo.put(ConsumptionStat.ActualNumberOfConsumers, actualNumOfConsumers);
        statInfo.put(ConsumptionStat.SumOfConsumptions, sumOfConsumptions);
        statInfo.put(ConsumptionStat.AverageConsumption, averageConsumption);
    
        return statInfo;
    }
    
}
