package Models.Interface;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author xorigin
 */
public interface ICustomerDashboard extends IModel {
    
    void releaseNewBill(String meterCode, String releaseDate, int currentReading, int consumption, double moneyValue, int tariff) throws SQLException;
    
    void complainAboutBill(String complain, int billNumber);
    
    List<Map<Enum, Object>> getAllBillsOfMeterCode(String meterCode) throws SQLException;
    
    Map<Enum, Object> getLastBillInfo(List<Enum> fields, String meterCode) throws SQLException;
    
    int getNumOfUnpaidBills(String meterCode) throws SQLException;
    
    String getActivationState(String meterCode) throws SQLException;
    
    String getTypeOfUse(String meterCode) throws SQLException;
}
