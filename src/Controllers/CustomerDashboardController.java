package Controllers;

import Controllers.Interface.ICustomerDashboardController;
import Controllers.Bill.ReadingValidation;
import Controllers.Bill.BillCalculations;
import Models.Enum.Column;
import Models.Interface.IModel;
import Views.Interface.IView;
import Views.Interface.ICustomerDashboardView;
import Models.Interface.ICustomerDashboard;
import java.sql.SQLException;
import java.util.Arrays;

/**
 *
 * @author xorigin
 */
public class CustomerDashboardController implements ICustomerDashboardController {
    
    private final ICustomerDashboardView view;
    private final ICustomerDashboard model;
    private final String meterCode;
    
    public CustomerDashboardController(IView view, IModel model, String loggedinMeterCode){
    
        this.view = (ICustomerDashboardView) view;
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
    
        try {
            
            return this.model.getActivationState(this.meterCode);
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return "";
    }
    
    @Override
    public String getLastReading(){
    
        try {
            
            return String.valueOf(this.model.getLastBillInfo(Arrays.asList(Column.CurrentReading), meterCode).get(Column.CurrentReading));
        
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return "";
    }
    
    @Override
    public String[] getLastReleaseDate(){
        
        // dd/dddd -> index 0 -> dd, index 1 - > dddd
        try {
            
            return ((String) this.model.getLastBillInfo(Arrays.asList(Column.ReleaseDate), meterCode).get(Column.ReleaseDate)).split("/");
        
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return new String[0];
    }
    
    @Override
    public boolean isValidReading(int currentReading){
    
        try {
            
            return new ReadingValidation().isValid(currentReading, meterCode);
        
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return false;
    }
    
    @Override
    public void releaseNewBill(int currentReading, String releaseDate){
    
        BillCalculations billCalculations = new BillCalculations(this.meterCode, currentReading);
        
        try {
            
            this.model.releaseNewBill(this.meterCode, releaseDate, currentReading,
                    billCalculations.getConsumption(), billCalculations.getMoneyValue(), billCalculations.getTariff()
            );
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
