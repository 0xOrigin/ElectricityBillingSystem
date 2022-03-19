package Models.Interface;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author xorigin
 */
public interface IOperatorDashboard extends IModel {
    
    List<Map<Enum, Object>> getAllBillsOfMeterCode(String meterCode) throws SQLException;
    
    List<Map<Enum, Object>> getAllBillsOfRegion(String governmentCode) throws SQLException;
    
    void payBill(String meterCode);
    
    void collectPayment(double moneyValue);
    
    boolean[] isMeterCodeExists_Active(String meterCode) throws SQLException;
    
    void toggleActivation(String meterCode) throws SQLException;
    
    void deleteCustomer(String meterCode);
    
    int getNumOfUnpaidBills(String meterCode) throws SQLException;
    
    Map<Enum, Object> getLastBillInfo(List<Enum> fields, String meterCode) throws SQLException;
    
}
