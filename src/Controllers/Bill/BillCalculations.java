package Controllers.Bill;

/**
 *
 * @author xorigin
 */
public class BillCalculations {
    
    private double moneyValue;
    private int tariff;
    
    public BillCalculations(){
    
        this.moneyValue = 0.0;
        this.tariff = 0;
    }
    
    public double getMoneyValue(int consumption, String typeOfUse){
    
        this.moneyValue = 0.0;
        
        if (typeOfUse.equals("Home")){
            
            if (consumption >= 0 && consumption <= 50) {
                
                moneyValue = 0.38 * consumption;
                this.tariff = 1;
                
            } else if (consumption <= 100) {
                
                moneyValue = 19 + 0.48 * (consumption - 50);
                this.tariff = 2;
                
            } else if (consumption <= 200) {
                
                moneyValue = 0.65 * consumption;
                this.tariff = 3;
                
            } else if (consumption <= 350) {
                
                moneyValue = 130 + 0.96 * (consumption - 200);
                this.tariff = 4;
                
            } else if (consumption <= 650) {
                
                moneyValue = 274 + 1.18 * (consumption - 350);
                this.tariff = 5;
                
            } else if (consumption <= 1000) {
                
                moneyValue = 1.18 * consumption;
                this.tariff = 6;
                
            } else {
                
                moneyValue = 1.45 * consumption;
                this.tariff = 7;
                
            }
            
        } else { // For Commercial use.
            
            if (consumption <= 100) {
                
                moneyValue = consumption * 0.65;
                this.tariff = 1;
                
            } else if (consumption <= 200) {
                
                moneyValue = consumption * 1.20;
                this.tariff = 2;
                
            } else if (consumption <= 600) {
                
                moneyValue = consumption * 1.40;
                this.tariff = 3;
                
            } else if (consumption <= 1000) {
                
                moneyValue = 840 + (consumption - 600) * 1.55;
                this.tariff = 4;
                
            } else {
                
                moneyValue = consumption * 1.60;
                this.tariff = 5;   
            }
        }
        
        return moneyValue;    
    }
    
    public int getTariff(){
    
        return this.tariff;
    }
    
}
