package Controllers;


/**
 *
 * @author xorigin
 */
public class AdministratorDataGenerator extends DataGenerator{
    
    String generateID(String nationalID){
        
        String ID = generateRandomNumbers(2) + nationalID.substring(7, 9) + generateRandomNumbers(4) + nationalID.substring(12, 14);
        
        return ID;    
    }
       
}
