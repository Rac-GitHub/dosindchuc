/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.swing.Help;

import java.awt.Color;
import java.awt.Component;
import java.util.Hashtable;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author ir
 */
public class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {
    
    
    CheckBoxRenderer() {
        setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        setSelected((value != null && ((Boolean) value).booleanValue()));
        return this;
        
    }
 
}
