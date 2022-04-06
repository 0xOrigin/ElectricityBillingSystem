package Controllers;


/**
 *
 * @author xorigin
 */
public class CustomerDataGenerator extends DataGenerator{
    
    String generateMeterCode(String governmentCode, String nationalID, int numberOfRegisteredCustomers){
         
        String meterCode = governmentCode + generateRandomNumbers(3) + nationalID.substring(7, 9)
                + generateEightNumbers("" + (numberOfRegisteredCustomers + 1));
        return meterCode;
        
    }
    
    String generateEightNumbers(String number){
        
        String eightNumber ="";
        int numberOfZeros = 8 - number.length();
        
        while(eightNumber.length()<numberOfZeros){
            eightNumber += "0";
        }
        
        eightNumber += number;
        return eightNumber;
        
    }
    
    
    String generatePassword(){
        
        final String AVAILABLECHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@#$%^&*()_+=-";
        System.out.println(AVAILABLECHARS.length());
        final int PASSWORDLENGTH = 10;
        String password = "";
        
        for (int counter = 0; counter < PASSWORDLENGTH; counter++) {
            int randomNumber = generateRandomNumber(AVAILABLECHARS.length());
            char randomChar = generateRandomChar(AVAILABLECHARS , randomNumber);
            password += randomChar ;
        }
        return password;
                
    }
    
    private char generateRandomChar(String availableChars,int randomNumber){
        
        char randomChar = availableChars.charAt(randomNumber);
        return randomChar ;
        
    }
    
    
}
