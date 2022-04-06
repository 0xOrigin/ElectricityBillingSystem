package Views.Admin;

import Controllers.AdminDashboardControllerImp;
import Controllers.AdministratorLoginControllerImp;
import Controllers.CustomerLoginControllerImp;
import Controllers.Interface.AdminDashboardController;
import Controllers.Interface.Controller;
import Controllers.NewCustomerControllerImp;
import Models.EBS_DbContext;
import Views.BillsOfRegionView;
import Views.IdVerificator;
import Views.MeterCodeVerificator;
import Views.Customer.NewCustomerView;
import Views.View;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author xorigin
 */
public class AdminDashboardView extends javax.swing.JFrame implements View {

    /**
     * Creates new form AdminDashboardView
     * @param previousFrame
     */
    public AdminDashboardView(javax.swing.JFrame previousFrame) {
        
        this.setPreviousFrame(previousFrame);
        
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
        ViewBillsOfRegionButton = new javax.swing.JButton();
        ViewTotalCollectedButton = new javax.swing.JButton();
        MakeConsumptionStatButton = new javax.swing.JButton();
        AddAdministratorButton = new javax.swing.JButton();
        AddCustomerButton = new javax.swing.JButton();
        UpdateAdministratorButton = new javax.swing.JButton();
        UpdateCustomerButton = new javax.swing.JButton();
        DeleteAdministratorButton = new javax.swing.JButton();
        DeleteCustomerButton = new javax.swing.JButton();
        SignedAsField = new javax.swing.JLabel();
        SignedAsLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Dashboard");
        setResizable(false);

        BackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/BackIcon.png"))); // NOI18N
        BackButton.setToolTipText("Log out");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        FrameDescription.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        FrameDescription.setText(" Admin Dashboard ");
        FrameDescription.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        ViewBillsOfRegionButton.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        ViewBillsOfRegionButton.setText("View all bills of specific region");
        ViewBillsOfRegionButton.setToolTipText("Press to view all bills of specific region");
        ViewBillsOfRegionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewBillsOfRegionButtonActionPerformed(evt);
            }
        });

        ViewTotalCollectedButton.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        ViewTotalCollectedButton.setText("View total collected money");
        ViewTotalCollectedButton.setToolTipText("Press to enter view total collected money");
        ViewTotalCollectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewTotalCollectedButtonActionPerformed(evt);
            }
        });

        MakeConsumptionStatButton.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        MakeConsumptionStatButton.setText("Make consumption statistics of specific region");
        MakeConsumptionStatButton.setToolTipText("Press to make consumption statistics");
        MakeConsumptionStatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MakeConsumptionStatButtonActionPerformed(evt);
            }
        });

        AddAdministratorButton.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        AddAdministratorButton.setText("Add administrator");
        AddAdministratorButton.setToolTipText("Press to add administrator");
        AddAdministratorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddAdministratorButtonActionPerformed(evt);
            }
        });

        AddCustomerButton.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        AddCustomerButton.setText("Add customer");
        AddCustomerButton.setToolTipText("Press to add customer");
        AddCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddCustomerButtonActionPerformed(evt);
            }
        });

        UpdateAdministratorButton.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        UpdateAdministratorButton.setText("Update administrator");
        UpdateAdministratorButton.setToolTipText("Press to update administrator");
        UpdateAdministratorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateAdministratorButtonActionPerformed(evt);
            }
        });

        UpdateCustomerButton.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        UpdateCustomerButton.setText("Update customer");
        UpdateCustomerButton.setToolTipText("Press to update customer");
        UpdateCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateCustomerButtonActionPerformed(evt);
            }
        });

        DeleteAdministratorButton.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        DeleteAdministratorButton.setText("Delete administrator");
        DeleteAdministratorButton.setToolTipText("Press to delete administrator");
        DeleteAdministratorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteAdministratorButtonActionPerformed(evt);
            }
        });

        DeleteCustomerButton.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        DeleteCustomerButton.setText("Delete customer");
        DeleteCustomerButton.setToolTipText("Press to delete customer");
        DeleteCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteCustomerButtonActionPerformed(evt);
            }
        });

        SignedAsField.setFont(new java.awt.Font("Chandas", 1, 14)); // NOI18N
        SignedAsField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SignedAsField.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                SignedAsFieldAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        SignedAsLabel.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        SignedAsLabel.setForeground(new java.awt.Color(51, 51, 51));
        SignedAsLabel.setText("Signed as: ");
        SignedAsLabel.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(104, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(MakeConsumptionStatButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ViewTotalCollectedButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ViewBillsOfRegionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(DeleteAdministratorButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(UpdateAdministratorButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AddAdministratorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(AddCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(UpdateCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(DeleteCustomerButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(101, 101, 101))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(128, 128, 128)
                        .addComponent(FrameDescription))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(SignedAsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SignedAsField, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(183, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BackButton, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(FrameDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(SignedAsField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SignedAsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(MakeConsumptionStatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ViewTotalCollectedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ViewBillsOfRegionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddAdministratorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UpdateAdministratorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UpdateCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DeleteAdministratorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed

        this.dispose();
        this.previousFrame.revalidate();
        this.previousFrame.setVisible(true);
    }//GEN-LAST:event_BackButtonActionPerformed

    private void ViewBillsOfRegionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewBillsOfRegionButtonActionPerformed
        
        this.dispose();
        new BillsOfRegionView(this, this.controller);
    }//GEN-LAST:event_ViewBillsOfRegionButtonActionPerformed

    private void ViewTotalCollectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewTotalCollectedButtonActionPerformed
        
        String message = "The total collected money by operators is: " + this.controller.getTotalCollectedMoney() + " .";
        JOptionPane.showMessageDialog(this, message, "Total collected money", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_ViewTotalCollectedButtonActionPerformed

    private void MakeConsumptionStatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MakeConsumptionStatButtonActionPerformed
        
        this.dispose();
        new ConsumptionStatisticsForRegion(this, this.controller);
    }//GEN-LAST:event_MakeConsumptionStatButtonActionPerformed

    private void AddAdministratorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddAdministratorButtonActionPerformed
        
        this.dispose();
        new AdminDashboardControllerImp(new NewAdministratorView(this), new EBS_DbContext());
    }//GEN-LAST:event_AddAdministratorButtonActionPerformed

    private void AddCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddCustomerButtonActionPerformed
        
        this.dispose();
        new NewCustomerControllerImp(new NewCustomerView(this), new EBS_DbContext());
    }//GEN-LAST:event_AddCustomerButtonActionPerformed

    private void UpdateAdministratorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateAdministratorButtonActionPerformed
        
        this.dispose();
        new IdVerificator(this, new AdministratorLoginControllerImp(null, new EBS_DbContext()), new UpdateAdministratorView(this), this.controller.getLoggedInID());
    }//GEN-LAST:event_UpdateAdministratorButtonActionPerformed

    private void UpdateCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateCustomerButtonActionPerformed
        
        this.dispose();
        new MeterCodeVerificator(this, new CustomerLoginControllerImp(null, new EBS_DbContext()), new UpdateCustomerView(this));
    }//GEN-LAST:event_UpdateCustomerButtonActionPerformed

    private void DeleteAdministratorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteAdministratorButtonActionPerformed
        
        this.dispose();
        new IdVerificator(this, new AdministratorLoginControllerImp(null, new EBS_DbContext()), new DeleteAdministratorView(this), this.controller.getLoggedInID());
    }//GEN-LAST:event_DeleteAdministratorButtonActionPerformed

    private void DeleteCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteCustomerButtonActionPerformed
        
        this.dispose();
        new MeterCodeVerificator(this, new CustomerLoginControllerImp(null, new EBS_DbContext()), new DeleteCustomerView(this));
    }//GEN-LAST:event_DeleteCustomerButtonActionPerformed

    private void SignedAsFieldAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_SignedAsFieldAncestorAdded

        this.SignedAsField.setText(this.controller.getLoggedInID());
    }//GEN-LAST:event_SignedAsFieldAncestorAdded

    @Override
    public final void setSpecialSettings() {
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    @Override
    public void setController(Controller controller){
    
        this.controller = (AdminDashboardController) controller;
    }
    
    @Override
    public final void setPreviousFrame(JFrame previousFrame) {
        
        this.previousFrame = previousFrame;
    }
    
    @Override
    public javax.swing.JFrame getPreviousFrame(){
    
        return this.previousFrame;
    }
    
    private AdminDashboardController controller;
    private javax.swing.JFrame previousFrame;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddAdministratorButton;
    private javax.swing.JButton AddCustomerButton;
    private javax.swing.JButton BackButton;
    private javax.swing.JButton DeleteAdministratorButton;
    private javax.swing.JButton DeleteCustomerButton;
    private javax.swing.JLabel FrameDescription;
    private javax.swing.JButton MakeConsumptionStatButton;
    private javax.swing.JLabel SignedAsField;
    private javax.swing.JLabel SignedAsLabel;
    private javax.swing.JButton UpdateAdministratorButton;
    private javax.swing.JButton UpdateCustomerButton;
    private javax.swing.JButton ViewBillsOfRegionButton;
    private javax.swing.JButton ViewTotalCollectedButton;
    // End of variables declaration//GEN-END:variables

}
