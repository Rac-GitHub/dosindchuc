/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.UI.swing.Help;

import dosindchuc.UI.swing.DIVFrm;

/**
 *
 * @author ir
 */
public class DIVButtons {
    
    
   private DIVFrm frmDIV;

    public DIVButtons(DIVFrm frmDIV) {

        this.frmDIV = frmDIV;

    }

    // worker     
    public void setAllDIVBtsInit(boolean divBts) {

        this.frmDIV.btDIV_Search.setEnabled(divBts);
        this.frmDIV.btDIV_Clean.setEnabled(divBts);
        this.frmDIV.btDIV_Save.setEnabled(!divBts);
        this.frmDIV.btDIV_Cancel.setEnabled(!divBts);

    }
    
    public void setAllDIVBtsSaveCancel(boolean divBts) {

        this.frmDIV.btDIV_Save.setEnabled(divBts);
        this.frmDIV.btDIV_Cancel.setEnabled(divBts);

    }
    
    
    
}
