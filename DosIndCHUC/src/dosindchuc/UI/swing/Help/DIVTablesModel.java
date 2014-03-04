/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.swing.Help;

import dosindchuc.UI.controller.DIVActionListener;
import dosindchuc.UI.controller.Help.DIVTablesActionListener;
import dosindchuc.UI.swing.DIVFrm;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Help.DateAndTime;
import dosindchuc.model.entities.Help.SetEnums;
import dosindchuc.model.service.DIVService;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;





// #######################3
public class DIVTablesModel {

    public static JTable tableNewDIVinfo;
    public static JTable tableOldDIVinfo;
    public static DefaultTableModel modelTableNewDIVinfo;
    public static DefaultTableModel modelTableOldDIVinfo;
 
    
    private DIVFrm frmDIV;
    private DateAndTime dateAndTime = new DateAndTime();
    private CheckBoxRenderer checkBoxRenderer;
    
  //  private DIVTablesActionListener divTablesActionListener;
    
 //   private DIVActionListener listeners;
    //     public static JComboBox trimester1 = new JComboBox();
    final int colPeriod = 2;
    final int colName = 3;
    final int colIdDsmt = 7;
    final int colSetPeriod = 8;
    final int colSetYear = 9;

    
    
    public DIVTablesModel(DIVFrm frmDIV) {

        this.frmDIV = frmDIV;
        checkBoxRenderer = new CheckBoxRenderer();
  //      divTablesActionListener = new DIVTablesActionListener(this.frmDIV);
   //     divService = new DIVService(this.frmDIV);
 //       listeners = new DIVActionListener(this.frmDIV);
 
    }

    
    
    public JTable getTableNewDIVinfo() {
        return tableNewDIVinfo;
    }

    public void setTableNewDIVinfo(JTable tableNewDIVinfo) {
        DIVTablesModel.tableNewDIVinfo = tableNewDIVinfo;
    }

    public DefaultTableModel getModelTableNewDIVinfo() {
        return modelTableNewDIVinfo;
    }

    public void setModelTableNewDIVinfo(DefaultTableModel modelTableNewDIVinfo) {
        DIVTablesModel.modelTableNewDIVinfo = modelTableNewDIVinfo;
    }

 /*
  *   getters and setters for Old DIV table
  */   
    
    public JTable getTableOldDIVinfo() {
        return tableOldDIVinfo;
    }

    public  void setTableOldDIVinfo(JTable tableOldDIVinfo) {
        DIVTablesModel.tableOldDIVinfo = tableOldDIVinfo;
    }

    public  DefaultTableModel getModelTableOldDIVinfo() {
        return modelTableOldDIVinfo;
    }

    public  void setModelTableOldDIVinfo(DefaultTableModel modelTableOldDIVinfo) {
        DIVTablesModel.modelTableOldDIVinfo = modelTableOldDIVinfo;
    }

 
    
 /*
  *  For New Table
  * 
  */   
    
    
    
    public void setSettingsNewDIVinfoTable(String tableStatus) {


        System.out.println("  Que caracas 1--- >>>> " + tableStatus);

        if (tableStatus.equalsIgnoreCase("removeTable")) {
    
            this.frmDIV.panelTableNewDIVinfo.remove(tableNewDIVinfo);

            
            this.frmDIV.btDIV_Save.setEnabled(true);
            this.frmDIV.btDIV_Cancel.setEnabled(true);

        }

        if (tableStatus.equalsIgnoreCase("newTable")) {

            setModelTableNewDIVinfo(setModelNewDIVinfoTable());
            setTableNewDIVinfo(tableNewDIVinfo);

            System.out.println(" Aqui ..... 1111 -> " + this.frmDIV);
            this.frmDIV.panelTableNewDIVinfo.add(tableNewDIVinfo);
            this.frmDIV.panelTableNewDIVinfo.setViewportView(tableNewDIVinfo);
            
     //       divTablesActionListener.addNewDIVInfoTableListeners(tableNewDIVinfo);
        
            
        }

    }

    
  /*
  *  For Old Table
  * 
  */   
    
    
    
