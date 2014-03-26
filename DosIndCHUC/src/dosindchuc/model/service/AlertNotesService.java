/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.service;

import dosindchuc.UI.swing.Help.AlertTableInMainFrm;
import dosindchuc.UI.swing.MainFrm;
import dosindchuc.model.dao.AlertNotesDao;
import dosindchuc.model.entities.AlertNotes;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Help.DateAndTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ir
 */
public class AlertNotesService {

    private MainFrm frmMain;
    private AlertNotesDao alertNotesDao;
    private DbPkIDs dbPkIDs;
    private DateAndTime dateAndTime = new DateAndTime();
    private List<AlertNotes> alertNotes;
    private DefaultTableModel model;
    private AlertTableInMainFrm tableNote;

    public AlertNotesService(MainFrm frmMain) {

        this.frmMain = frmMain;
        dbPkIDs = new DbPkIDs();
        tableNote = new AlertTableInMainFrm(this.frmMain);

        alertNotesDao = new AlertNotesDao();

    }

    public void fillAlertNotesTable() {


        ArrayList<Object[]> alertNoteInfo = new ArrayList();

        List<AlertNotes> alertNoteList = new ArrayList<>();

        alertNotes = alertNotesDao.getDoseAlerNotes();

        int nResultsDose = alertNotes.size();

        if (nResultsDose > 0) {

            for (int i = 0; i < nResultsDose; i++) {

                AlertNotes alertNote = alertNotes.get(i);
                alertNoteList.add(alertNote);

            }

        }


        alertNotes.clear();

        alertNotes = alertNotesDao.getDsmtAlerNotes();

        int nResultsDsmt = alertNotes.size();

        if (nResultsDsmt > 0) {

            for (int i = 0; i < nResultsDsmt; i++) {
                AlertNotes alertNote = alertNotes.get(i);
                alertNoteList.add(alertNote);
            }


            if (!(nResultsDose > 0) && !(nResultsDsmt > 0)) {

                return;
            }


            // sort Collection

            Collections.sort(alertNoteList, Collections.reverseOrder(new sortArrayList()));

            // display sorted info on table 

            model = setTableModel("toedit");

            for (AlertNotes alN : alertNoteList) {

                Object newAlInfo[] = new Object[]{alN.getPk_notes(), alN.getNotesType(), alN.getNotesLevel(),
                    alN.getNotesMec(), alN.getNotesName(), alN.getNotesDept(), alN.getNotesNote(),
                    alN.getNotesStatus(), alN.getNotesDate(), Boolean.FALSE};

                model.addRow(newAlInfo);

                alertNoteInfo.add(newAlInfo);

            }

            // save to use later 

            dbPkIDs.setAlertNote(alertNoteInfo);

        }
    }

    public void saveAlertNotesTable() {

        ArrayList<Object[]> alertNoteInfo;
        alertNoteInfo = dbPkIDs.getAlertNote();

        ArrayList<Integer> removedRows = new ArrayList<>();

        JTable table = tableNote.getNoteTable();
        DefaultTableModel tmodel = tableNote.getModelAlertTable();

        int nAlertNotes = table.getRowCount();

        for (int i = 0; i < nAlertNotes; i++) {

            boolean isToSave = table.getValueAt(i, 8).equals(true);

            if (isToSave) {

                alertNotesDao.updateAlertNote(i, table);

                table.setValueAt(dateAndTime.currDateTime().split("\\s+")[0], i, 7);
                table.setValueAt(Boolean.FALSE, i, 8);

                if (table.getValueAt(i, 6).toString().matches("C")) {

                    removedRows.add(i);

                }
            }
        }


        int nR = 0;
        for (int i = 0; i < removedRows.size(); i++) {

            int rowR = removedRows.get(i);

            tmodel.removeRow(rowR - nR);
            alertNoteInfo.remove(rowR - nR);

            nR++;

        }


        dbPkIDs.setAlertNote(alertNoteInfo);

    }

    public void cancelAlertNotesTable() {


        DefaultTableModel tmodel = tableNote.getModelAlertTable();

        int nRow = tmodel.getRowCount();

        for (int i = 0; i < nRow; i++) {
            tmodel.removeRow(0);
        }

        displayAlertNotesTable(tmodel);

    }

    private DefaultTableModel setTableModel(String type) {

        tableNote.setSettingsAlertTable(type);
        model = tableNote.getModelAlertTable();
        return model;

    }

    private void displayAlertNotesTable(DefaultTableModel model) {

        ArrayList<Object[]> alertNoteInfo;

        alertNoteInfo = dbPkIDs.getAlertNote();

        for (int i = 0; i < alertNoteInfo.size(); i++) {

            Object alertNote[] = alertNoteInfo.get(i);

            model.addRow(alertNote);

        }


    }
}

// class to sort an ArrayList --- AlertNotes based on a column value
class sortArrayList implements Comparator<AlertNotes> {

    //Sorting using Anonymous inner class type
    @Override
    public int compare(AlertNotes alN, AlertNotes alN1) {
        return alN.getNotesLevel().compareTo(alN1.getNotesLevel());
    }
}
