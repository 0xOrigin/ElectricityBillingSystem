package Controllers;

import Controllers.Bill.ReadingValidator;
import Controllers.Bill.BillCalculations;
import Models.Enum.Column;
import java.util.Arrays;
import Controllers.Interface.CustomerDashboardController;
import Models.DbContext;
import Views.View;
import java.util.List;
import java.util.Map;

/**
 *
 * @author xorigin
 */
public class CustomerDashboardControllerImp implements CustomerDashboardController {
    
    private final View view;
    private final DbContext dbContext;
    private final String meterCode;
    private String targetMeterCode = "";
    
    public CustomerDashboardControllerImp(View view, DbContext dbContext, String loggedinMeterCode){
    
        this.view = view;
        this.dbContext = dbContext;
        this.meterCode = loggedinMeterCode;

        this.registerInView();
    }
    
    public CustomerDashboardControllerImp(View view, DbContext dbContext, String loggedinMeterCode, String targetMeterCode){
    
        this(view, dbContext, loggedinMeterCode);
        this.targetMeterCode = targetMeterCode;
    }
    
    @Override
    public final void registerInView(){
    
        if(this.view != null)
            this.view.setController(this);
    }
    
    @Override
    public String getActivationState(){
            
        return (String) this.dbContext.getCustomerModel().getInfo(Arrays.asList(Column.Activation), this.meterCode).get(Column.Activation);
    }
    
    @Override
    public String getLastReading(){

        return String.valueOf(this.dbContext.getBillModel().getLastBillInfo(Arrays.asList(Column.CurrentReading), this.meterCode).get(Column.CurrentReading));
    }
    
    @Override
    public String[] getLastReleaseDate(){
        
        // dd/dddd [index 0 -> dd, index 1 - > dddd].
        return ((String) this.dbContext.getBillModel().getLastBillInfo(Arrays.asList(Column.ReleaseDate), this.meterCode).get(Column.ReleaseDate)).split("/");
    }
    
    @Override
    public boolean isValidReading(int currentReading){

        return new ReadingValidator(this.dbContext).validate(currentReading, this.meterCode);
    }
    
    @Override
    public void releaseNewBill(int currentReading, String releaseDate){
    
        BillCalculations billCalculations = new BillCalculations(this.dbContext ,this.meterCode, currentReading);

        this.dbContext.getBillModel().releaseNewBill(this.meterCode, releaseDate, currentReading,
                billCalculations.getConsumption(), billCalculations.getMoneyValue(), billCalculations.getTariff()
        );
    }

    
    @Override
    public List<Map<Enum, Object>> getAllBillsOfMeterCode(){
    
        return this.dbContext.getBillModel().getAllBillsOfMeterCode(meterCode);
    }
    
    @Override
    public void complainAboutBill(String complaint, int billNumber){
    
        this.dbContext.getBillModel().complainAboutBill(complaint, billNumber);
    }
    
    @Override
    public boolean isValidComplaint(String complaint){
    
        return new DataValidator().isValidComplaint(complaint);
    }
    
    @Override
    public String getLoggedinMeterCode(){
    
        return this.meterCode;
    }
    
    @Override
    public String getTargetMeterCode(){
    
        return this.targetMeterCode;
    }
    
    private boolean areThereUnPaidBills(String meterCode){

        return (this.dbContext.getBillModel().getNumOfUnpaidBills(meterCode) > 0) ; 
    }
    
    @Override
    public boolean deleteCustomer(String meterCode){
        
        if(!areThereUnPaidBills(meterCode)){
            this.dbContext.getCustomerModel().delete(meterCode);
            return true ; 
        }
        else
            return false ; 
        
    }
    
}
