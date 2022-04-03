package Controllers;

/**
 *
 * @author xorigin
 */
abstract class DataGenerator {

    DataGenerator() {
        
    }
    
    String generateDateOfBirth(String nationalID){
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
    String generateGender(String nationalID){
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
    String generateDateOfContract(){
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
