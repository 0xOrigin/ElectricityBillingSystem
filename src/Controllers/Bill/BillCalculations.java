package Controllers.Bill;

import Models.Database.Bill;
import Models.Database.Customer;
import Models.Database.ORM.SQLiteAdapter;
import Models.Enum.Column;
import Models.Enum.Table;
import Models.Enum.TypeOfUse;
import java.util.Arrays;

/**
 *
 * @author xorigin
 */
public class BillCalculations {
    
    private double moneyValue;
    private int tariff;
    private String typeOfUse;
    private final String meterCode;
    private int consumption;
    private final int currentReading;
    
    public BillCalculations(String meterCode, int currentReading){
    
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

        this.typeOfUse = (String) new Customer(new SQLiteAdapter(Table.Customer))
                .getInfo(Arrays.asList(Column.TypeOfUse), this.meterCode)
                .get(Column.TypeOfUse);
    }
    
    private void calculateConsumption(){

        this.consumption = this.currentReading - (int) new Bill(new SQLiteAdapter(Table.Bill))
                                                        .getLastBillInfo(Arrays.asList(Column.CurrentReading), this.meterCode)
                                                        .get(Column.CurrentReading);
            
    }
    
    private void calculateMoneyValue(){
    
        this.moneyValue = 0.0;
        
        if (typeOfUse.equals(TypeOfUse.Home.name())){
            
            if (consumption >= 0 && consumption <= 50) {
                
                this.moneyValue = 0.38 * consumption;
                this.tariff = 1;
                
            } else if (consumption <= 100) {
                
                this.moneyValue = 19 + 0.48 * (consumption - 50);
                this.tariff = 2;
                
            } else if (consumption <= 200) {
                
                this.moneyValue = 0.65 * consumption;
                this.tariff = 3;
                
            } else if (consumption <= 350) {
                
                this.moneyValue = 130 + 0.96 * (consumption - 200);
                this.tariff = 4;
                
            } else if (consumption <= 650) {
                
                this.moneyValue = 274 + 1.18 * (consumption - 350);
                this.tariff = 5;
                
            } else if (consumption <= 1000) {
                
                this.moneyValue = 1.18 * consumption;
                this.tariff = 6;
                
            } else {
                
                this.moneyValue = 1.45 * consumption;
                this.tariff = 7;
                
            }
            
        } else { // For Commercial use.
            
            if (consumption <= 100) {
                
                this.moneyValue = consumption * 0.65;
                this.tariff = 1;
                
            } else if (consumption <= 200) {
                
                this.moneyValue = consumption * 1.20;
                this.tariff = 2;
                
            } else if (consumption <= 600) {
                
                this.moneyValue = consumption * 1.40;
                this.tariff = 3;
                
            } else if (consumption <= 1000) {
                
                this.moneyValue = 840 + (consumption - 600) * 1.55;
                this.tariff = 4;
                
            } else {
                
                this.moneyValue = consumption * 1.60;
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
