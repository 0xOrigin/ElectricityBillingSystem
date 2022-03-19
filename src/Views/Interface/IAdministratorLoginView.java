package Views.Interface;

/**
 *
 * @author xorigin
 */
public interface IAdministratorLoginView extends IView{
    
    void redirectToDashboard(String role);
    
    void openAdminDashboard();
    
    void openOperatorDashboard();
    
}
