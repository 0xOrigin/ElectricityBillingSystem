package Controllers;

import Controllers.Interface.ICustomerDashboardController;
import Controllers.Bill.ReadingValidator;
import Controllers.Bill.BillCalculations;
import Models.Enum.Column;
import Models.IDbContext;
import Views.IView;
import java.util.Arrays;

/**
 *
 * @author xorigin
 */
public class CustomerDashboardController implements ICustomerDashboardController {
    
    private final IView view;
    private final IDbContext dbContext;
    private final String meterCode;
    
    public CustomerDashboardController(IView view, IDbContext dbContext, String loggedinMeterCode){
    
        this.view = view;
        this.dbContext = dbContext;
        this.meterCode = loggedinMeterCode;

        this.startView();
    }
    
    @Override
    public final void startView(){
    
        this.view.setController(this);
        this.view.setVisible(true);
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
        return ((String) this.dbContext.getBillModel().getLastBillInfo(Arrays.asList(Column.ReleaseDate), meterCode).get(Column.ReleaseDate)).split("/");
    }
    
    @Override
    public boolean isValidReading(int currentReading){

        return new ReadingValidator(this.dbContext).validate(currentReading, meterCode);
    }
    
    @Override
    public void releaseNewBill(int currentReading, String releaseDate){
    
        BillCalculations billCalculations = new BillCalculations(this.dbContext ,this.meterCode, currentReading);

        this.dbContext.getBillModel().releaseNewBill(this.meterCode, releaseDate, currentReading,
                billCalculations.getConsumption(), billCalculations.getMoneyValue(), billCalculations.getTariff()
        );
    }
}
