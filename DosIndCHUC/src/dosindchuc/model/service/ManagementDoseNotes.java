/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.service;

import dosindchuc.UI.swing.Help.ManagementButtons;
import dosindchuc.UI.swing.Help.ManagementClean;
import dosindchuc.UI.swing.Help.ManagementFields;
import dosindchuc.UI.swing.ManagementFrm;
import dosindchuc.globals.Tbl_dose_notes;
import dosindchuc.model.dao.DoseNotesHistDao;
import dosindchuc.model.dao.Dose_notesDao;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Dose_notes;
import dosindchuc.model.entities.Dose_notes_hist;
import dosindchuc.model.entities.Help.DateAndTime;
import dosindchuc.model.entities.Help.SetEnums;
import javax.swing.JTable;

/**
 *
 * @author ir
 */
public class ManagementDoseNotes {

    private ManagementFrm frmMan;
    private Dose_notesDao doseNotesdao;
    private DoseNotesHistDao doseNotesHistdao;
    private DbPkIDs dbPkIDs;
    private DateAndTime dateAndTime = new DateAndTime();
    private ManagementButtons setButtonsState;
    private ManagementClean setCleanState;
    private ManagementFields setFieldState;
    //  private ManagementSearch setDoseNoteInfo;
    private JTable table;
    //   private ManagementActionListener Listeners;

    public ManagementDoseNotes(ManagementFrm frmMan) {

        this.frmMan = frmMan;
        dbPkIDs = new DbPkIDs();
        doseNotesdao = new Dose_notesDao();
        doseNotesHistdao = new DoseNotesHistDao();
        setButtonsState = new ManagementButtons(this.frmMan);
        setCleanState = new ManagementClean(this.frmMan);
        setFieldState = new ManagementFields(this.frmMan);

    }

    /* ############################################### */
    /*                                                  */
    /*              dose Note  info                       */
    /*                                                  */
    /* ###############################################  */
    private Dose_notes getDoseNote(String newOrUpdate) {

        String currDateTime = dateAndTime.currDateTime();

        Dose_notes doseNote = new Dose_notes();

        table = this.frmMan.tableDoseInfo;

        int row = table.getSelectedRow();
        String pk_dose = dbPkIDs.getDose_id().get(row).toString();
        doseNote.setPk_dose(pk_dose);

        doseNote.setNote(this.frmMan.getTxtDoseNote().getText());
        String cbStatus = this.frmMan.cbDoseNoteStatus.getSelectedItem().toString();
        String cbAlertLevel = this.frmMan.cbDoseNoteLevel.getSelectedItem().toString();

        doseNote.setStatus(SetEnums.note_status.valueOf(cbStatus));
        doseNote.setAlert_level(SetEnums.note_alertlevel.valueOf(cbAlertLevel));

        if (newOrUpdate.equalsIgnoreCase("new")) {

            doseNote.setTimestamp(currDateTime);
            doseNote.setLastchange(currDateTime);
            doseNote.setAlert_level_timestamp(currDateTime);
            doseNote.setStatus_timestamp(currDateTime);

        } else {

            doseNote.setTimestamp(this.frmMan.getTxtDoseNotesDateCreated().getText());
            doseNote.setStatus_timestamp(this.frmMan.getTxtDoseNoteStatusDate().getText());
            if (!cbStatus.matches(dbPkIDs.getDoseNotes_id().get(0, 3).toString())) {
                doseNote.setStatus_timestamp(currDateTime);
            }

            doseNote.setAlert_level_timestamp(this.frmMan.getTxtDoseNoteLevelDate().getText());
            if (!cbAlertLevel.matches(dbPkIDs.getDoseNotes_id().get(0, 2).toString())) {
                doseNote.setAlert_level_timestamp(currDateTime);
            }

        }

        return doseNote;

    }

    public void newDoseNote() {

        setCleanState.cleanDoseNotes();
        setFieldState.setNewDoseNotes(true);
        setButtonsState.setNewDoseNoteBts(true);

        this.frmMan.tableDoseInfo.setEnabled(false);

        this.frmMan.getTxtInfoAction().setText("Inserting a New dose note");

    }

    // insert into the database 
    public void saveNewDoseNote() {

        Dose_notes doseNote = getDoseNote("new");

        String id = doseNotesdao.insertDoseNote(doseNote);

        this.frmMan.getTxtInfoAction().setText("Dose note to dose_id = " + doseNote.getPk_dose() + " saved into database");

        recordHist(doseNote, id, -1);
        // actualiza info
        this.frmMan.tableDoseInfo.setEnabled(true);
        fillDoseNoteInfo();

    }

    /*
     *
     */
    public void updateDoseNote() {

        setButtonsState.setUpdateDoseNoteBts(true);
        setFieldState.setUpdateDoseNotes(true);

        this.frmMan.tableDoseInfo.setEnabled(false);
        this.frmMan.getTxtInfoAction().setText("Updating Dose note info");

    }

    public void saveUpdateDoseNote() {

        Dose_notes doseNote = getDoseNote("update");

        String doseNote_id = dbPkIDs.getDoseNotes_id().get(0, 0).toString();

        doseNotesdao.updateDoseNote(doseNote, doseNote_id);

        this.frmMan.getTxtInfoAction().setText("Dose note updated into database");

        recordHist(doseNote, doseNote_id, 0);

        // actualiza info
        this.frmMan.tableDoseInfo.setEnabled(true);
        fillDoseNoteInfo();

    }

    /* 
     *history
     */
    public void cancelDoseNote() {

        this.frmMan.getTxtInfoAction().setText("Dose note action cancelled");
        setButtonsState.setAllCancelBts();
        setCleanState.cleanDoseNotes();
        setButtonsState.setAllDoseNoteBtsInit(false);
        this.frmMan.tableDoseInfo.setEnabled(true);

    }

    public void fillDoseNoteInfo() {

        setCleanState.cleanDoseNotes();
        setButtonsState.setAllDoseNoteBtsInit(false);

    }

    /* 
     * history
     * 
     */
    private void recordHist(Dose_notes doseNote, String doseNote_id, int doseRow) {


        System.out.println(" .... -> dsmtRow -- > " + doseRow);

        String value[] = new String[]{doseNote.getNote(), doseNote.getStatus().toString(), doseNote.getAlert_level().toString()};

        switch (doseRow) {

            case -1:
                for (int i = 0; i < Tbl_dose_notes.nrHist; i++) {
                    String id_change = Tbl_dose_notes.parmHist[i];
                    saveDoseNoteHist(doseNote_id, id_change, value[i]);
                }
                break;

            default:

                for (int i = 0; i < Tbl_dose_notes.nrHist; i++) {

                    if (!dbPkIDs.getDoseNotes_id().contains(value[i])) {
                        String id_change = Tbl_dose_notes.parmHist[i];
                        saveDoseNoteHist(doseNote_id, id_change, value[i]);
                    }

                }

                break;

        }
    }

    private void saveDoseNoteHist(String doseNote_id, String id_change, String value) {

        Dose_notes_hist doseNoteHist = new Dose_notes_hist();

        doseNoteHist.setPk_dose_notes(doseNote_id);
        doseNoteHist.setId_change(id_change);
        doseNoteHist.setValue(value);

        doseNotesHistdao.insertDoseNoteHist(doseNoteHist);

    }
}
