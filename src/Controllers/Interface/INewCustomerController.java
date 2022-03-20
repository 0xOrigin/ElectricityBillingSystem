package Controllers.Interface;


/**
 *
 * @author xorigin
 */
public interface INewCustomerController extends IController {
    
    boolean isValidName(String name);
    
    boolean isValidNationalID(String nationalID);
    
    boolean isValidAddress(String address);
    
    boolean isValidEmail(String email);
    
    boolean isValidPhoneNumber(String phoneNumber);
    
    void registerCustomer(String name, String nationalID, String address, String email, String governmentCode,
                          String phoneNumber, String typeOfUse, String propertyOwnershipContract);
    
}
