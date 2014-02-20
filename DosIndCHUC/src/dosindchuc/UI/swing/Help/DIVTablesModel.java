/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.swing.Help;

import dosindchuc.UI.swing.DIVFrm;
import dosindchuc.model.entities.Help.SetEnums;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;




public class DIVTablesModel  {
   
    
        public static JTable tableNewDIVinfo;
        public static DefaultTableModel modelTableNewDIVinfo;
  
        private DIVFrm frmDIV;
        private CheckBoxRenderer checkBoxRenderer;
        
    
      
	public DIVTablesModel(DIVFrm frmDIV) {
            
            this.frmDIV = frmDIV;
            checkBoxRenderer = new CheckBoxRenderer();
                
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

 
    
    
        
        public void  setSettingsNewDIVinfoTable (String tableStatus) {
            
            
            System.out.println("  Que caracas 1--- >>>> " +  tableStatus);
            
            if (tableStatus.equalsIgnoreCase("toedit")) {
             
                this.frmDIV.btDIV_Save.setEnabled(true);
                this.frmDIV.btDIV_Cancel.setEnabled(true);
                
            }
            
            if (tableStatus.equalsIgnoreCase("newToActionListeners")) {
                
                setModelTableNewDIVinfo(setModelNewDIVinfoTable());
                setTableNewDIVinfo(tableNewDIVinfo);
           
                System.out.println("  Que caracas -Model-- >>>> " +  getModelTableNewDIVinfo());
                System.out.println("  Que caracas -Table -- >>>> " +  getTableNewDIVinfo());
                System.out.println(" Aqui ..... 1111 -> " +  this.frmDIV);
                this.frmDIV.panelTableNewDIVinfo.add(tableNewDIVinfo);
                this.frmDIV.panelTableNewDIVinfo.setViewportView( tableNewDIVinfo );
            }
            
          }
                
 
        
        
        
        
       private DefaultTableModel setModelNewDIVinfoTable () {
             
           DefaultTableModel model = setModelSettingsNewDIVTable();
           
           tableNewDIVinfo = createTableNewDIVinfo(model);

           tableDefaultSettings();

           String[] colWidths = newDIVTable("width");

           tableColumnsSettings(colWidths);

           
           tableNewDIVinfo.getColumnModel().getColumn(tableNewDIVinfo.getColumnCount() - 1).setCellRenderer(checkBoxRenderer);

 
           tableNewDIVinfo.removeColumn(tableNewDIVinfo.getColumnModel().getColumn(0));
           tableNewDIVinfo.removeColumn(tableNewDIVinfo.getColumnModel().getColumn(0));
           tableNewDIVinfo.removeColumn(tableNewDIVinfo.getColumnModel().getColumn(0));
     
           return model;

                
    }
        
        
          
          
	private JTable createTableNewDIVinfo (DefaultTableModel model) {


        JTable table = new JTable(model) {
            
            final int colPeriod = 2;
            final int colChangePeriod = 8;
            final JComboBox month = new JComboBox(SetEnums.month.values());
            final JComboBox trimester = new JComboBox(SetEnums.Trimester.values());

            @Override
            public TableCellEditor getCellEditor(int row, int column) {

                int modelColumn = convertColumnIndexToModel(column);

                if (modelColumn == colChangePeriod) {

                    String value = getModel().getValueAt(row, colPeriod).toString();

                    if (value.equalsIgnoreCase("Mensal")) {
                        DefaultCellEditor defaultCellEditor = new DefaultCellEditor(month);
                        return defaultCellEditor;
                    }

                    DefaultCellEditor defaultCellEditor = new DefaultCellEditor(trimester);
                    return defaultCellEditor;


                } else {
                    return super.getCellEditor(row, column);
                }
                
            }
            
        
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                
                Component c = super.prepareRenderer(renderer, row, column);

                //  Color row based on a cell property
                c.setBackground(getBackground());

                if ( ! getModel().isCellEditable(row, column)) {
                        c.setBackground(Color.getHSBColor(110, 110, 55));
                }


                return c;
            }
        
            
        };

        return table;

    }

        
        
        
        
    private DefaultTableModel setModelSettingsNewDIVTable() {
        
        String[] colNames = newDIVTable("name");
        
        DefaultTableModel model = new DefaultTableModel(new Object [][] {},
                colNames
                ){
                    
                   @Override
                   public boolean isCellEditable(int rowIndex, int colIndex) {
                        if ( colIndex < 8 ) {
                                return false;
                            } else {
                                return true;
                            }
                    }
                   
                };
  
      
        return model;
        
    }

    
       
    /**
     *
     * @param table
     */
    private void tableDefaultSettings () {
        
        
        tableNewDIVinfo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableNewDIVinfo.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tableNewDIVinfo.getTableHeader().setReorderingAllowed(false);
        tableNewDIVinfo.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        tableNewDIVinfo.setFocusable(true);
        
        tableNewDIVinfo.setRequestFocusEnabled(true);
        tableNewDIVinfo.setUpdateSelectionOnSort(false);
        tableNewDIVinfo.setDragEnabled(false);
        tableNewDIVinfo.setRowSelectionAllowed(true);
   
    }
     
    
    
    private void tableColumnsSettings (String [] widths) {
        
        final int colName = 3;
  
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
   
        for (int i = 0; i < widths.length; i++) {

            tableNewDIVinfo.getColumnModel().getColumn(i).setPreferredWidth(Integer.parseInt(widths[i]));
            
            switch(i){
                case colName:
                    tableNewDIVinfo.getColumnModel().getColumn(i).setCellRenderer(leftRenderer);
                    break;
                default:
                    tableNewDIVinfo.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        
     }
        
    }  
    
        
    private String [] newDIVTable (String nameOrwidth) {
    
         if (nameOrwidth.equalsIgnoreCase("name")) {
            String[] names = { "pk_id", "pk_dsmt", "Periodicity", "Name", "Mec", "Category", "Dept", "Dsmt_ID", "Per", 
                "Year", "Hp007", "Hp10", "Comments", "Note", "OK" };
            return names;
        } else {
            String[] widths = { "0", "0", "0", "55", "10", "10", "10", "10", "10", "10", "10", "10", "60", "85", "10" };
            return widths;
        }
        
    }
    
        
    
    
    
    private String [] oldDIVTable (String nameOrwidth) {
   
        if (nameOrwidth.equalsIgnoreCase("name")) {
            String[] names = {"Dsmt", "Year", "Trimester", "Month", "Hp007", "Hp10", "Inserted ", "Comments", "LastChange"};
            return names;
        } else {
            String[] widths = {"10", "10", "35", "20", "10", "10", "70", "80", "70"};
            return widths;
        }
        
    }
    
     
 
    
	
}
