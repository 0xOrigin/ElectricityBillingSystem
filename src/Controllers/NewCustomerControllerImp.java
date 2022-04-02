package Controllers;

import Controllers.Email.SendEmail;
import Models.AppDate.EmailConfigImp;
import Models.Enum.ActivationState;
import Controllers.Interface.NewCustomerController;
import Models.DbContext;
import Views.View;

/**
 *
 * @author xorigin
 */
public class NewCustomerControllerImp implements NewCustomerController {
    
    private final View view;
    private final DbContext dbContext;
    private final CustomerDataGenerator generator;
    private final Validator validator;
    
    public NewCustomerControllerImp(View view, DbContext dbContext){
    
        this.view = view;
        this.dbContext = dbContext;
        
        this.generator = new CustomerDataGenerator();
        this.validator = new Validator();
        
        this.startView();
    }
    
    @Override
    public final void startView(){
    
        this.view.setController(this);
        this.view.setVisible(true);
    }

    @Override
    public boolean isValidName(String name) {
        
        return this.validator.isValidName(name);
    }

    @Override
    public boolean isValidNationalID(String nationalID) {
        
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
        
    
    @Override
    public void registerCustomer(String name, String nationalID, String address, String email, String governmentCode,
                                 String phoneNumber, String typeOfUse, String propertyOwnershipContract){
    
        String gender, dateOfBirth, dateOfContract, meterCode, password;
        
        gender = this.generator.generateGender(nationalID);
        dateOfBirth = this.generator.generateDateOfBirth(nationalID);
        dateOfContract = this.generator.generateDateOfContract();
                    
        meterCode = this.generator.generateMeterCode(governmentCode, nationalID, this.dbContext.getCustomerModel().getNumOfCustomers());
        password = this.generator.generatePassword();

        this.dbContext.getCustomerModel().insert(name, nationalID, address, email, governmentCode, phoneNumber,
                          gender, dateOfBirth, typeOfUse, meterCode, password,
                          ActivationState.Active.name(), dateOfContract, propertyOwnershipContract);
        
        this.sendSuccessfulRegistrationEmail(email, meterCode, password);
    }
    
    private void sendSuccessfulRegistrationEmail(String email, String meterCode, String password){
    
        String messageSubject = "Successful Registration";
        String messageText = "Your meter code is " + meterCode + "\n" 
                           + "Your Password is " + password + " .";

        SendEmail.setDefaultConfig(new EmailConfigImp()).send(email, messageSubject, messageText);
    }
}
