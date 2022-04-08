package Controllers;

import Controllers.Email.SendEmail;
import Controllers.Interface.NewAdministratorController;
import Models.AppDate.EmailConfigImp;
import Models.DbContext;
import Views.View;

/**
 *
 * @author xorigin
 */
public class NewAdministratorControllerImp extends AdministratorControllerImp implements NewAdministratorController{
    
    private final DataValidator validator;
    private final AdministratorDataGenerator generator;
    
    public NewAdministratorControllerImp(View view, DbContext dbContext){
    
        super(view, dbContext, "");
        this.validator = new DataValidator();
        this.generator = new AdministratorDataGenerator();
        
        this.registerInView(this);
    }
    
    @Override
    public void registerAdministrator(String name, String nationalID, String address, String email, String Role,
                                 String phoneNumber){
    
        String gender, dateOfBirth, dateOfContract, ID, password;
        
        gender = this.generator.generateGender(nationalID);
        dateOfBirth = this.generator.generateDateOfBirth(nationalID);
        dateOfContract = this.generator.generateDateOfContract();
                    
        ID = this.generator.generateID( nationalID);
        password = this.generator.generatePassword();

        this.dbContext.getAdministratorModel().insert(name, nationalID, address, email, phoneNumber,
                          gender, dateOfBirth,ID, password,
                          Role, dateOfContract);
        
        this.sendSuccessfulRegistrationEmail(email, ID, password);
    }
    
    private void sendSuccessfulRegistrationEmail(String email, String ID, String password){
    
        String messageSubject = "Successful Registration";
        String messageText = "Your ID is " + ID + "\n" 
                           + "Your Password is " + password + " .";

        SendEmail.setDefaultConfig(new EmailConfigImp()).send(email, messageSubject, messageText);
    }
    
    @Override
    public boolean isValidName(String name) {
        
        return this.validator.isValidName(name);
    }

    @Override
    public boolean isValidNationalID(String nationalID) {
        
        if(this.dbContext.getAdministratorModel().isNationalIdExists(nationalID))
            return false;
        
        return this.validator.isValidNationalID(nationalID);
    }

    @Override
    public boolean isValidAddress(String address) {
        
        return this.validator.isValidAddress(address);
    }

    @Override
    public boolean isValidEmail(String email) {
        
        return this.validator.isValidEmail(email);
    }

    @Override
    public boolean isValidPhoneNumber(String phoneNumber) {
        
        return this.validator.isValidPhoneNumber(phoneNumber);
    }
    
}
