/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.swing.Help;

import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.model.entities.Help.SetEnums;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ir
 */
public class ManagementTablesModel {

    
    private ManagementFrm frmMan;

    
    
    public ManagementTablesModel(ManagementFrm frmMan) {
        this.frmMan = frmMan;
        
    }
    
    private DefaultTableModel searchTable;
    private DefaultTableModel doseTable;
    private DefaultTableModel dsmtTable;
 
    
    public DefaultTableModel getSearchTable() {
        return searchTable;
    }

    public void setSearchTable(DefaultTableModel searchTable) {
        this.searchTable = searchTable;
    }

    public DefaultTableModel getDoseTable() {
        return doseTable;
    }

    public void setDoseTable(DefaultTableModel doseTable) {
        this.doseTable = doseTable;
    }

    public DefaultTableModel getDsmtTable() {
        return dsmtTable;
    }

    public void setDsmtTable(DefaultTableModel dsmtTable) {
        this.dsmtTable = dsmtTable;
    }
    
    
    
    
    
    
    public void setDefaultSearchTable () {
        
        setSearchTable(setDefaultSettingsSearchTable());
        
    }
    
    
    public void setDefaultDoseTable () {
        
        setDoseTable(setDefaultSettingsDoseTable());
        
    }
    
    
    public void setDefaultDsmtTable (String tableStatus) {
        
        System.out.println(" Estou no mangement tables ... " + tableStatus);
        
        if (tableStatus.equalsIgnoreCase("readonly")) {
            setDsmtTable(setDefaultSettingsDosimeterTable());
         } else if (tableStatus.equalsIgnoreCase("newdsmt")) {
            setNewDosimeterSettingsTable();
        }
    
    }
    
    
    
 
    // define Models for Tables
    
    
    
    private DefaultTableModel setDefaultSettingsSearchTable () {
 
        
        DefaultTableModel model = new DefaultTableModel(new Object [][] {},
                new String [] { "Mec", "Name", "Category", "Department", "Status" }
                ){
                    @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                            return false; //Disallow the editing of any cell
                    }
                };
        
        this.frmMan.searchTable.setModel(model);
        
        this.frmMan.searchTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        this.frmMan.searchTable.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        this.frmMan.searchTable.setFocusable(true);
        this.frmMan.searchTable.setRequestFocusEnabled(true);
        this.frmMan.searchTable.setUpdateSelectionOnSort(false);
        this.frmMan.searchTable.setDragEnabled(false);
        this.frmMan.searchTable.setRowSelectionAllowed(true);
         
        this.frmMan.searchTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.frmMan.searchTable.getColumnModel().getColumn(1).setPreferredWidth(210);
        this.frmMan.searchTable.getColumnModel().getColumn(2).setPreferredWidth(90);
        this.frmMan.searchTable.getColumnModel().getColumn(3).setPreferredWidth(70);
        this.frmMan.searchTable.getColumnModel().getColumn(4).setPreferredWidth(70);
        