    public void setSettingsOldDIVinfoTable(String tableStatus) {


        System.out.println("  Que caracas OLD--- >>>> " + tableStatus);

        if (tableStatus.equalsIgnoreCase("removeTable")) {
    
            this.frmDIV.panelTableOldDIVInfo.remove(tableOldDIVinfo);

/*            
            this.frmDIV.btDIV_Save.setEnabled(true);
            this.frmDIV.btDIV_Cancel.setEnabled(true);
*/
        }

        if (tableStatus.equalsIgnoreCase("newTable")) {

            System.out.println("  Que caracas OLD nova--- >>>> " + tableStatus);
            
            setModelTableOldDIVinfo(setModelOldDIVinfoTable());
            setTableOldDIVinfo(tableOldDIVinfo);

      
            this.frmDIV.panelTableOldDIVInfo.add(tableOldDIVinfo);
            this.frmDIV.panelTableOldDIVInfo.setViewportView(tableOldDIVinfo);
        }

    }

    
    
    
    
    /*
     * 
     *  New DIV 
     * 
     */
    
    
    
    
    private DefaultTableModel setModelNewDIVinfoTable() {


        DefaultTableModel model = setModelSettingsNewDIVTable();

        tableNewDIVinfo = createTableNewDIVinfo(model);

        tableDefaultSettings(tableNewDIVinfo);

        String[] colWidths = newDIVTable("width");

        tableColumnsSettings(colWidths,tableNewDIVinfo);

        System.out.println ("   colocar model --- com picuinhices  --- > ");
        DefaultCellEditor defaultDsmtIDCellEditor = new CustomComboBoxEditor(this.frmDIV.comboDsmtID);
        defaultDsmtIDCellEditor.setClickCountToStart(2);
        tableNewDIVinfo.getColumnModel().getColumn(colIdDsmt).setCellEditor(defaultDsmtIDCellEditor);
        
        
        tableNewDIVinfo.getColumnModel().getColumn(tableNewDIVinfo.getColumnCount() - 1).setCellRenderer(checkBoxRenderer);
        

        tableNewDIVinfo.removeColumn(tableNewDIVinfo.getColumnModel().getColumn(0));
        tableNewDIVinfo.removeColumn(tableNewDIVinfo.getColumnModel().getColumn(0));
        tableNewDIVinfo.removeColumn(tableNewDIVinfo.getColumnModel().getColumn(0));

        return model;


    }

    private JTable createTableNewDIVinfo(DefaultTableModel model) {
         
        System.out.println(" crear tabela ... DIV >  " + this.frmDIV);
        
            
           final ArrayList year = new ArrayList();
            int yearNow = Integer.parseInt(dateAndTime.currYear());

       
           for (int j = yearNow; j > 2009; --j) {
             year.add(j);
            }
        
        
        
        JTable table = new JTable(model) {
            
            JComboBox month = new JComboBox(SetEnums.month.values());
            JComboBox trimester = new JComboBox(SetEnums.Trimester.values());
        
            JComboBox cbYear = new JComboBox(year.toArray());
            
            
            @Override
            public TableCellEditor getCellEditor(int row, int column) {

                int modelColumn = convertColumnIndexToModel(column);

                 System.out.println(" Aqui no model column --- > " + modelColumn);
                
                switch (modelColumn) {
                    case colSetPeriod:

                        String value = getModel().getValueAt(row, colPeriod).toString();

                        if (value.equalsIgnoreCase("Mensal")) {
                            DefaultCellEditor defaultCellEditor = new DefaultCellEditor(month);
                            return defaultCellEditor;
                        }

                        if (value.equalsIgnoreCase("Trimestral")) {
                            DefaultCellEditor defaultCellEditor = new DefaultCellEditor(trimester);
                            return defaultCellEditor;
                        }

                    break;
                        
                     case colSetYear:   
                            DefaultCellEditor defaultCellEditor = new DefaultCellEditor(cbYear);
                            return defaultCellEditor;
                   
                        
                    default:
                        System.out.println(" Aqui no default name --- > " );
                        return super.getCellEditor(row, column);
       
                }

                return null;

            }
            
  
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {

                int modelColumn = convertColumnIndexToModel(column);
                
                Component c = super.prepareRenderer(renderer, row, column);

                //  Color row based on a cell property
                c.setBackground(getBackground());
              //  c.setForeground(getForeground());

                if (!getModel().isCellEditable(row, modelColumn)) {
                    c.setBackground(Color.getHSBColor(110, 110, 110));
                  //  c.setForeground(Color.getHSBColor(90, 90, 90));
                }


                return c;
            }
        };

        return table;

    }

