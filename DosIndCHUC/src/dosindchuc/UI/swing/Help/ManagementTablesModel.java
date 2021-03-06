/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.swing.Help;

import dosindchuc.UI.swing.MainFrm;
import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Help.DateAndTime;
import dosindchuc.model.entities.Help.SetEnums;
import dosindchuc.model.service.Help.YearMonthAndTrimester;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ir
 */
public class ManagementTablesModel {

    private ManagementFrm frmMan;
 //   private ManagementButtons setButtonsState;
    private YearMonthAndTrimester yearMonthTrimester;
 //   private MainFrm frmMain;
    private DbPkIDs dbPkIDs;
    private DateAndTime dateAndTime = new DateAndTime();
    private JTable table;
    private DefaultTableModel searchTable;
    private DefaultTableModel doseTable;
    private DefaultTableModel dsmtTable;
    private DefaultTableModel dsmtHistTable;

    public ManagementTablesModel(MainFrm frmMain, ManagementFrm frmMan) {
        this.frmMan = frmMan;
  //      setButtonsState = new ManagementButtons(this.frmMan);
  //      this.frmMain = frmMain;
        dbPkIDs = new DbPkIDs();
        yearMonthTrimester = new YearMonthAndTrimester();

    }

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

    public DefaultTableModel getDsmtHistTable() {
        return dsmtHistTable;
    }

    public void setDsmtHistTable(DefaultTableModel dsmtHistTable) {
        this.dsmtHistTable = dsmtHistTable;
    }
    
    
    
    public void setDefaultSearchTable() {

        setSearchTable(setDefaultSettingsSearchTable());

    }
    
    public void setDefaultDsmtHistTable() {

        setDsmtHistTable(setDefaultSettingsDsmtHist());

    }

    public void setDefaultDsmtTable(String tableStatus) {

        if (tableStatus.equalsIgnoreCase("readonly")) {
            setDsmtTable(setDefaultSettingsDosimeterTable());
        } else if (tableStatus.equalsIgnoreCase("newdsmt")) {
            setNewDosimeterSettingsTable();
        } else if (tableStatus.equalsIgnoreCase("updatedsmt")) {
            setDsmtTable(setUpdateDosimeterSettingsTable());
        }

    }

    public void setDefaultDoseTable(String tableStatus) {

        setDoseTable(setDefaultSettingsDoseTable());

        if (tableStatus.equalsIgnoreCase("readonly")) {
            setDoseTable(setDefaultSettingsDoseTable());
        } else if (tableStatus.equalsIgnoreCase("newdose")) {
            setNewDoseSettingsTable();
        } else if (tableStatus.equalsIgnoreCase("updatedose")) {
            setDoseTable(setUpdateDoseSettingsTable());
        }

    }

    /*
     * 
     * define Models for Tables
     * 
     */
    /*
     * 
     *  Search Table
     * 
     */
    private DefaultTableModel setDefaultSettingsSearchTable() {

        table = this.frmMan.searchTable;

        String[] colNames = searchTable("name");

        DefaultTableModel model = new DefaultTableModel(new Object[][]{},
                colNames) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };

        table.setModel(model);

        tableDefaultSettings();

        String[] colWidths = searchTable("width");
        tableColumnsSettings(colWidths);