        return model;
        
    }
    
    
 // for dosimeters ---  Models 
    
    private DefaultTableModel setDefaultSettingsDosimeterTable() {
 
        
        DefaultTableModel model = new DefaultTableModel(new Object [][] {},
                new String [] { "Id", "Label", "Type", "Periodicity", "Supplier", "Inserted", "Comments", "Status", "LastChanged" }
                ){
                    @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                            return false; //Disallow the editing of any cell
                    }
                };
        
        this.frmMan.tableDosimeterInfo.setModel(model);
        
        this.frmMan.tableDosimeterInfo.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        this.frmMan.tableDosimeterInfo.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        this.frmMan.tableDosimeterInfo.setFocusable(true);
        this.frmMan.tableDosimeterInfo.setRequestFocusEnabled(true);
        this.frmMan.tableDosimeterInfo.setUpdateSelectionOnSort(false);
        this.frmMan.tableDosimeterInfo.setDragEnabled(false);
        this.frmMan.tableDosimeterInfo.setRowSelectionAllowed(true);
         
        this.frmMan.tableDosimeterInfo.getColumnModel().getColumn(0).setPreferredWidth(15);
        this.frmMan.tableDosimeterInfo.getColumnModel().getColumn(1).setPreferredWidth(40);
        this.frmMan.tableDosimeterInfo.getColumnModel().getColumn(2).setPreferredWidth(10);
        this.frmMan.tableDosimeterInfo.getColumnModel().getColumn(3).setPreferredWidth(25);
        this.frmMan.tableDosimeterInfo.getColumnModel().getColumn(4).setPreferredWidth(30);
        this.frmMan.tableDosimeterInfo.getColumnModel().getColumn(5).setPreferredWidth(80);
        this.frmMan.tableDosimeterInfo.getColumnModel().getColumn(6).setPreferredWidth(50);
        this.frmMan.tableDosimeterInfo.getColumnModel().getColumn(7).setPreferredWidth(30);
        this.frmMan.tableDosimeterInfo.getColumnModel().getColumn(8).setPreferredWidth(80);
        
        return model;
        
    }

    
    private void setNewDosimeterSettingsTable () {
 
        
        DefaultTableModel model = new DefaultTableModel(new Object [][] {},
                new String [] { "Id", "Label", "Type", "Periodicity", "Supplier", "Created", "Comments", "Status", "LastChanged" }
                ){
                    @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                        switch(colIndex){
                            case 5:                   // ONLY 4TH COL IS EDITABLE
                                return false;
                            case 8:                   // ONLY 4TH COL IS EDITABLE
                                return false;
                            default:
                                return true;
                        }
       
                    }
                };
        
        this.frmMan.tableDosimeterInfo.setModel(model);
        
        this.frmMan.tableDosimeterInfo.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        this.frmMan.tableDosimeterInfo.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        this.frmMan.tableDosimeterInfo.setFocusable(true);
        this.frmMan.tableDosimeterInfo.setRequestFocusEnabled(true);
        this.frmMan.tableDosimeterInfo.setUpdateSelectionOnSort(false);
        this.frmMan.tableDosimeterInfo.setDragEnabled(false);
        this.frmMan.tableDosimeterInfo.setRowSelectionAllowed(true);
         
        this.frmMan.tableDosimeterInfo.getColumnModel().getColumn(0).setPreferredWidth(15);
        this.frmMan.tableDosimeterInfo.getColumnModel().getColumn(1).setPreferredWidth(40);
        this.frmMan.tableDosimeterInfo.getColumnModel().getColumn(2).setPreferredWidth(10);
        this.frmMan.tableDosimeterInfo.getColumnModel().getColumn(3).setPreferredWidth(25);
        this.frmMan.tableDosimeterInfo.getColumnModel().getColumn(4).setPreferredWidth(30);
        this.frmMan.tableDosimeterInfo.getColumnModel().getColumn(5).setPreferredWidth(80);
        this.frmMan.tableDosimeterInfo.getColumnModel().getColumn(6).setPreferredWidth(50);
        this.frmMan.tableDosimeterInfo.getColumnModel().getColumn(7).setPreferredWidth(30);
        this.frmMan.tableDosimeterInfo.getColumnModel().getColumn(8).setPreferredWidth(80);
        
        JComboBox cbType = new JComboBox(SetEnums.dsmt_type.values());
        JComboBox cbPerd = new JComboBox(SetEnums.dsmt_periodicity.values());
        JComboBox cbSuppl = new JComboBox(SetEnums.dsmt_supplier.values());
        JComboBox cbStatus = new JComboBox(SetEnums.status.values());
  
        frmMan.tableDosimeterInfo.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(cbType));
        frmMan.tableDosimeterInfo.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(cbPerd));
        frmMan.tableDosimeterInfo.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(cbSuppl));
        frmMan.tableDosimeterInfo.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(cbStatus));
        
        
        Object newRow[] = new Object [] {"", "", SetEnums.dsmt_type.CI, SetEnums.dsmt_periodicity.Trimestral
                     , SetEnums.dsmt_supplier.MedicalConsult, "", "", SetEnums.status.Activo, ""};
        model.addRow(newRow);
        
    }
    
    
    
    // Dose models
    
       
    private DefaultTableModel setDefaultSettingsDoseTable () {
 
        
        DefaultTableModel model = new DefaultTableModel(new Object [][] {},
                new String [] { "Year", "Trimester", "Month", "Hp007", "Hp10", "Comments", "LastChange" }
                ){
                    @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                            return false; //Disallow the editing of any cell
                    }
                };
        
        this.frmMan.tableDoseInfo.setModel(model);
        
        this.frmMan.tableDoseInfo.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        this.frmMan.tableDoseInfo.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        this.frmMan.tableDoseInfo.setFocusable(true);
        this.frmMan.tableDoseInfo.setRequestFocusEnabled(true);
        this.frmMan.tableDoseInfo.setUpdateSelectionOnSort(false);
        this.frmMan.tableDoseInfo.setDragEnabled(false);
        this.frmMan.tableDoseInfo.setRowSelectionAllowed(true);
         
        this.frmMan.tableDoseInfo.getColumnModel().getColumn(0).setPreferredWidth(20);
        this.frmMan.tableDoseInfo.getColumnModel().getColumn(1).setPreferredWidth(40);
        this.frmMan.tableDoseInfo.getColumnModel().getColumn(2).setPreferredWidth(30);
        this.frmMan.tableDoseInfo.getColumnModel().getColumn(3).setPreferredWidth(15);
        this.frmMan.tableDoseInfo.getColumnModel().getColumn(4).setPreferredWidth(15);
        this.frmMan.tableDoseInfo.getColumnModel().getColumn(5).setPreferredWidth(90);
        this.frmMan.tableDoseInfo.getColumnModel().getColumn(6).setPreferredWidth(90);
        
        return model;
        
    }
    
    
}
