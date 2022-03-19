package Controllers;

import Controllers.Email.SendEmail;
import Controllers.Interface.INewCustomerController;
import Models.AppDate.EmailConfig;
import Models.Enum.ActivationState;
import Models.Interface.IModel;
import Models.Interface.INewCustomer;
import Views.Interface.INewCustomerView;
import Views.Interface.IView;
import java.io.IOException;
import java.sql.SQLException;
import org.json.simple.parser.ParseException;

/**
 *
 * @author xorigin
 */
public class NewCustomerController implements INewCustomerController {
    
    private final INewCustomerView view;
    private final INewCustomer model;
    private final Generator generator;
    private final Validator validator;
    
    public NewCustomerController(IView view, IModel model){
    
        this.view = (INewCustomerView) view;
        this.model = (INewCustomer) model;
        
        this.generator = new Generator();
        this.validator = new Validator();
        
        this.start();
    }
    
    @Override
    public final void start(){
    
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
    public void registerCustomer(){
    
        String nationalID, gender, dateOfBirth, email, governmentCode, dateOfContract;
        String meterCode = "", password = "";
        
        nationalID = this.view.getNationalID();
        email = this.view.getEmail();
        governmentCode = this.view.getGovernmentCode();
        
        gender = this.generator.generateGender(nationalID);
        dateOfBirth = this.generator.generateDateOfBirth(nationalID);
        dateOfContract = this.generator.generateDateOfContract();
        
        try {
            
            meterCode = this.generator.generateMeterCode(governmentCode, nationalID, this.model.getNumOfCustomers());
            password = this.generator.generateCustomerPassword();
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        this.model.insert(this.view.getName(),
                          nationalID,
                          this.view.getAddress(),
                          email,
                          governmentCode,
                          this.view.getPhoneNumber(),
                          gender, 
                          dateOfBirth,
                          this.view.getTypeOfUse(),
                          meterCode,
                          password,
                          ActivationState.Active.name(),
                          dateOfContract,
                          this.view.getPropertyOwnershipContract());
        
        this.sendSuccessfulRegistrationEmail(email, meterCode, password);
    }
    
    private void sendSuccessfulRegistrationEmail(String email, String meterCode, String password){
    
        String messageSubject = "Successful Registration";
        String messageText = "Your meter code is " + meterCode + "\n" 
                           + "Your Password is " + password + " .";
        
        try {
            
           new SendEmail("Electricity Company", new EmailConfig()).send(email, messageSubject, messageText);
            
        } catch (ParseException | IOException ex) {
            System.out.println(ex);
        } 
    }
}
