package Views;

import Controllers.CustomerDashboardControllerImp;
import Controllers.Interface.Controller;
import Controllers.Interface.CustomerLoginController;
import Models.EBS_DbContext;
import javax.swing.JOptionPane;

/**
 *
 * @author xorigin
 */
public class MeterCodeVerificator extends javax.swing.JFrame implements View{

    /**
     * Creates new form MeterCodeVerificator
     * @param previousFrame
     * @param nextFrame
     * @param controller
     */
    public MeterCodeVerificator(javax.swing.JFrame previousFrame, Controller controller, View nextFrame, String loggedinMeterCode) {
        
        
        this.setPreviousFrame(previousFrame);
        
        this.nextFrame = nextFrame;
        this.loggedinMeterCode = loggedinMeterCode;
        
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
        MeterCodeField = new javax.swing.JTextField();
        MeterCodeLabel = new javax.swing.JLabel();
        SubmitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Enter meter code");
        setResizable(false);

        BackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/BackIcon.png"))); // NOI18N
        BackButton.setToolTipText("Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        MeterCodeField.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        MeterCodeField.setToolTipText("Enter meter code");
        MeterCodeField.setMinimumSize(new java.awt.Dimension(64, 30));
        MeterCodeField.setPreferredSize(new java.awt.Dimension(64, 30));
        MeterCodeField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                MeterCodeFieldFocusLost(evt);
            }
        });

        MeterCodeLabel.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        MeterCodeLabel.setText("Meter code");
        MeterCodeLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(MeterCodeLabel)
                            .addGap(207, 207, 207))
                        .addComponent(MeterCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(SubmitButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(53, 53, 53))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BackButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MeterCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MeterCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SubmitButton)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed

        this.dispose();
        this.previousFrame.revalidate();
        this.previousFrame.setVisible(true);
    }//GEN-LAST:event_BackButtonActionPerformed

    private void MeterCodeFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_MeterCodeFieldFocusLost

        if(this.MeterCodeField.getText().isBlank()){
            
            this.globalValidationState = false;
            JOptionPane.showMessageDialog(this, "Please enter a valid meter code.", "Meter code field", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_MeterCodeFieldFocusLost

    private void SubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitButtonActionPerformed
        
        this.globalValidationState = true;
        
        this.MeterCodeFieldFocusLost(null);
        
        if(this.globalValidationState && this.verify()){
        
            this.redirectToNextFrame();
            
        } else {
        
            JOptionPane.showMessageDialog(this, "Please enter a valid meter code.", "Meter code field", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_SubmitButtonActionPerformed

    private boolean verify(){
    
        return this.controller.isMeterCodeExists(this.MeterCodeField.getText());
    }

    private void redirectToNextFrame(){
    
        this.dispose();
        
        new CustomerDashboardControllerImp(this.nextFrame, new EBS_DbContext(), this.loggedinMeterCode, this.MeterCodeField.getText());
        this.nextFrame.setVisible(true);
    }
    
    @Override
    public final void setSpecialSettings() {
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    @Override
    public final void setController(Controller controller){
        
        this.controller = (CustomerLoginController) controller;
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
    private CustomerLoginController controller;
    private javax.swing.JFrame previousFrame;
    private final View nextFrame;
    private final String loggedinMeterCode;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JTextField MeterCodeField;
    private javax.swing.JLabel MeterCodeLabel;
    private javax.swing.JButton SubmitButton;
    // End of variables declaration//GEN-END:variables
}
