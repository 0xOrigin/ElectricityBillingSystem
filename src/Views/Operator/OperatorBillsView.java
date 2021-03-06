package Views.Operator;

import Controllers.Interface.Controller;
import Models.Enum.ActivationState;
import Models.Enum.Column;
import Models.Enum.GovernmentCode;
import Models.Enum.PaymentState;
import Views.View;
import Views.ViewsHelper;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import Controllers.Interface.CustomerController;

/**
 *
 * @author acro
 */
public class OperatorBillsView extends javax.swing.JFrame implements View {

    /**
     * Creates new form OperatorBillsView
     *
     * @param previousFrame
     */
    public OperatorBillsView(javax.swing.JFrame previousFrame) {

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

        NumOfBillsField = new javax.swing.JLabel();
        ScrollPane = new javax.swing.JScrollPane();
        BillsTable = new javax.swing.JTable();
        BackButton = new javax.swing.JButton();
        StatusField = new javax.swing.JComboBox<>();
        StatusLabel = new javax.swing.JLabel();
        NumOfBillsLabel = new javax.swing.JLabel();
        PayBill = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("View bills");
        setResizable(false);

        NumOfBillsField.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        NumOfBillsField.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        BillsTable.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        BillsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Number", "Governorate", "Tariff", "Last Reading", "Current Reading", "Consumption", "Money Value", "Status", "Release Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        BillsTable.getTableHeader().setReorderingAllowed(false);
        BillsTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                BillsTablePropertyChange(evt);
            }
        });
        ScrollPane.setViewportView(BillsTable);

        BackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/BackIcon.png"))); // NOI18N
        BackButton.setToolTipText("Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        StatusField.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        StatusField.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                StatusFieldAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        StatusField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusFieldActionPerformed(evt);
            }
        });

        StatusLabel.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        StatusLabel.setText("Select status");

        NumOfBillsLabel.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        NumOfBillsLabel.setText("Number of Bills: ");

        PayBill.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        PayBill.setText("Pay bill");
        PayBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PayBillActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(389, 389, 389)
                .addComponent(StatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(StatusField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 392, Short.MAX_VALUE)
                .addComponent(PayBill, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(NumOfBillsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(NumOfBillsField, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PayBill, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StatusField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(265, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(BackButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NumOfBillsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(NumOfBillsField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BillsTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_BillsTablePropertyChange

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int iterator = 0; iterator < BillsTable.getColumnCount(); iterator++)
            BillsTable.getColumnModel().getColumn(iterator).setCellRenderer(centerRenderer);
    }//GEN-LAST:event_BillsTablePropertyChange

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        
        this.dispose();
        this.previousFrame.revalidate();
        this.previousFrame.setVisible(true);
    }//GEN-LAST:event_BackButtonActionPerformed

    private void StatusFieldAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_StatusFieldAncestorAdded
        
        this.fillStatusField();
        this.loadAllBills();
        this.fillBillsTableByStatus(this.StatusField.getSelectedItem().toString());
    }//GEN-LAST:event_StatusFieldAncestorAdded

    private void StatusFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusFieldActionPerformed
        
        this.fillBillsTableByStatus(this.StatusField.getSelectedItem().toString());
    }//GEN-LAST:event_StatusFieldActionPerformed

    private void PayBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PayBillActionPerformed
        
        this.StatusField.setSelectedIndex(1);
        
        if (this.controller.getActivationState().equals(ActivationState.Inactive.name())) {
            JOptionPane.showMessageDialog(null, "\t This meter is inactive \t");
            return;
        }
        
        if (this.tableModel.getRowCount() < 1) {
            JOptionPane.showMessageDialog(null, "\t There is no unpaid bill! \t");
            return;
        }
        
        this.dispose();
        new BillPayment(this, this.controller, this.getChosenBillMap());
    }//GEN-LAST:event_PayBillActionPerformed
    
    private Map<Enum, Object> getChosenBillMap(){
        
        int start = 0, end = this.bills.size() - 1, mid;
        
        int selectedBillNumFromTable = Integer.valueOf(this.BillsTable.getValueAt(0, 0).toString());

        while(start <= end){
        
            mid = (start + end) / 2;

            int billNumber = Integer.valueOf(this.bills.get(mid).get(Column.Num).toString());
            
            if(billNumber == selectedBillNumFromTable)
                return this.bills.get(mid);
            
            if(billNumber < selectedBillNumFromTable)
                start = mid + 1;
            
            else
                end = mid - 1;
        }
                
        return null;
    }
    
    private void fillBillsTableByStatus(String status) {

        this.tableModel = (DefaultTableModel) this.BillsTable.getModel();

        this.clearTableRows();

        for (Map<Enum, Object> bill : this.bills) {
            if (status.equals(bill.get(Column.Status).toString())) {
                this.tableModel.addRow(this.constructRow(bill));
            }
        }

        this.updateNumOfBills();
    }

    private void fillStatusField() {

        String[] states = ViewsHelper.getStringValues(PaymentState.values());
        if (this.StatusField.getItemCount() < 2) {
            for (String state : states) {
                this.StatusField.addItem(state);
            }

            this.StatusField.setSelectedIndex(1);
        }
    }

    private void clearTableRows() {

        this.tableModel.setRowCount(0);
    }

    private Object[] constructRow(Map<Enum, Object> bill) {

        Object[] billInfo = new Object[9];
        billInfo[0] = bill.get(Column.Num);
        billInfo[1] = ViewsHelper.getStringValueFromEnumStringValue(GovernmentCode.getEnumNameForValue(bill.get(Column.GovernmentCode)));
        billInfo[2] = bill.get(Column.Tariff);
        billInfo[3] = bill.get(Column.LastReading);
        billInfo[4] = bill.get(Column.CurrentReading);
        billInfo[5] = bill.get(Column.Consumption);
        billInfo[6] = String.format("%.2f", bill.get(Column.MoneyValue));
        billInfo[7] = bill.get(Column.Status);
        billInfo[8] = bill.get(Column.ReleaseDate);

        return billInfo;
    }

    private void updateNumOfBills() {

        this.NumOfBillsField.setText(String.valueOf(this.tableModel.getRowCount()));
    }

    @Override
    public final void setSpecialSettings() {
        this.setLocationRelativeTo(null);
    }

    @Override
    public final void setController(Controller controller) {
        this.controller = (CustomerController) controller;
        this.loadAllBills();
    }

    private void loadAllBills() {
        this.bills = this.controller.getAllBillsOfMeterCode();
    }

    @Override
    public final void setPreviousFrame(JFrame previousFrame) {
        this.previousFrame = previousFrame;
    }

    @Override
    public javax.swing.JFrame getPreviousFrame() {

        return this.previousFrame;
    }

    List<Map<Enum, Object>> bills;
    DefaultTableModel tableModel;
    private CustomerController controller;
    private javax.swing.JFrame previousFrame;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JTable BillsTable;
    private javax.swing.JLabel NumOfBillsField;
    private javax.swing.JLabel NumOfBillsLabel;
    private javax.swing.JButton PayBill;
    private javax.swing.JScrollPane ScrollPane;
    private javax.swing.JComboBox<String> StatusField;
    private javax.swing.JLabel StatusLabel;
    // End of variables declaration//GEN-END:variables
}
