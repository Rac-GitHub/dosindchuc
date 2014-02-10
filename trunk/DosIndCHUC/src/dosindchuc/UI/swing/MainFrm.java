/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.swing;

import dosindchuc.UI.controller.MainActionListener;
import dosindchuc.UI.swing.Help.AlertTableWithRowRendering;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import org.edisoncor.gui.passwordField.PasswordField;
import org.edisoncor.gui.textField.TextField;


/**
 *
 * @author ir
 */
public class MainFrm extends javax.swing.JFrame {

    private MainActionListener listeners;
    private AlertTableWithRowRendering tableNote;
    
    /**
     * Creates new form MainFrm
     */
    public MainFrm() {
        initComponents();
        listeners = new MainActionListener(this);
        System.out.println("3+3");
        tableNote = new AlertTableWithRowRendering(this);
         System.out.println("3+3");
  //      setNotetable();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel2 = new org.edisoncor.gui.panel.Panel();
        panelCHUC = new org.edisoncor.gui.panel.PanelRectTranslucidoComplete();
        jButton1 = new javax.swing.JButton();
        panelShadow1 = new org.edisoncor.gui.panel.PanelShadow();
        panelTranslucidoComplete2 = new org.edisoncor.gui.panel.PanelTranslucidoComplete();
        labelMetric1 = new org.edisoncor.gui.label.LabelMetric();
        labelMetric2 = new org.edisoncor.gui.label.LabelMetric();
        txtPassword = new org.edisoncor.gui.passwordField.PasswordField();
        txtUsername = new org.edisoncor.gui.textField.TextField();
        btLoginOk = new javax.swing.JButton();
        btLoginCancel = new javax.swing.JButton();
        btProfInfo = new javax.swing.JButton();
        btInsertIndDosimetry = new javax.swing.JButton();
        btAdministration = new javax.swing.JButton();
        panelNotes = new org.edisoncor.gui.panel.PanelRectTranslucido();
        btNoteSave = new javax.swing.JButton();
        btNoteCancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelShadow2 = new org.edisoncor.gui.panel.PanelShadow();
        txtNameOfUser = new org.edisoncor.gui.textField.TextFieldRound();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panel2.setColorPrimario(new java.awt.Color(204, 204, 255));
        panel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dosimetry1.jpg"))); // NOI18N

        panelCHUC.setBackground(new java.awt.Color(51, 153, 255));
        panelCHUC.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panelCHUC.setForeground(new java.awt.Color(255, 0, 0));
        panelCHUC.setColorPrimario(new java.awt.Color(204, 255, 255));
        panelCHUC.setDoubleBuffered(false);
        panelCHUC.setEnabled(false);
        panelCHUC.setFocusable(false);
        panelCHUC.setOpaque(false);
        panelCHUC.setRequestFocusEnabled(false);
        panelCHUC.setTran(0.2F);
        panelCHUC.setVerifyInputWhenFocusTarget(false);

        jButton1.setBackground(new java.awt.Color(204, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logotipo_chuc_novo.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setDefaultCapable(false);
        jButton1.setFocusable(false);
        jButton1.setRequestFocusEnabled(false);
        jButton1.setRolloverEnabled(false);
        jButton1.setVerifyInputWhenFocusTarget(false);

        panelShadow1.setDistance(6);

        panelTranslucidoComplete2.setBackground(new java.awt.Color(102, 102, 255));
        panelTranslucidoComplete2.setForeground(new java.awt.Color(51, 102, 255));
        panelTranslucidoComplete2.setColorPrimario(new java.awt.Color(0, 204, 255));
        panelTranslucidoComplete2.setEnabled(false);
        panelTranslucidoComplete2.setFocusable(false);
        panelTranslucidoComplete2.setOpaque(false);

        labelMetric1.setText("Username:");
        labelMetric1.setFocusable(false);
        labelMetric1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        labelMetric2.setText("Password:");
        labelMetric2.setFocusable(false);
        labelMetric2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        btLoginOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/success48.png"))); // NOI18N
        btLoginOk.setText("LoginOK");
        btLoginOk.setBorderPainted(false);
        btLoginOk.setContentAreaFilled(false);
        btLoginOk.setFocusPainted(false);

        btLoginCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel48.png"))); // NOI18N
        btLoginCancel.setText("LoginCancel");
        btLoginCancel.setBorderPainted(false);
        btLoginCancel.setContentAreaFilled(false);
        btLoginCancel.setDefaultCapable(false);
        btLoginCancel.setFocusPainted(false);
        btLoginCancel.setFocusable(false);
        btLoginCancel.setRequestFocusEnabled(false);
        btLoginCancel.setRolloverEnabled(false);
        btLoginCancel.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout panelTranslucidoComplete2Layout = new javax.swing.GroupLayout(panelTranslucidoComplete2);
        panelTranslucidoComplete2.setLayout(panelTranslucidoComplete2Layout);
        panelTranslucidoComplete2Layout.setHorizontalGroup(
            panelTranslucidoComplete2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucidoComplete2Layout.createSequentialGroup()
                .addGroup(panelTranslucidoComplete2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelTranslucidoComplete2Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(panelTranslucidoComplete2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelMetric2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMetric1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelTranslucidoComplete2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTranslucidoComplete2Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(btLoginOk, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btLoginCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        panelTranslucidoComplete2Layout.setVerticalGroup(
            panelTranslucidoComplete2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucidoComplete2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(panelTranslucidoComplete2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelTranslucidoComplete2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(panelTranslucidoComplete2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btLoginOk, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btLoginCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTranslucidoComplete2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTranslucidoComplete2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelCHUCLayout = new javax.swing.GroupLayout(panelCHUC);
        panelCHUC.setLayout(panelCHUCLayout);
        panelCHUCLayout.setHorizontalGroup(
            panelCHUCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCHUCLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCHUCLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
        );
        panelCHUCLayout.setVerticalGroup(
            panelCHUCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCHUCLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );

        btProfInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/couple48.png"))); // NOI18N
        btProfInfo.setText("Worker");
        btProfInfo.setActionCommand("Management");
        btProfInfo.setBorderPainted(false);
        btProfInfo.setContentAreaFilled(false);
        btProfInfo.setFocusPainted(false);
        btProfInfo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btProfInfo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btInsertIndDosimetry.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Open-Folder-Accept48.png"))); // NOI18N
        btInsertIndDosimetry.setText("Insert\nDoses");
        btInsertIndDosimetry.setBorderPainted(false);
        btInsertIndDosimetry.setContentAreaFilled(false);
        btInsertIndDosimetry.setFocusPainted(false);
        btInsertIndDosimetry.setFocusable(false);
        btInsertIndDosimetry.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btInsertIndDosimetry.setRequestFocusEnabled(false);
        btInsertIndDosimetry.setRolloverEnabled(false);
        btInsertIndDosimetry.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btAdministration.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/configure48.png"))); // NOI18N
        btAdministration.setText("Administration");
        btAdministration.setBorderPainted(false);
        btAdministration.setContentAreaFilled(false);
        btAdministration.setDefaultCapable(false);
        btAdministration.setFocusPainted(false);
        btAdministration.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btAdministration.setRequestFocusEnabled(false);
        btAdministration.setRolloverEnabled(false);
        btAdministration.setVerifyInputWhenFocusTarget(false);
        btAdministration.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        panelNotes.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        panelNotes.setTran(0.2F);
        panelNotes.setVerifyInputWhenFocusTarget(false);

        btNoteSave.setText("Save");
        btNoteSave.setActionCommand("btNoteSave");

        btNoteCancel.setText("Cancel");
        btNoteCancel.setActionCommand("btNoteCancel");

        jScrollPane1.setOpaque(false);

        javax.swing.GroupLayout panelNotesLayout = new javax.swing.GroupLayout(panelNotes);
        panelNotes.setLayout(panelNotesLayout);
        panelNotesLayout.setHorizontalGroup(
            panelNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNotesLayout.createSequentialGroup()
                .addGroup(panelNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNotesLayout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addComponent(btNoteSave)
                        .addGap(154, 154, 154)
                        .addComponent(btNoteCancel))
                    .addGroup(panelNotesLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        panelNotesLayout.setVerticalGroup(
            panelNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNotesLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNoteSave)
                    .addComponent(btNoteCancel))
                .addGap(79, 79, 79))
        );

        panelShadow2.setForeground(new java.awt.Color(238, 238, 238));
        panelShadow2.setDoubleBuffered(false);
        panelShadow2.setFocusable(false);

        txtNameOfUser.setBorder(null);
        txtNameOfUser.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtNameOfUser.setAutoscrolls(false);
        txtNameOfUser.setBorde(0.0F);
        txtNameOfUser.setCaretColor(new java.awt.Color(255, 255, 255));
        txtNameOfUser.setDistanciaDeSombra(5);
        txtNameOfUser.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        txtNameOfUser.setOpacidad(new java.lang.Float(0.2F));
        txtNameOfUser.setRequestFocusEnabled(false);
        txtNameOfUser.setSelectedTextColor(new java.awt.Color(204, 255, 255));
        txtNameOfUser.setVerifyInputWhenFocusTarget(false);
        txtNameOfUser.setVertical(false);

        javax.swing.GroupLayout panelShadow2Layout = new javax.swing.GroupLayout(panelShadow2);
        panelShadow2.setLayout(panelShadow2Layout);
        panelShadow2Layout.setHorizontalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtNameOfUser, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelShadow2Layout.setVerticalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNameOfUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCHUC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addComponent(btProfInfo)
                                .addGap(118, 118, 118)
                                .addComponent(btInsertIndDosimetry)
                                .addGap(121, 121, 121)
                                .addComponent(btAdministration)
                                .addGap(0, 108, Short.MAX_VALUE))
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelNotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelCHUC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(panelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btInsertIndDosimetry)
                            .addComponent(btProfInfo)
                            .addComponent(btAdministration))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(panelNotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
      /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("1Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//       //         new MainFrm().setVisible(true);
//            }
//        });
    }

    // getter and setters
    
    public JButton getBtInsertIndDosimetry() {
        return btInsertIndDosimetry;
    }

    public void setBtInsertIndDosimetry(JButton btInsertIndDosimetry) {
        this.btInsertIndDosimetry = btInsertIndDosimetry;
    }

    public JButton getBtAdministration() {
        return btAdministration;
    }

    public void setBtAdministration(JButton btAdministration) {
        this.btAdministration = btAdministration;
    }

    public JButton getBtNoteCancel() {
        return btNoteCancel;
    }

    public void setBtCancel(JButton btNoteCancel) {
        this.btNoteCancel = btNoteCancel;
    }

    public JButton getBtLoginOk() {
        return btLoginOk;
    }

    public void setBtLoginOk(JButton btLoginOk) {
        this.btLoginOk = btLoginOk;
    }

    public JButton getBtLoginCancel() {
        return btLoginCancel;
    }

    public void setBtLoginCancel(JButton btLoginCancel) {
        this.btLoginCancel = btLoginCancel;
    }

    public JButton getBtProfInfo() {
        return btProfInfo;
    }

    public void setBtProfInfo(JButton btProfInfo) {
        this.btProfInfo = btProfInfo;
    }

    public JButton getBtNoteSave() {
        return btNoteSave;
    }

    public void setBtNoteSave(JButton btNoteSave) {
        this.btNoteSave = btNoteSave;
    }

    public PasswordField getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(PasswordField txtPassword) {
        this.txtPassword = txtPassword;
    }

    public TextField getTxtUsername() {
        return txtUsername;
    }

    public void setTxtUsername(TextField txtUsername) {
        this.txtUsername = txtUsername;
    }
    
    
    
    
   /* public void setNotetable () {
      
              
        DefaultTableModel model = new DefaultTableModel(new Object [][] {},
                new String [] {
                "Level", "Type", "Mec.", "Name", "Dept.", "Note", "Status", "Date"
                }
                ){
                    @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
      
                        switch(colIndex){
                            case 0:                   // ONLY 4TH COL IS EDITABLE
                                return false;
                            case 6:                   // ONLY 4TH COL IS EDITABLE
                                return false;
                            case 8:                   // ONLY 4TH COL IS EDITABLE
                                return false;
                            default:
                                return true;
                           }
                        
                    }
                };
        
        JTable NoteTable = createData(model);
        
        NoteTable.setModel(model);
        
        NoteTable.setBackground(new java.awt.Color(238, 238, 238));
        NoteTable.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        NoteTable.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
       
        
        
        NoteTable.setEnabled(true);
        NoteTable.setFocusable(true);
        NoteTable.setGridColor(Color.LIGHT_GRAY);
        NoteTable.setOpaque(false);
        NoteTable.setRequestFocusEnabled(false);
        NoteTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        NoteTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        NoteTable.setShowHorizontalLines(true);
        NoteTable.setShowVerticalLines(true);
        NoteTable.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        NoteTable.setUpdateSelectionOnSort(false);
    //    NoteTable.set
        
        NoteTable.setDragEnabled(false);
        NoteTable.setRowSelectionAllowed(true);
        NoteTable.setColumnSelectionAllowed(false);
        System.out.println("nnnote table ... " + NoteTable.getColumnSelectionAllowed());
        
 
        
    }
    
    
    private JTable createData(DefaultTableModel model)
	
       {
		JTable table = new JTable( model )
		{
                    @Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
			{
				Component c = super.prepareRenderer(renderer, row, column);

				//  Color row based on a cell value

				if (isRowSelected(row))
				{
					c.setBackground(Color.BLUE);
					int modelRow = convertRowIndexToModel(row);
					String type = (String)getModel().getValueAt(modelRow, 0);
					if ("Buy".equals(type)) c.setBackground(Color.GREEN);
					if ("Sell".equals(type)) c.setBackground(Color.YELLOW); 
				}

				return c;
			}
		};

	//	table.setPreferredScrollableViewportSize(table.getPreferredSize());
		
       
		return  table ;
	}
    */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btAdministration;
    public javax.swing.JButton btInsertIndDosimetry;
    public javax.swing.JButton btLoginCancel;
    public javax.swing.JButton btLoginOk;
    public javax.swing.JButton btNoteCancel;
    public javax.swing.JButton btNoteSave;
    public javax.swing.JButton btProfInfo;
    private javax.swing.JButton jButton1;
    public javax.swing.JScrollPane jScrollPane1;
    private org.edisoncor.gui.label.LabelMetric labelMetric1;
    private org.edisoncor.gui.label.LabelMetric labelMetric2;
    private org.edisoncor.gui.panel.Panel panel2;
    private org.edisoncor.gui.panel.PanelRectTranslucidoComplete panelCHUC;
    public org.edisoncor.gui.panel.PanelRectTranslucido panelNotes;
    private org.edisoncor.gui.panel.PanelShadow panelShadow1;
    private org.edisoncor.gui.panel.PanelShadow panelShadow2;
    private org.edisoncor.gui.panel.PanelTranslucidoComplete panelTranslucidoComplete2;
    public org.edisoncor.gui.textField.TextFieldRound txtNameOfUser;
    public org.edisoncor.gui.passwordField.PasswordField txtPassword;
    public org.edisoncor.gui.textField.TextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
