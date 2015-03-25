/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.swing.Help;

import dosindchuc.UI.swing.ManagementFrm;

    
/**
 *
 * @author ir
 */
public class ManagementCommons {
    
    
    private final ManagementFrm frmMan;
    private ManagementFields setFieldsState;
    private ManagementButtons setButtonsState;
 

    public ManagementCommons(ManagementFrm frmMan) {

        this.frmMan = frmMan;
        setFieldsState = new ManagementFields(this.frmMan);
        setButtonsState = new ManagementButtons(this.frmMan);
     
    }
    
    
    /**
     *
     * @param searchAll
     */
    public void searchAllEditInfoPanel(boolean searchAll) {
                        
        setFieldsState.setAllSearchEdit(searchAll);
        setButtonsState.setAllSearchBts(searchAll);
        this.frmMan.searchTable.setEnabled(searchAll);

    }  
    
    
    
}
