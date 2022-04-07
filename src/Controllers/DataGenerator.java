package Controllers;

import Models.Enum.Gender;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author xorigin
 */
abstract class DataGenerator {

    DataGenerator() {
        
    }
    
    String generateGender(String nationalID){
        
        int checkNumber = Integer.parseInt(nationalID.substring(12,13));
        
        return (checkNumber % 2 == 0 ? Gender.Female.name() : Gender.Male.name());
    }
    
    String generateDateOfBirth(String nationalID){
        
        String birthDay = getBirthDay(nationalID);
        String birthMonth = getBirthMonth(nationalID);
        String birthYear = getBirthYear(nationalID);
        String dateOfBirth = birthDay + "/" + birthMonth + "/" + birthYear;
        
        return dateOfBirth;
    }
    
    String generateDateOfContract(){
        
        Date currentDate = new Date(System.currentTimeMillis());
        SimpleDateFormat formatDate = new SimpleDateFormat("E, dd/MM/yyyy, hh:mm:ss a");
        return formatDate.format(currentDate);
    }
    
    String generatePassword(){
        
        final String AVAILABLE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~@#$%&()_=";
        final int PASSWORD_LENGTH = 10;
        
        String password = "";
        
        for (int counter = 0; counter < PASSWORD_LENGTH; counter++) {
            
            int randomNumber = generateRandomNumber(AVAILABLE_CHARS.length());
            char randomChar = generateRandomChar(AVAILABLE_CHARS , randomNumber);
            password += randomChar;
        }
        
        return password;   
    }
    
    
    private String getBirthDay(String nationalID){

        return nationalID.substring(5, 7); 
    }
            
    private String getBirthMonth(String nationalID){
        
        return nationalID.substring(3, 5);
    }
    
    private String getBirthYear(String nationalID){
        
        int birthYear = Integer.parseInt(nationalID.substring(0, 3));
        birthYear += 1700;
        
        return String.valueOf(birthYear);
    }
    
    
    protected String generateRandomNumbers(int count ){
        
        final int MAXNUMBER = 9;
        String randomNumbers = "";
        
        for (int counter = 0; counter < count; counter++)
            randomNumbers += generateRandomNumber(MAXNUMBER) + "";
        
        return randomNumbers;
    }
    
    protected int generateRandomNumber(int maxNumber){
        
        int randomNumber = new Random().nextInt(maxNumber);
        return randomNumber;
    }
    
    protected char generateRandomChar(String availableChars, int randomNumber){
        
        return availableChars.charAt(randomNumber);   
    }
    
}
