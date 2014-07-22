/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.swing.Help;

import dosindchuc.UI.swing.MainFrm;
import dosindchuc.model.entities.Help.SetEnums;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class AlertTableInMainFrm {

    public static JTable NoteTable;
    public static DefaultTableModel modelAlertTable;
    private MainFrm frmMain;
    private CheckBoxRenderer checkBoxRenderer;

    public AlertTableInMainFrm(MainFrm frmMain) {

        this.frmMain = frmMain;
        checkBoxRenderer = new CheckBoxRenderer();

    }

    public JTable getNoteTable() {
        return NoteTable;
    }

    public void setNoteTable(JTable NoteTable) {
        AlertTableInMainFrm.NoteTable = NoteTable;
    }

    public DefaultTableModel getModelAlertTable() {
        return modelAlertTable;
    }

    public void setModelAlertTable(DefaultTableModel modelAlertTable) {
        AlertTableInMainFrm.modelAlertTable = modelAlertTable;
    }

    public void setSettingsAlertTable(String tableStatus) {

        if (tableStatus.equalsIgnoreCase("toedit")) {

            this.frmMain.btNoteSave.setEnabled(true);
            this.frmMain.btNoteCancel.setEnabled(true);

        }

        if (tableStatus.equalsIgnoreCase("newToActionListeners")) {

            setModelAlertTable(setModelForAlertTable());
            setNoteTable(NoteTable);
            this.frmMain.jScrollPane1.add(NoteTable);
            this.frmMain.jScrollPane1.setViewportView(NoteTable);
        }

    }

    private DefaultTableModel setModelForAlertTable() {

        DefaultTableModel model = setModelSettingsAlertTable();

        NoteTable = createTableWithrowRendering(model);

        tableDefaultSettings();

        String[] colWidths = noteAlertTable("width");

        tableColumnsSettings(colWidths);

        JComboBox cbLevel = new JComboBox(SetEnums.note_alertlevel.values());
        JComboBox cbStatus = new JComboBox(SetEnums.note_status.values());

        NoteTable.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(cbLevel));
        NoteTable.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(cbStatus));
        NoteTable.getColumnModel().getColumn(9).setCellEditor(new DefaultCellEditor(new JCheckBox()));

        NoteTable.getColumnModel().getColumn(9).setCellRenderer(checkBoxRenderer);

        //         NoteTable.setRowSorter(new TableRowSorter(model));

        NoteTable.removeColumn(NoteTable.getColumnModel().getColumn(0));

        return model;


    }

    private JTable createTableWithrowRendering(DefaultTableModel model) {

        JTable table = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);

                //  Color row based on a cell value
                c.setBackground(getBackground());

                if (!isRowSelected(row)) {
                    int modelRow = convertRowIndexToModel(row);
                    String type = getModel().getValueAt(modelRow, 2).toString();

                    if ("I".equals(type)) {
                        c.setBackground(Color.MAGENTA);
                    }
                    if ("C".equals(type)) {
                        c.setBackground(Color.RED);
                    }
                    if ("MI".equals(type)) {
                        c.setBackground(Color.ORANGE);
                    }
                    if ("N".equals(type)) {
                        c.setBackground(Color.gray);
                    }
                }


                return c;
            }
        };

        return table;


    }

    private DefaultTableModel setModelSettingsAlertTable() {

        String[] colNames = noteAlertTable("name");

        DefaultTableModel model = new DefaultTableModel(new Object[][]{},
                colNames) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                switch (colIndex) {
                    case 2:                   // ONLY 4TH COL IS EDITABLE
                        return true;
                    case 6:                   // ONLY 4TH COL IS EDITABLE
                        return true;
                    case 7:                   // ONLY 4TH COL IS EDITABLE
                        return true;
                    case 9:                   // ONLY 4TH COL IS EDITABLE
                        return true;
                    default:
                        return false;
                }

            }
        };


        return model;

    }

    /**
     *
     * @param table
     */
    private void tableDefaultSettings() {


        NoteTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        NoteTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        NoteTable.getTableHeader().setReorderingAllowed(false);
        NoteTable.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        NoteTable.setFocusable(true);

        NoteTable.setRequestFocusEnabled(true);
        NoteTable.setUpdateSelectionOnSort(false);
        NoteTable.setDragEnabled(false);
        NoteTable.setRowSelectionAllowed(true);

    }

    private void tableColumnsSettings(String[] widths) {


        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < widths.length; i++) {

            NoteTable.getColumnModel().getColumn(i).setPreferredWidth(Integer.parseInt(widths[i]));
            NoteTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);


        }


    }

    private String[] noteAlertTable(String nameOrwidth) {

        if (nameOrwidth.equalsIgnoreCase("name")) {
            String[] names = {"id", "Type", "Level", "Mec", "Name", "Department", "Note", "Status", "LastChanged", "Save"};
            return names;
        } else {
            String[] widths = {"5", "15", "15", "15", "55", "30", "80", "10", "80", "10"};
            return widths;
        }
    }
}
