package Controllers;

/**
 *
 * @author xorigin
 */
class DataValidator {

    DataValidator() {
        
    }
    
    public boolean isValidName(String name) {
        return name.matches( "[a-zA-Z]+[\\-'\\s]?[a-zA-Z ]+(_|-)*" );
    }

    public boolean isValidNationalID(String nationalID) {
        return nationalID.matches("(2|3)[0-9][0-9][0-1][0-9][0-3][0-9]" +
                "(01|02|03|04|11|12|13|14|15|16|17|18|19|21|22|23|24|25|26|27|28|29|31|32|33|34|35|88)[0-9]{5}");
    }

    public boolean isValidAddress(String address) {
        return address.matches("[a-zA-Z0-9,_'-.]+");
    }

    public boolean isValidEmail(String email) {
        return email.matches("(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*" +
                "@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)" +
                "*(\\.[A-Za-z]{2,})");
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("01(0|1|2|5)[0-9]{8}");
    }
    
    public boolean isValidComplaint(String complaint){
        return complaint.matches("[a-zA-z0-9]+");
    }
    
    public static boolean isValidPassword(String password){
        return password.matches("[0-9a-zA-Z!@#&()–{}:;',?/*~$^+=<>]{4,10}");
    }
    
}