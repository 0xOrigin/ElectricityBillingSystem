package Controllers.Bill;

import Models.Enum.Column;
import Models.Enum.TypeOfUse;
import Models.IDbContext;
import java.util.Arrays;

/**
 *
 * @author xorigin
 */
public class BillCalculations {
    
    private final IDbContext dbContext;
    private double moneyValue;
    private int tariff;
    private String typeOfUse;
    private final String meterCode;
    private int consumption;
    private final int currentReading;
    
    public BillCalculations(IDbContext dbContext, String meterCode, int currentReading){
    
        this.dbContext = dbContext;
        
        this.meterCode = meterCode;
        this.moneyValue = 0.0;
        this.tariff = 0;
        this.consumption = 0;
        this.currentReading = currentReading;
        
        this.setTypeOfUse();
        this.calculateConsumption();
        this.calculateMoneyValue();
    }
    
    private void setTypeOfUse(){

        this.typeOfUse = (String) this.dbContext.getCustomerModel()
                .getInfo(Arrays.asList(Column.TypeOfUse), this.meterCode)
                .get(Column.TypeOfUse);
    }
    
    private void calculateConsumption(){

        this.consumption = this.currentReading - (int) this.dbContext.getBillModel()
                                                        .getLastBillInfo(Arrays.asList(Column.CurrentReading), this.meterCode)
                                                        .get(Column.CurrentReading);
            
    }
    
    private void calculateMoneyValue(){
    
        this.moneyValue = 0.0;
        
        if (this.typeOfUse.equals(TypeOfUse.Home.name())){
            
            if (this.consumption >= 0 && this.consumption <= 50) {
                
                this.moneyValue = 0.38 * this.consumption;
                this.tariff = 1;
                
            } else if (this.consumption <= 100) {
                
                this.moneyValue = 19 + 0.48 * (this.consumption - 50);
                this.tariff = 2;
                
            } else if (this.consumption <= 200) {
                
                this.moneyValue = 0.65 * this.consumption;
                this.tariff = 3;
                
            } else if (this.consumption <= 350) {
                
                this.moneyValue = 130 + 0.96 * (this.consumption - 200);
                this.tariff = 4;
                
            } else if (this.consumption <= 650) {
                
                this.moneyValue = 274 + 1.18 * (this.consumption - 350);
                this.tariff = 5;
                
            } else if (this.consumption <= 1000) {
                
                this.moneyValue = 1.18 * this.consumption;
                this.tariff = 6;
                
            } else {
                
                this.moneyValue = 1.45 * this.consumption;
                this.tariff = 7;
                
            }
            
        } else { // For Commercial use.
            
            if (this.consumption <= 100) {
                
                this.moneyValue = this.consumption * 0.65;
                this.tariff = 1;
                
            } else if (this.consumption <= 200) {
                
                this.moneyValue = this.consumption * 1.20;
                this.tariff = 2;
                
            } else if (this.consumption <= 600) {
                
                this.moneyValue = this.consumption * 1.40;
                this.tariff = 3;
                
            } else if (this.consumption <= 1000) {
                
                this.moneyValue = 840 + (this.consumption - 600) * 1.55;
                this.tariff = 4;
                
            } else {
                
                this.moneyValue = this.consumption * 1.60;
                this.tariff = 5;   
            }
        }     
    }
    
    public int getConsumption(){
    
        return this.consumption;
    }
    
    public double getMoneyValue(){
    
        return this.moneyValue;
    }
    
    public int getTariff(){
    
        return this.tariff;
    }
    
}
