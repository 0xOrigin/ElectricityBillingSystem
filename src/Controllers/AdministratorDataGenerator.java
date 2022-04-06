package Controllers;


/**
 *
 * @author xorigin
 */
public class AdministratorDataGenerator extends DataGenerator{
    
    String generatePassword(){
        
        final String AVAILABLECHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@#$%^&*()_+=-";
        System.out.println(AVAILABLECHARS.length());
        final int PASSWORDLENGTH = 10;
        String password = "";
        for (int counter = 0; counter < PASSWORDLENGTH; counter++) {
            int randomNumber = generateRandomNumber(AVAILABLECHARS.length());
            char randomChar = getRandomChar(AVAILABLECHARS , randomNumber);
            password += randomChar ;
        }
        return password;
                
    }
    private char getRandomChar(String availableChars,int randomNumber){
        
        char randomChar = availableChars.charAt(randomNumber);
        return randomChar ;
        
    }
    
    
    String generateID(String nationalID){
        
        String ID = generateRandomNumbers(2) + nationalID.substring(7, 9) + generateRandomNumbers(4) + nationalID.substring(12, 14);
        return ID;
        
    }
    
    
}
