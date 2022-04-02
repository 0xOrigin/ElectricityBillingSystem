package Views;

import Controllers.Interface.Controller;

/**
 *
 * @author xorigin
 */
public interface View {
    
    void setSpecialSettings();
    
    void setVisible(boolean bool);
    
    void setController(Controller controller);
    
    javax.swing.JFrame getPreviousFrame();
}
