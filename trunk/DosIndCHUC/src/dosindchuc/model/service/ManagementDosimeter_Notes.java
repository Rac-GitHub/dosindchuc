/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.service;

import dosindchuc.UI.swing.Help.ManagementButtons;
import dosindchuc.UI.swing.Help.ManagementClean;
import dosindchuc.UI.swing.Help.ManagementFields;
import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.model.dao.Dosimeter_notesDao;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Dsmt_notes;
import dosindchuc.model.entities.Help.DateAndTime;
import dosindchuc.model.entities.Help.SetEnums;
import javax.swing.JTable;

/**
 *
 * @author ir
 */
public class ManagementDosimeter_Notes {

    private ManagementFrm frmMan;
    private Dosimeter_notesDao dsmtNotesdao;
    private DbPkIDs dbPkIDs;
    private DateAndTime dateAndTime = new DateAndTime();
    private ManagementButtons setButtonsState;
    private ManagementClean setCleanState;
    private ManagementFields setFieldState;
    private JTable table;

    public ManagementDosimeter_Notes(ManagementFrm frmMan) {

        this.frmMan = frmMan;
        dbPkIDs = new DbPkIDs();
        dsmtNotesdao = new Dosimeter_notesDao();
        setButtonsState = new ManagementButtons(this.frmMan);
        setCleanState = new ManagementClean(this.frmMan);
        setFieldState = new ManagementFields(this.frmMan);

    }

    /* ############################################### */
    /*                                                  */
    /*              dose Note  info                       */
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
}
