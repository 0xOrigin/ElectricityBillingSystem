package Controllers;

import Controllers.Interface.ICustomerDashboardController;
import Controllers.Bill.ReadingValidation;
import Controllers.Bill.BillCalculations;
import Models.Enum.Column;
import Models.Interface.IModel;
import Views.IView;
import Models.Interface.ICustomerDashboard;
import java.util.Arrays;

/**
 *
 * @author xorigin
 */
public class CustomerDashboardController implements ICustomerDashboardController {
    
    private final IView view;
    private final ICustomerDashboard model;
    private final String meterCode;
    
    public CustomerDashboardController(IView view, IModel model, String loggedinMeterCode){
    
        this.view = view;
        this.model = (ICustomerDashboard) model;
        this.meterCode = loggedinMeterCode;

        this.start();
    }
    
    @Override
    public final void start(){
    
        this.view.setController(this);
        this.view.setVisible(true);
    }
    
    @Override
    public String getActivationState(){
            
        return this.model.getActivationState(this.meterCode);
    }
    
    @Override
    public String getLastReading(){

        return String.valueOf(this.model.getLastBillInfo(Arrays.asList(Column.CurrentReading), meterCode).get(Column.CurrentReading));
    }
    
    @Override
    public String[] getLastReleaseDate(){
        
        // dd/dddd -> index 0 -> dd, index 1 - > dddd    
        return ((String) this.model.getLastBillInfo(Arrays.asList(Column.ReleaseDate), meterCode).get(Column.ReleaseDate)).split("/");
    }
    
    @Override
    public boolean isValidReading(int currentReading){

        return new ReadingValidation().isValid(currentReading, meterCode);
    }
    
    @Override
    public void releaseNewBill(int currentReading, String releaseDate){
    
        BillCalculations billCalculations = new BillCalculations(this.meterCode, currentReading);

        this.model.releaseNewBill(this.meterCode, releaseDate, currentReading,
                billCalculations.getConsumption(), billCalculations.getMoneyValue(), billCalculations.getTariff()
        );
    }
}
