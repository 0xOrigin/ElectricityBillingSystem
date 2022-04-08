package Controllers;

import Controllers.Bill.ReadingValidator;
import Controllers.Bill.BillCalculations;
import Controllers.Email.SendEmail;
import Models.Enum.Column;
import java.util.Arrays;
import Models.AppDate.EmailConfigImp;
import Models.DbContext;
import Views.View;
import java.util.List;
import java.util.Map;
import Controllers.Interface.CustomerController;

/**
 *
 * @author xorigin
 */
public class CustomerControllerImp implements CustomerController {

    private final View view;
    private final DbContext dbContext;
    private final String meterCode;
    private final DataValidator validator;

    public CustomerControllerImp(View view, DbContext dbContext, String loggedinMeterCode) {

        this.view = view;
        this.dbContext = dbContext;
        this.meterCode = loggedinMeterCode;
        this.validator = new DataValidator();

        this.registerInView();
    }

    @Override
    public final void registerInView() {

        if (this.view != null) {
            this.view.setController(this);
        }
    }

    @Override
    public String getActivationState() {

        return String.valueOf(this.dbContext.getCustomerModel().getInfo(Arrays.asList(Column.Activation), this.meterCode).get(Column.Activation));
    }

    @Override
    public String getLastReading() {

        return String.valueOf(this.dbContext.getBillModel().getLastBillInfo(Arrays.asList(Column.CurrentReading), this.meterCode).get(Column.CurrentReading));
    }

    @Override
    public String[] getLastReleaseDate() {

        // dd/dddd [index 0 -> dd, index 1 - > dddd].
        return String.valueOf(this.dbContext.getBillModel().getLastBillInfo(Arrays.asList(Column.ReleaseDate), this.meterCode).get(Column.ReleaseDate)).split("/");
    }

    @Override
    public boolean isValidReading(int currentReading) {

        return new ReadingValidator(this.dbContext).validate(currentReading, this.meterCode);
    }

    @Override
    public void releaseNewBill(int currentReading, String releaseDate) {

        BillCalculations billCalculations = new BillCalculations(this.dbContext, this.meterCode, currentReading);

        this.dbContext.getBillModel().releaseNewBill(this.meterCode, releaseDate, currentReading,
                billCalculations.getConsumption(), billCalculations.getMoneyValue(), billCalculations.getTariff()
        );

        if (this.unpaidEmailDeterminer(this.meterCode)) {
            this.sendUnpaidBillsEmail(this.getCustomerEmail(this.meterCode), this.meterCode);
        }
    }

    private String getCustomerEmail(String meterCode) {

        return String.valueOf(this.dbContext.getCustomerModel().getInfo(Arrays.asList(Column.Email), meterCode).get(Column.Email));
    }

    private boolean unpaidEmailDeterminer(String meterCode) {

        return this.dbContext.getBillModel().getNumOfUnpaidBills(meterCode) >= 3;
    }

    private void sendUnpaidBillsEmail(String email, String meterCode) {

        String messageSubject = "Alert: bills are accumulated!";
        String messageText = "You haven't paid your bills for three months or more at the meter number: " + meterCode + " .";

        SendEmail.setDefaultConfig(new EmailConfigImp()).send(email, messageSubject, messageText);
    }

    @Override
    public List<Map<Enum, Object>> getAllBillsOfMeterCode() {

        return this.dbContext.getBillModel().getAllBillsOfMeterCode(meterCode);
    }

    @Override
    public void complainAboutBill(String complaint, int billNumber) {

        this.dbContext.getBillModel().complainAboutBill(complaint, billNumber);
    }

    @Override
    public boolean isValidComplaint(String complaint) {

        return this.validator.isValidComplaint(complaint);
    }

    @Override
    public String getMeterCode() {

        return this.meterCode;
    }

    private boolean areThereUnPaidBills(String meterCode) {

        return (this.dbContext.getBillModel().getNumOfUnpaidBills(meterCode) > 0);
    }

    @Override
    public boolean deleteCustomer(String meterCode) {

        if (!this.areThereUnPaidBills(meterCode)) {

            this.dbContext.getCustomerModel().delete(meterCode);
            return true;
        }

        return false;
    }

    @Override
    public void toggleActivation(String meterCode) {

        this.dbContext.getCustomerModel().toggleActivation(meterCode);
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
    public void updateCustomer(List<Enum> fields, List<Object> values, String meterCode) {

        this.dbContext.getCustomerModel().update(fields, values, meterCode);
    }

    @Override
    public Map<Enum, Object> getInfo(List<Enum> fields, String MeterCode) {

        return this.dbContext.getCustomerModel().getInfo(fields, MeterCode);
    }

    @Override
    public void payBill(String meterCode) {
        
        this.dbContext.getBillModel().payBill(meterCode);
    }
    
    @Override
    public void collectPayment(String moneyValue){
    
        this.dbContext.getCollectedMoneyModel().collectPayment(Double.valueOf(moneyValue));
    }
    
}
