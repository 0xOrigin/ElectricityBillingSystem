package Controllers;

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
        
        if(isMale(nationalID)){
            return("Male");
        }else{
            return ("Female");
        }
    }
    private boolean isMale(String nationalID){
        
        int checkNumber = Integer.parseInt(nationalID.substring(12,13));
        if(checkNumber % 2 == 0){
            return false;
        }else{
            return true;
        }
    }
    
    String generateDateOfBirth(String nationalID){
        
        String birthDay = getBirthDay(nationalID);
        String birthMonth = getBirthMonth(nationalID);
        String birthYear = getBirthYear(nationalID);
        String dateOfBirth = birthDay + "/" + birthMonth + "/" + birthYear;
        return dateOfBirth;
    }
    
    private String getBirthDay(String nationalID){
        
        String birthDay = nationalID.substring(5, 7);
        return birthDay;
        
    }
            
    private String getBirthMonth(String nationalID){
        
        String birthMonth = nationalID.substring(3, 5);
        return birthMonth;
        
    }
    
    private String getBirthYear(String nationalID){
        
        int birthYear = Integer.parseInt(nationalID.substring(0, 3));
        birthYear += 1700;
        return birthYear + "";
        
    }
    
    String generateDateOfContract(){
        
        Date currentDate = new Date(System.currentTimeMillis());
        SimpleDateFormat formatDate = new SimpleDateFormat("E, dd/MM/yyyy, hh:mm:ss a");
        return formatDate.format(currentDate);
    }
    protected String generateRandomNumbers(int count ){
        
        final int MAXNUMBER = 9;
        String randomNumbers = "";
        
        for (int counter = 0; counter < count; counter++) {
            randomNumbers += generateRandomNumber(MAXNUMBER) + "";
        }
        return randomNumbers;
        
    }
    
    protected int generateRandomNumber(int maxNumber){
        
        int randomNumber = new Random().nextInt(maxNumber);
        return randomNumber;
    }
    
}
