package Models;

import Models.Database.Administrator;
import Models.Database.Bill;
import Models.Database.CollectedMoney;
import Models.Database.Customer;

/**
 *
 * @author xorigin
 */
public interface DbContext {
    
    Administrator getAdministratorModel();
    
    Bill getBillModel();
    
    CollectedMoney getCollectedMoneyModel();
    
    Customer getCustomerModel();
    
}
