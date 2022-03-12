import Views.MainView;
import java.sql.SQLException;

/**
 *
 * @author xorigin
 */
public class ElectricityBillingSystem {

    
    public static void main(String[] args) throws SQLException {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainView().setVisible(true);
            }
        });

    }
    
}