    private DefaultTableModel setModelSettingsNewDIVTable() {

        String[] colNames = newDIVTable("name");

        DefaultTableModel model = new DefaultTableModel(new Object[][]{},
                colNames) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                if (colIndex < colIdDsmt) {
                    return false;
                } else {
                    return true;
                }
           
        };
                };
  
        return model;

    }

    
    
    
    /*
     *  OLd DIV info Table
     * 
     */
    
    
      private DefaultTableModel setModelOldDIVinfoTable() {


        DefaultTableModel model = setModelSettingsOldDIVTable();

        tableOldDIVinfo = createTableOldDIVinfo(model);

        tableDefaultSettings(tableOldDIVinfo);

        String[] colWidths = oldDIVTable("width");

        tableColumnsSettings(colWidths,tableOldDIVinfo);
       
        tableOldDIVinfo.removeColumn(tableOldDIVinfo.getColumnModel().getColumn(0));

        return model;


    }
    
    
    
    
    private JTable createTableOldDIVinfo(DefaultTableModel model) {
         
             
        
        JTable table = new JTable(model) {
    
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {

                int modelColumn = convertColumnIndexToModel(column);
                
                Component c = super.prepareRenderer(renderer, row, column);

                //  Color row based on a cell property
                c.setBackground(getBackground());
              //  c.setForeground(getForeground());

                if (!getModel().isCellEditable(row, modelColumn)) {
                    c.setBackground(Color.getHSBColor(110, 110, 110));
                  //  c.setForeground(Color.getHSBColor(90, 90, 90));
                }


                return c;
            }
        };

        return table;

    }
    
    
    
    private DefaultTableModel setModelSettingsOldDIVTable() {

        String[] colNames = oldDIVTable("name");

        DefaultTableModel model = new DefaultTableModel(new Object[][]{},
                colNames) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;
        };
                };
  
        return model;

    }
    
    
    
    
    
    
    
    /* 
     * Commons for all the tables
     * 
     */
    
    
    
    /**
     *
     * @param table
     */
    private void tableDefaultSettings(JTable table) {


        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        table.getTableHeader().setReorderingAllowed(false);
        table.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        table.setFocusable(true);

        table.setRequestFocusEnabled(true);
        table.setUpdateSelectionOnSort(false);
        table.setDragEnabled(false);
        table.setRowSelectionAllowed(true);

    }

    private void tableColumnsSettings(String[] widths, JTable table) {


        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);

        for (int i = 0; i < widths.length; i++) {

            table.getColumnModel().getColumn(i).setPreferredWidth(Integer.parseInt(widths[i]));

            switch (i) {
                case colName:
                    table.getColumnModel().getColumn(i).setCellRenderer(leftRenderer);
                    break;
                default:
                    table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

        }

    }

    private String[] newDIVTable(String nameOrwidth) {

        if (nameOrwidth.equalsIgnoreCase("name")) {
            String[] names = {"pk_id", "pk_dsmt", "Periodicity", "Name", "Mec", "Category", "Dept", "Dsmt_ID", "Per",
                "Year", "Hp007", "Hp10", "Comments", "Note", "OK"};
            return names;
        } else {
            String[] widths = {"0", "0", "0", "70", "10", "10", "10", "10", "10", "10", "10", "10", "60", "85", "10"};
            return widths;
        }

    }

    private String[] oldDIVTable(String nameOrwidth) {

        if (nameOrwidth.equalsIgnoreCase("name")) {
            String[] names = {"pk_dose", "Dsmt", "Perd", "Year", "Hp007", "Hp10", "Inserted ", "Comments", "LastChange"};
            return names;
        } else {
            String[] widths = {"10", "10", "10", "10", "10", "10", "70", "80", "70"};
            return widths;
        }

    }
    
    
    
    
    
    
}


/*
 * 
 *  For new DIV info TAble
 * 
 * 
 */


class CustomComboBoxEditor extends DefaultCellEditor {

    // Declare a model that is used for adding the elements to the `ComboBox`
    
    private DefaultComboBoxModel model;
    private ArrayList<String> obtainedItemList;

    public CustomComboBoxEditor(final JComboBox combo) {

        super(combo);
  
        this.model = (DefaultComboBoxModel) ((JComboBox) getComponent()).getModel();
  
    }
    
    
   
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {


        obtainedItemList = DbPkIDs.getAllDsmtIDs().get(row);

        model.removeAllElements();

        for (int i = 0; i < obtainedItemList.size(); i++) {
            model.addElement(obtainedItemList.get(i));
        }

        
        return super.getTableCellEditorComponent(table, value, isSelected, row, column);


    }
    
    @Override
    public int getClickCountToStart() {
        
        return 2;
        
    }
    
}



