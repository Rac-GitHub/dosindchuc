/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.service;

import dosindchuc.UI.swing.Help.ManagementButtons;
import dosindchuc.UI.swing.Help.ManagementClean;
import dosindchuc.UI.swing.Help.ManagementFields;
import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.globals.Tbl_dsmt_notes;
import dosindchuc.model.dao.DsmtNoteHistDao;
import dosindchuc.model.dao.DsmtNotesDao;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Dsmt_notes;
import dosindchuc.model.entities.Dsmt_notes_hist;
import dosindchuc.model.entities.Help.DateAndTime;
import dosindchuc.model.entities.Help.SetEnums;
import javax.swing.JTable;

/**
 *
 * @author ir
 */
public class ManagementDsmtNotes {

    private ManagementFrm frmMan;
    private DsmtNotesDao dsmtNotesdao;
    private DsmtNoteHistDao dsmtNoteHistdao;
    private DbPkIDs dbPkIDs;
    private DateAndTime dateAndTime = new DateAndTime();
    private ManagementButtons setButtonsState;
    private ManagementClean setCleanState;
    private ManagementFields setFieldState;
    private JTable table;

    public ManagementDsmtNotes(ManagementFrm frmMan) {

        this.frmMan = frmMan;
        dbPkIDs = new DbPkIDs();
        dsmtNotesdao = new DsmtNotesDao();
        dsmtNoteHistdao = new DsmtNoteHistDao();
        setButtonsState = new ManagementButtons(this.frmMan);
        setCleanState = new ManagementClean(this.frmMan);
        setFieldState = new ManagementFields(this.frmMan);

    }

    /* ############################################### */
    /*                                                  */
    /*              Dsmt NOtes                       */
    /*                                                  */
    /* ###############################################  */
    private Dsmt_notes getDsmtNote(String newOrUpdate) {

        String currDateTime = dateAndTime.currDateTime();

        Dsmt_notes dsmtNote = new Dsmt_notes();

        table = this.frmMan.tableDosimeterInfo;

        int row = table.getSelectedRow();
        String pk_dsmt = dbPkIDs.getDsmt_id().get(row)[0].toString();
        dsmtNote.setPk_dsmt(pk_dsmt);

        dsmtNote.setNote(this.frmMan.getTxtDosimeterNote().getText());
        String cbStatus = this.frmMan.cbDosimeterNotesStatus.getSelectedItem().toString();
        String cbAlertLevel = this.frmMan.cbDosimeterNotesAlert.getSelectedItem().toString();

        dsmtNote.setStatus(SetEnums.note_status.valueOf(cbStatus));
        dsmtNote.setAlert_level(SetEnums.note_alertlevel.valueOf(cbAlertLevel));

        if (newOrUpdate.equalsIgnoreCase("new")) {

            dsmtNote.setTimestamp(currDateTime);
            dsmtNote.setLastchange(currDateTime);
            dsmtNote.setAlert_level_timestamp(currDateTime);
            dsmtNote.setStatus_timestamp(currDateTime);

        } else {

            dsmtNote.setTimestamp(this.frmMan.getTxtDosimeterNotesDateCreated().getText());

            dsmtNote.setStatus_timestamp(this.frmMan.getTxtDosimeterNoteStatusDate().getText());
            if (!cbStatus.matches(dbPkIDs.getDsmtNotes_id().get(0, 3).toString())) {
                dsmtNote.setStatus_timestamp(currDateTime);
            }

            dsmtNote.setAlert_level_timestamp(this.frmMan.getTxtDosimeterNoteAlertdate().getText());
            if (!cbAlertLevel.matches(dbPkIDs.getDsmtNotes_id().get(0, 2).toString())) {
                dsmtNote.setAlert_level_timestamp(currDateTime);
            }

        }

        return dsmtNote;

    }

    public void newDsmtNote() {

        setCleanState.cleanDsmtNotes();
        setFieldState.setNewDsmtNotes(true);
        setButtonsState.setNewDsmtNoteBts(true);

        this.frmMan.tableDosimeterInfo.setEnabled(false);
        this.frmMan.getTxtInfoAction().setText("Inserting a New dosimeter note");

    }

    // insert into the database 
    public void saveNewDsmtNote() {

        Dsmt_notes dsmtNote = getDsmtNote("new");

        String id = dsmtNotesdao.insertDsmtNote(dsmtNote);

        this.frmMan.getTxtInfoAction().setText("Dosimeter note to dsmt_id = " + dsmtNote.getPk_dsmt() + " saved into database");

        recordHist(dsmtNote, id, -1);

        // actualiza info
        this.frmMan.tableDosimeterInfo.setEnabled(true);
        fillDsmtNoteInfo();

    }

    /**
     *
     */
    public void updateDsmtNote() {

        setButtonsState.setUpdateDsmtNoteBts(true);
        setFieldState.setUpdateDsmtNotes(true);

        this.frmMan.tableDosimeterInfo.setEnabled(false);
        this.frmMan.getTxtInfoAction().setText("Updating Dosimeter note info");

    }

    public void saveUpdateDsmtNote() {

        Dsmt_notes dsmtNote = getDsmtNote("update");

        String dsmtNote_id = dbPkIDs.getDsmtNotes_id().get(0, 0).toString();

        dsmtNotesdao.updateDsmtNote(dsmtNote, dsmtNote_id);

        this.frmMan.getTxtInfoAction().setText("Dosimeter note updated into database");

        recordHist(dsmtNote, dsmtNote_id, 0);

        // actualiza info
        this.frmMan.tableDosimeterInfo.setEnabled(true);
        fillDsmtNoteInfo();

    }

    public void cancelDsmtNote() {

        this.frmMan.getTxtInfoAction().setText("Dosimeter note action cancelled");
        setButtonsState.setAllCancelBts();
        setCleanState.cleanDsmtNotes();
        setButtonsState.setAllDsmtNoteBtsInit(false);
        this.frmMan.tableDosimeterInfo.setEnabled(true);

    }

    public void fillDsmtNoteInfo() {

        setCleanState.cleanDsmtNotes();
        setButtonsState.setAllDsmtNoteBtsInit(false);

    }

    /* 
     * history
     * 
     */
   

    private void recordHist(Dsmt_notes dsmtNote, String dsmtNote_id, int dsmtRow) {


        System.out.println(" .... -> dsmtRow -- > " + dsmtRow);

        String value[] = new String[]{dsmtNote.getNote(), dsmtNote.getStatus().toString(), dsmtNote.getAlert_level().toString()};

        switch (dsmtRow) {

            case -1:
                for (int i = 0; i < Tbl_dsmt_notes.nrHist; i++) {
                    String id_change = Tbl_dsmt_notes.parmHist[i];
                    saveDsmtNoteHist(dsmtNote_id, id_change, value[i]);
                }
                break;

            default:

                for (int i = 0; i < Tbl_dsmt_notes.nrHist; i++) {

                    if (!dbPkIDs.getDsmtNotes_id().contains(value[i])) {
                        String id_change = Tbl_dsmt_notes.parmHist[i];
                        saveDsmtNoteHist(dsmtNote_id, id_change, value[i]);
                    }

                }

                break;

        }
    }
    

    private void saveDsmtNoteHist(String dsmtNote_id, String id_change, String value) {

        Dsmt_notes_hist dsmtNoteHist = new Dsmt_notes_hist();

        dsmtNoteHist.setPk_dsmt_notes(dsmtNote_id);
        dsmtNoteHist.setId_change(id_change);
        dsmtNoteHist.setValue(value);

        dsmtNoteHistdao.insertDsmtNoteHist(dsmtNoteHist);

    }
    
}
