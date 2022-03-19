package Views.Interface;

import Controllers.Interface.IController;

/**
 *
 * @author xorigin
 */
public interface IView {
    
    void setSpecialSettings();
    
    void setVisible(boolean bool);
    
    void setController(IController controller);
    
    javax.swing.JFrame getPreviousFrame();
}
