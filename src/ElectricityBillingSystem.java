import Views.MainView;

/**
 *
 * @author xorigin
 */
public class ElectricityBillingSystem {

    
    public static void main(String[] args) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainView().setVisible(true);
            }
        });
        
    }
    
}