        return model;

    }

    /*
     * 
     *  Dosimeter Table  - Default
     *   
     */
    private DefaultTableModel setDefaultSettingsDosimeterTable() {

        table = this.frmMan.tableDosimeterInfo;

        String[] colNames = dsmtTable("name");

        DefaultTableModel model = new DefaultTableModel(new Object[][]{},
                colNames) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };

        table.setModel(model);

        tableDefaultSettings();

        String[] colWidths = dsmtTable("width");
        tableColumnsSettings(colWidths);

        return model;

    }

    /*
     *  New Dosimeter Model
     */
    private void setNewDosimeterSettingsTable() {

        table = this.frmMan.tableDosimeterInfo;

        String[] colNames = dsmtTable("name");

        DefaultTableModel model = new DefaultTableModel(new Object[][]{},
                colNames) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                switch (colIndex) {
                    case 5:                   // ONLY 4TH COL IS EDITABLE
                        return false;
                    case 8:                   // ONLY 4TH COL IS EDITABLE
                        return false;
                    default:
                        return true;
                }

            }
        };

        table.setModel(model);

        tableDefaultSettings();

        String[] colWidths = dsmtTable("width");
        tableColumnsSettings(colWidths);


        JComboBox cbType = new JComboBox(SetEnums.dsmt_type.values());
        JComboBox cbPerd = new JComboBox(SetEnums.dsmt_periodicity.values());
        JComboBox cbSuppl = new JComboBox(SetEnums.dsmt_supplier.values());
        JComboBox cbStatus = new JComboBox(SetEnums.dsmt_status.values());

        table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(cbType));
        table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(cbPerd));
        table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(cbSuppl));
        table.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(cbStatus));

        Object newRow[] = new Object[]{"", "", SetEnums.dsmt_type.CI, SetEnums.dsmt_periodicity.Mensal, 
            SetEnums.dsmt_supplier.ITN, "", "", SetEnums.dsmt_status.Pedido, ""};
        model.addRow(newRow);

    }

    /*
     *  Update Dosimeter Model
     */
    private DefaultTableModel setUpdateDosimeterSettingsTable() {

        table = this.frmMan.tableDosimeterInfo;

        final int selectedRow = dbPkIDs.getDsmtRowSelected();

        String[] colNames = dsmtTable("name");

        DefaultTableModel model = new DefaultTableModel(new Object[][]{},
                colNames) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {

                if (rowIndex == selectedRow) {
                    switch (colIndex) {
                        case 5:                   // ONLY 4TH COL IS EDITABLE
                            return false;
                        case 8:                   // ONLY 4TH COL IS EDITABLE
                            return false;
                        default:
                            return true;
                    }
                } else {
                    return false;
                }
            }
        };

        table.setModel(model);

        tableDefaultSettings();

        String[] colWidths = dsmtTable("width");
        tableColumnsSettings(colWidths);

        JComboBox cbType = new JComboBox(SetEnums.dsmt_type.values());
        JComboBox cbPerd = new JComboBox(SetEnums.dsmt_periodicity.values());
        JComboBox cbSuppl = new JComboBox(SetEnums.dsmt_supplier.values());
        JComboBox cbStatus = new JComboBox(SetEnums.dsmt_status.values());

        table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(cbType));
        table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(cbPerd));
        table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(cbSuppl));
        table.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(cbStatus));

        return model;

    }

    /*
     * 
     *  Dose Tables models  - Default
     *   
     */
    private DefaultTableModel setDefaultSettingsDoseTable() {

        table = this.frmMan.tableDoseInfo;

        String[] colNames = doseTable("name");

        DefaultTableModel model = new DefaultTableModel(new Object[][]{},
                colNames) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };

        table.setModel(model);

        tableDefaultSettings();

        String[] colWidths = doseTable("width");
        tableColumnsSettings(colWidths);

        return model;

    }

    /*
     *  New Dose table Model
     */
    private void setNewDoseSettingsTable() {

        table = this.frmMan.tableDoseInfo;

        String[] colNames = doseTable("name");

        DefaultTableModel model = new DefaultTableModel(new Object[][]{},
                colNames) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                switch (colIndex) {
                    case 6:                   // ONLY 4TH COL IS EDITABLE
                        return false;
                    case 8:                   // ONLY 4TH COL IS EDITABLE
                        return false;
                    default:
                        return true;
                }

            }
        };

        table.setModel(model);

        tableDefaultSettings();

        String[] colWidths = doseTable("width");
        tableColumnsSettings(colWidths);

        ArrayList idActDsmt = new ArrayList();
        ArrayList year = new ArrayList();

        /*    Object dsmtInfo[] = new Object[]{dosimeter.getPk_dsmt(), dosimeter.getId(), dosimeter.getLabel(), 
                dosimeter.getType(), dosimeter.getPeriodicity(), dosimeter.getSupplier(), dosimeter.getComments(), 
                dosimeter.getStatus()}; */

        for (int i = 0; i < dbPkIDs.getDsmt_id().size(); ++i) {
            if ((dbPkIDs.getDsmt_id().get(i)[7]).toString().equalsIgnoreCase("Activo")) {
                idActDsmt.add(dbPkIDs.getDsmt_id().get(i)[1]);
            }
        }

        
        int yearNow = Integer.parseInt(dateAndTime.currYear());

        for (int i = yearNow; i > 1999; --i) {
            year.add(i);
        }
    
        
        
        
        
        
        JComboBox cbDsmt = new JComboBox(idActDsmt.toArray());
        JComboBox cbYear = new JComboBox(year.toArray());
        JComboBox cbTrimester = new JComboBox(SetEnums.Trimester.values());
        JComboBox cbMonth = new JComboBox(SetEnums.month.values());

        table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(cbDsmt));
        table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cbYear));
        table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(cbTrimester));
        table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(cbMonth));


        String dsmtPeriodicity = dbPkIDs.getDsmt_id().get(0)[4].toString();
        System.out.println(" Man tables dsmtPeriodicity -->  " +    dsmtPeriodicity);
        if (dsmtPeriodicity.equalsIgnoreCase("Mensal")) {
            SetEnums.month Month[] = SetEnums.month.values();
            int month =  yearMonthTrimester.Month();
            int yearDose = yearMonthTrimester.Year();
            Object newRow[] = new Object[]{idActDsmt.get(0), yearDose, SetEnums.Trimester.NULL, Month[month],
                "", "", "", "", ""};
            model.addRow(newRow);
        } else if (dsmtPeriodicity.equalsIgnoreCase("Trimestral")) {
            SetEnums.Trimester Trimester[] = SetEnums.Trimester.values();
            int trimester = yearMonthTrimester.Trimester();
            int yearDose = yearMonthTrimester.Year();
            Object newRow[] = new Object[]{idActDsmt.get(0), yearDose, Trimester[trimester], SetEnums.month.NULL, 
                "", "", "", "", ""};
            model.addRow(newRow);
        } else {
            System.err.println(" Probs com type do dosimetro");
        }

    }

    /*
     *  Update Dose table Model
     */
    private DefaultTableModel setUpdateDoseSettingsTable() {

        table = this.frmMan.tableDoseInfo;

        final int selectedRow = dbPkIDs.getDoseRowSelected();

        String[] colNames = doseTable("name");

        DefaultTableModel model = new DefaultTableModel(new Object[][]{},
                colNames) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {

                if (rowIndex == selectedRow) {
                    switch (colIndex) {
                        case 0:                   // ONLY 4TH COL IS EDITABLE
                            return false;
                        case 6:                   // ONLY 4TH COL IS EDITABLE
                            return false;
                        case 8:                   // ONLY 4TH COL IS EDITABLE
                            return false;
                        default:
                            return true;
                    }
                } else {
                    return false;
                }
            }
        };

        table.setModel(model);
        tableDefaultSettings();

        String[] colWidths = doseTable("width");
        tableColumnsSettings(colWidths);


        ArrayList year = new ArrayList();

        int yearNow = Integer.parseInt(dateAndTime.currYear());

        for (int i = yearNow; i > 1999; --i) {
            year.add(i);
        }

        JComboBox cbYear = new JComboBox(year.toArray());
        JComboBox cbTrimester = new JComboBox(SetEnums.Trimester.values());
        JComboBox cbMonth = new JComboBox(SetEnums.month.values());

        table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cbYear));
        table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(cbTrimester));
        table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(cbMonth));

        return model;

    }

    
    /*
     * 
     *  Dsmt Hist models  - Default
     *   
     */
    private DefaultTableModel setDefaultSettingsDsmtHist() {

        table = this.frmMan.tableDsmtHist;

        String[] colNames = dsmtHistTable("name");

        DefaultTableModel model = new DefaultTableModel(new Object[][]{},
                colNames) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };

        table.setModel(model);

        tableDefaultSettings();

        String[] colWidths = dsmtHistTable("width");
        tableColumnsSettings(colWidths);

        return model;

    }
    
    
    
    /**
     *
     * @param table
     */
    private void tableDefaultSettings() {

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        table.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        table.setFocusable(true);

        table.setRequestFocusEnabled(true);
        table.setUpdateSelectionOnSort(false);
        table.setDragEnabled(false);
        table.setRowSelectionAllowed(true);

    }

    private void tableColumnsSettings(String[] widths) {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < widths.length; i++) {

            table.getColumnModel().getColumn(i).setPreferredWidth(Integer.parseInt(widths[i]));
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

        }


    }

    private String[] searchTable(String nameOrwidth) {

        if (nameOrwidth.equalsIgnoreCase("name")) {
            String[] names = {"Mec", "Name", "Category", "Department", "Status"};
            return names;
        } else {
            String[] widths = {"50", "210", "90", "70", "70"};
            return widths;
        }

    }

    private String[] dsmtTable(String nameOrwidth) {

        if (nameOrwidth.equalsIgnoreCase("name")) {
            String[] names = {"Id", "Label", "Type", "Periodicity", "Supplier", "Created", "Comments", "Status", "LastChanged"};
            return names;
        } else {
            String[] widths = {"15", "40", "10", "25", "30", "80", "50", "30", "80"};
            return widths;
        }
    }
    
    private String[] dsmtHistTable(String nameOrwidth) {

        if (nameOrwidth.equalsIgnoreCase("name")) {
            String[] names = {"Id_value", "Value", "LastChanged"};
            return names;
        } else {
            String[] widths = {"20", "50", "80"};
            return widths;
        }
    }
    

    private String[] doseTable(String nameOrwidth) {

        if (nameOrwidth.equalsIgnoreCase("name")) {
            String[] names = {"Dsmt", "Year", "Trimester", "Month", "Hp007", "Hp10", "Inserted ", "Comments", "LastChange"};
            return names;
        } else {
            String[] widths = {"10", "10", "35", "20", "10", "10", "70", "80", "70"};
            return widths;
        }
    }
}  
    
  