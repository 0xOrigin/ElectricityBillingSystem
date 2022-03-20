package Models.Interface;

import java.util.List;
import java.util.Map;

/**
 *
 * @author xorigin
 */
public interface IOperatorDashboard extends IModel {
    
    List<Map<Enum, Object>> getAllBillsOfMeterCode(String meterCode);
    
    List<Map<Enum, Object>> getAllBillsOfRegion(String governmentCode);
    
    void payBill(String meterCode);
    
    void collectPayment(double moneyValue);
    
    boolean[] isMeterCodeExists_Active(String meterCode);
    
    void toggleActivation(String meterCode);
    
    void deleteCustomer(String meterCode);
    
    int getNumOfUnpaidBills(String meterCode);
    
    Map<Enum, Object> getLastBillInfo(List<Enum> fields, String meterCode);
    
}
