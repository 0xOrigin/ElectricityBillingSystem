package Controllers;


/**
 *
 * @author xorigin
 */
public class CustomerDataGenerator extends DataGenerator{
    
    String generateMeterCode(String governmentCode, String nationalID, int numberOfRegisteredCustomers){
         
        String meterCode = governmentCode + generateRandomNumbers(3) + nationalID.substring(7, 9)
                + String.format("%08d", numberOfRegisteredCustomers + 1);
        
        return meterCode;
        
    }
    
}
