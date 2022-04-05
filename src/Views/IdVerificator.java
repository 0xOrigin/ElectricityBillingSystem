package Views;

import Controllers.AdministratorControllerImp;
import Controllers.Interface.AdministratorLoginController;
import Controllers.Interface.Controller;
import Models.EBS_DbContext;
import javax.swing.JOptionPane;

/**
 *
 * @author xorigin
 */
public class IdVerificator extends javax.swing.JFrame implements View{

    /**
     * Creates new form IdVerificator
     * @param previousFrame
     * @param controller
     * @param nextFrame
     */
    public IdVerificator(javax.swing.JFrame previousFrame, Controller controller, View nextFrame, String loggedinID) {
        
        
        this.setPreviousFrame(previousFrame);
        
        this.nextFrame = nextFrame;
        this.loggedinID = loggedinID;
        
        this.setController(controller);
        
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
        IDField = new javax.swing.JTextField();
        IDLabel = new javax.swing.JLabel();
        SubmitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Enter ID");
        setResizable(false);

        BackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/BackIcon.png"))); // NOI18N
        BackButton.setToolTipText("Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

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

        SubmitButton.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        SubmitButton.setText("Submit");
        SubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(IDLabel)
                        .addComponent(IDField, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(SubmitButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BackButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(IDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(IDField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SubmitButton)
                .addGap(29, 29, 29))
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

    private void SubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitButtonActionPerformed

        this.globalValidationState = true;

        this.IDFieldFocusLost(null);

        if(this.globalValidationState && this.verify()){

            this.redirectToNextFrame();

        } else {

            JOptionPane.showMessageDialog(this, "Please enter a valid ID.", "ID field", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_SubmitButtonActionPerformed

     private boolean verify(){
    
        return this.controller.isAdministratorExists(this.IDField.getText());
    }
     
    private void redirectToNextFrame(){
    
        this.dispose();
        
        new AdministratorControllerImp(this.nextFrame, new EBS_DbContext(), this.loggedinID, this.IDField.getText());
        this.nextFrame.setVisible(true);
    }
    
    @Override
    public final void setSpecialSettings() {
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    @Override
    public final void setController(Controller controller){
        
        this.controller = (AdministratorLoginController) controller;
    }
    
    @Override
    public final void setPreviousFrame(javax.swing.JFrame previousFrame){
    
        this.previousFrame = previousFrame;
    }
    
    @Override
    public javax.swing.JFrame getPreviousFrame(){
    
        return this.previousFrame;
    }
    
    private boolean globalValidationState = false;
    private AdministratorLoginController controller;
    private javax.swing.JFrame previousFrame;
    private final View nextFrame;
    private final String loggedinID;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JTextField IDField;
    private javax.swing.JLabel IDLabel;
    private javax.swing.JButton SubmitButton;
    // End of variables declaration//GEN-END:variables
}
