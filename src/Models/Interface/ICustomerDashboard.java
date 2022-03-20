package Models.Interface;

import java.util.List;
import java.util.Map;

/**
 *
 * @author xorigin
 */
public interface ICustomerDashboard extends IModel {
    
    void releaseNewBill(String meterCode, String releaseDate, int currentReading, int consumption, double moneyValue, int tariff);
    
    void complainAboutBill(String complain, int billNumber);
    
    List<Map<Enum, Object>> getAllBillsOfMeterCode(String meterCode);
    
    Map<Enum, Object> getLastBillInfo(List<Enum> fields, String meterCode);
    
    int getNumOfUnpaidBills(String meterCode);
    
    String getActivationState(String meterCode);
    
    String getTypeOfUse(String meterCode);
}
