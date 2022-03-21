package Views;

import Controllers.AdminDashboardController;
import Controllers.Interface.IAdministratorLoginController;
import Controllers.Interface.IController;
import Controllers.OperatorDashboardController;
import Models.EBS_DbContext;
import Models.Enum.Role;
import javax.swing.JOptionPane;

/**
 *
 * @author xorigin
 */
public class AdministratorLoginView extends javax.swing.JFrame implements IView {

    /**
     * Creates new form AdministratorLoginView
     * @param previousFrame
     */
    public AdministratorLoginView(javax.swing.JFrame previousFrame) {
        
        this.previousFrame = previousFrame;
        
        initComponents();
        
        this.setSpecialSettings();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BackButton = new javax.swing.JButton();
        FrameDescription = new javax.swing.JLabel();
        IDField = new javax.swing.JTextField();
        IDLabel = new javax.swing.JLabel();
        PasswordField = new javax.swing.JPasswordField();
        PasswordLabel = new javax.swing.JLabel();
        LoginButton = new javax.swing.JButton();
        ForgetPasswordButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrator Login");
        setResizable(false);

        BackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/BackIcon.png"))); // NOI18N
        BackButton.setToolTipText("Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        FrameDescription.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        FrameDescription.setText(" Administrator Login ");
        FrameDescription.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        IDField.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        IDField.setToolTipText("Enter your ID");
        IDField.setMinimumSize(new java.awt.Dimension(64, 30));
        IDField.setPreferredSize(new java.awt.Dimension(64, 30));
        IDField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                IDFieldFocusLost(evt);
            }
        });

        IDLabel.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        IDLabel.setText("ID");
        IDLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        PasswordField.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        PasswordField.setToolTipText("Enter your password");
        PasswordField.setMinimumSize(new java.awt.Dimension(64, 30));
        PasswordField.setPreferredSize(new java.awt.Dimension(64, 30));
        PasswordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                PasswordFieldFocusLost(evt);
            }
        });

        PasswordLabel.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        PasswordLabel.setText("Password");
        PasswordLabel.setToolTipText("");
        PasswordLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        LoginButton.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        LoginButton.setText("Login");
        LoginButton.setToolTipText("Press to login.");
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });

        ForgetPasswordButton.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        ForgetPasswordButton.setText("Forget password");
        ForgetPasswordButton.setToolTipText("Press to get new password.");
        ForgetPasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ForgetPasswordButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(FrameDescription))
                    .addComponent(IDLabel)
                    .addComponent(IDField, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PasswordLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(LoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ForgetPasswordButton))
                        .addComponent(PasswordField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(FrameDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BackButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(IDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(IDField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ForgetPasswordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed

        this.dispose();
        this.previousFrame.revalidate();
        this.previousFrame.setVisible(true);
    }//GEN-LAST:event_BackButtonActionPerformed

    private void IDFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_IDFieldFocusLost

        if(this.IDField.getText().isBlank()){

            this.globalValidationState = false;

            JOptionPane.showMessageDialog(this, "Please enter a valid ID.", "ID field", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_IDFieldFocusLost

    private void PasswordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordFieldFocusLost

        if(String.copyValueOf(this.PasswordField.getPassword()).isBlank()){

            this.globalValidationState = false;

            JOptionPane.showMessageDialog(this, "Please enter a valid password.", "Password field", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_PasswordFieldFocusLost

    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginButtonActionPerformed

        this.globalValidationState = true;

        this.IDFieldFocusLost(null);
        this.PasswordFieldFocusLost(null);

        String password = String.copyValueOf(this.PasswordField.getPassword());

        if(this.globalValidationState && this.controller.isValidAccount(this.IDField.getText(), password)){

            String role =  this.controller.getRole(this.IDField.getText());
            
            String message = "You will be redirected to the " + role + " Dashboard.";

            JOptionPane.showMessageDialog(this, message, "Successful Login", JOptionPane.INFORMATION_MESSAGE);

            this.redirectToDashboard(role);

        } else {

            String message = "Wrong ID or password.";

            JOptionPane.showMessageDialog(this, message, "Login failed", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_LoginButtonActionPerformed

    private void ForgetPasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ForgetPasswordButtonActionPerformed

        this.globalValidationState = true;

        this.IDFieldFocusLost(null);

        String ID = this.IDField.getText();

        if(this.globalValidationState && this.controller.isAdministratorExists(ID)){

            String message = "You will receive an email to your email address registered in the system with a new password.";

            this.controller.generateNewPassword(ID);

            JOptionPane.showMessageDialog(this, message, "Successful operation", JOptionPane.INFORMATION_MESSAGE);

        } else {

            String message = "ID entered incorrectly or this ID does not exist.";

            JOptionPane.showMessageDialog(this, message, "Problem with ID", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ForgetPasswordButtonActionPerformed

    
    public void redirectToDashboard(String role){
    
        if(role.equals(Role.Admin.name()))
            this.openAdminDashboard();
        else if(role.equals(Role.Operator.name()))
            this.openOperatorDashboard();
    }
    
    
    public void openAdminDashboard(){
    
        this.dispose();
        new AdminDashboardController(new AdminDashboardView(this.previousFrame), new EBS_DbContext(), this.IDField.getText());
    }
    
    
    public void openOperatorDashboard(){
    
        this.dispose();
        new OperatorDashboardController(new OperatorDashboardView(this.previousFrame), new EBS_DbContext(), this.IDField.getText());
    }
    
    @Override
    public final void setSpecialSettings() {
        
        this.setLocationRelativeTo(null);
        
        this.IDField.setDocument(new FixedSizeDocument(10));
        this.PasswordField.setDocument(new FixedSizeDocument(10));
    }
    
    @Override
    public void setController(IController controller){
    
        this.controller = (IAdministratorLoginController) controller;
    }
    
    @Override
    public javax.swing.JFrame getPreviousFrame(){
    
        return this.previousFrame;
    }
    
    
    private IAdministratorLoginController controller;
    private boolean globalValidationState = false;
    private final javax.swing.JFrame previousFrame;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JButton ForgetPasswordButton;
    private javax.swing.JLabel FrameDescription;
    private javax.swing.JTextField IDField;
    private javax.swing.JLabel IDLabel;
    private javax.swing.JButton LoginButton;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JLabel PasswordLabel;
    // End of variables declaration//GEN-END:variables
}
