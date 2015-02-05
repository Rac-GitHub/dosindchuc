/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.service;

import dosindchuc.UI.swing.DIVFrm;
import dosindchuc.UI.swing.Help.DIVButtons;
import dosindchuc.UI.swing.Help.DIVTablesModel;
import dosindchuc.model.dao.DIVDao;
import dosindchuc.model.entities.DIVOldInfo;
import dosindchuc.model.entities.DIVinfo;
import dosindchuc.model.entities.DIVnotes;
import dosindchuc.model.entities.DbPkIDs;
import dosindchuc.model.entities.Help.DateAndTime;
import dosindchuc.model.entities.Help.SetEnums;
import dosindchuc.model.service.Help.YearMonthAndTrimester;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author ir
 */
public class DIVService {

    private int colOK = 16;
    private DIVFrm frmDIV;
    private DIVDao divdao;
    private DbPkIDs dbPkIDs;
    private List<DIVinfo> divInfo;
    private List<DIVOldInfo> oldDivInfo;
    private List<DIVnotes> notesDiv;
    private DIVinfo divINFO;
    private YearMonthAndTrimester yearMonthTrimester;
    private DIVTablesModel divTableModel;
    private DefaultTableModel model;
    private DateAndTime dateAndTime = new DateAndTime();
    private ArrayList<ArrayList<String>> allDsmtIDs = new ArrayList<>();
    private ArrayList<ArrayList<String>> allDsmtPeriod = new ArrayList<>();
    private DIVButtons setButtonsState;

    public DIVService(final DIVFrm frmDIV) {

        this.frmDIV = frmDIV;
        divTableModel = new DIVTablesModel(this.frmDIV);
        divdao = new DIVDao();
        dbPkIDs = new DbPkIDs();
        setButtonsState = new DIVButtons(this.frmDIV);
        divINFO = new DIVinfo();


    }

    public void saveDIVs() {

        divInfo.clear();

        List<Integer> rowToBeRemove = new ArrayList<>();

        DefaultTableModel tmodel = divTableModel.getModelTableNewDIVinfo();

        int newDivs = tmodel.getRowCount();

        for (int i = 0; i < newDivs; i++) {

            boolean isToSave = tmodel.getValueAt(i, colOK).equals(true);

            if (isToSave) {

                divINFO.setPk_id(tmodel.getValueAt(i, 0).toString());
                divINFO.setPk_dsmt(tmodel.getValueAt(i, 1).toString());
                divINFO.setPeriodicity(SetEnums.dsmt_periodicity.valueOf(tmodel.getValueAt(i, 2).toString()));
                divINFO.setYear(tmodel.getValueAt(i, 9).toString());
                divINFO.setPerd(tmodel.getValueAt(i, 8).toString());
                divINFO.setHp007(tmodel.getValueAt(i, 10).toString());
                divINFO.setHp10(tmodel.getValueAt(i, 11).toString());

                if (!(tmodel.getValueAt(i, 12) == null)) {
                    divINFO.setComments(tmodel.getValueAt(i, 12).toString());
                }

                if (!(tmodel.getValueAt(i, 13) == null)) {
                    divINFO.setDose_note(tmodel.getValueAt(i, 13).toString());
                }

                divINFO.setTimestamp(dateAndTime.currDateTime());

                divINFO.setNoteAlertlevel(SetEnums.note_alertlevel.valueOf(tmodel.getValueAt(i, 14).toString()));
                divINFO.setNoteStatus(SetEnums.note_status.valueOf(tmodel.getValueAt(i, 15).toString()));

                divInfo.add(divINFO);

                String pk_dose = divdao.saveDIV_doseInfo(divInfo);

                if (!(divINFO.getDose_note() == null)) {
                    divdao.saveDIV_doseNote(divInfo, pk_dose);
                }

                rowToBeRemove.add(i);

            }

        }

        int nRow = 0;
        for (int i = 0; i < rowToBeRemove.size(); i++) {

            int row = rowToBeRemove.get(i) - nRow;
            tmodel.removeRow(row);

            nRow = nRow + 1;

        }


    }

    public void searchDIVInfo() {

        frmDIV.txtWorkerNameDIV.setText("");
        clearNewDIVTables();
        clearOldDIVTables();
        clearNotesDIVTables();

        String department = frmDIV.getCbDIV_Department().getSelectedItem().toString();
        String dsmt_id = frmDIV.txtDIV_dsmtID.getText();
        String name = frmDIV.getTxtDIV_Name().getText();
        String category = frmDIV.getCbDIV_Category().getSelectedItem().toString();
        
        
        
        System.out.println("searchDIVInfo +++ ->  dep " + department + " dsmt_id " + dsmt_id + " name " +  name + " category " + category);

        divInfo = divdao.getDIVInfo(name, department, category, dsmt_id);

        removeDuplicates(divInfo);

        if (!(divInfo.size() > 0)) {
            removeDIVTables();
            return;
        }

        int iN = 0;
        for (DIVinfo div : divInfo) {

            Object newDIVInfo[] = new Object[]{div.getPk_id(), div.getName()};

            String[] splitedName = newDIVInfo[1].toString().split("\\s+");
            div.setName(splitedName[0] + " " + splitedName[splitedName.length - 1]);

            addCBwithAllDsmtIDs(newDIVInfo[0], iN);

            iN++;

        }


        DbPkIDs.setAllDsmtIDs(allDsmtIDs);
        DbPkIDs.setAllDsmtPeriod(allDsmtPeriod);


        model = setNewDIVTableModel("newTable");

        for (DIVinfo div : divInfo) {

            div.setNoteAlertlevel(SetEnums.note_alertlevel.N);
            div.setNoteStatus(SetEnums.note_status.C);

            Object newDIVInfo[] = new Object[]{div.getPk_id(), div.getPk_dsmt(), div.getPeriodicity(),
                div.getName(), div.getId_mec(), div.getCategory(), div.getDepartment(), div.getId_dsmt(), div.getMonth(),
                div.getYear(), div.getHp007(), div.getHp10(), div.getComments(), div.getDose_note(), div.getNoteAlertlevel(),
                div.getNoteStatus(), Boolean.FALSE};


            newDIVInfo = selectPeriod(newDIVInfo);

            model.addRow(newDIVInfo);


        }


        setButtonsState.setAllDIVBtsSaveCancel(true);

        DbPkIDs.setRowSelectedNewDIV(-1);
        addNewDIVInfoTableListeners(divTableModel.getTableNewDIVinfo());


    }

    private void addCBwithAllDsmtIDs(Object divPk_id, int nRow) {

        String pk_id = divPk_id.toString();

        ArrayList<String> allDsmtIds = new ArrayList();
        ArrayList<String> allDsmtPerd = new ArrayList();

        ArrayList<Object[]> dsmtIds = dbPkIDs.getDiv_dsmtID();

        for (int i = 0; i < dsmtIds.size(); i++) {

            if (dsmtIds.get(i)[0].toString().matches(pk_id)) {

                allDsmtIds.add(dsmtIds.get(i)[1].toString());
                allDsmtPerd.add(dsmtIds.get(i)[2].toString());

            }

        }

        allDsmtIDs.add(nRow, allDsmtIds);
        allDsmtPeriod.add(nRow, allDsmtPerd);

    }

    private List<DIVinfo> removeDuplicates(List<DIVinfo> divInfo) {


        /* Set of all attributes seen so far */
        Set<String> attributes = new HashSet<>();
        /* All confirmed duplicates go in here */
        List duplicates = new ArrayList<>();

        ArrayList<Object[]> dsmtIds = new ArrayList();
        int ndsmtId = 0;

        for (DIVinfo x : divInfo) {

            Object divPeriod[] = setDIVPeriod(x.getPeriodicity().toString());
            Object testeDIVAlready[] = new Object[]{x.getPk_dsmt(), divPeriod[0], divPeriod[1]};

            boolean resultTestDIV = divdao.getDIVAlreadyInserted(testeDIVAlready);

            if (!resultTestDIV) {

                if (attributes.contains(x.getPk_id())) {
                    duplicates.add(x);
                }

                Object dsmtID[] = new Object[]{x.getPk_id(), x.getId_dsmt(), x.getPeriodicity()};
                dsmtIds.add(ndsmtId, dsmtID);

                ndsmtId++;

                attributes.add(x.getPk_id());

            } else {
                duplicates.add(x);
            }

        }

        dbPkIDs.setDiv_dsmtID(dsmtIds);

        /* Clean list without any dups */

        divInfo.removeAll(duplicates);

        return divInfo;

    }

    private Object[] selectPeriod(Object newDIVInfo[]) {


        yearMonthTrimester = new YearMonthAndTrimester();

        int colPeriod = 2;
        int colMonthTrim = 8;
        int colYear = 9;
        int trimester = 0;
        int year = yearMonthTrimester.Year();


        SetEnums.month Month[] = SetEnums.month.values();
        SetEnums.Trimester Trimester[] = SetEnums.Trimester.values();

        String period = newDIVInfo[colPeriod].toString();

        switch (period) {
            case "Mensal":

                int month = yearMonthTrimester.Month();
                year = yearMonthTrimester.Year();

                newDIVInfo[colMonthTrim] = Month[month];
                newDIVInfo[colYear] = year;

                return newDIVInfo;

            case "Trimestral":

                trimester = yearMonthTrimester.Trimester();
                year = yearMonthTrimester.Year();

                newDIVInfo[colMonthTrim] = Trimester[trimester];
                newDIVInfo[colYear] = year;
                return newDIVInfo;

            default:
                newDIVInfo[colMonthTrim] = Trimester[trimester];
                newDIVInfo[colYear] = year;
                return newDIVInfo;

        }


    }

    private Object[] setDIVPeriod(String period) {

        yearMonthTrimester = new YearMonthAndTrimester();

        Object[] divPeriod = new Object[]{null, null};

        SetEnums.month Month[] = SetEnums.month.values();
        SetEnums.Trimester Trimester[] = SetEnums.Trimester.values();

        switch (period) {
            case "Mensal":

                int month = yearMonthTrimester.Month();
                int year = yearMonthTrimester.Year();

                divPeriod[0] = Month[month];
                divPeriod[1] = year;

                return divPeriod;

            case "Trimestral":

                int trimester = yearMonthTrimester.Trimester();
                year = yearMonthTrimester.Year();
                
                System.out.println(" trimester --- setDivPeriod -- : " + trimester);
                System.out.println(" year --- setDivPeriod -- : " + year);

                divPeriod[0] = Trimester[trimester];
                divPeriod[1] = year;

                return divPeriod;

            default:
                return divPeriod;


        }

    }

    private void addNewDIVInfoTableListeners(final JTable table) {

        final TableModel tableModel = table.getModel();

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                int selectedColumn = table.getSelectedColumn();
                int selectedRow = table.getSelectedRow();

                if (selectedColumn == 0) {

                    if (e.getSource() == table.getSelectionModel() && e.getFirstIndex() >= 0) {

                        int rowSelected = table.getSelectedRow();
                        String pk_id = (String) tableModel.getValueAt(table.getSelectedRow(), 0);

                        if (rowSelected != DbPkIDs.getRowSelectedNewDIV()) {
                            // Display the selected item

                            selectDIVtable(pk_id);
                            DbPkIDs.setRowSelectedNewDIV(rowSelected);

                            frmDIV.txtWorkerNameDIV.setText(table.getValueAt(selectedRow, selectedColumn).toString());

                        }

                    }
                } else {
                    frmDIV.txtWorkerNameDIV.setText("");
                    clearOldDIVTables();
                    clearNotesDIVTables();
                }
            }
        });


    }

    private void selectDIVtable(String pk_id) {

        clearOldDIVTables();

        oldDivInfo = divdao.getOldDIVInfo(pk_id);

        if (!(oldDivInfo.size() > 0)) {
            return;
        }

        model = setOldDIVTableModel("newTable");

        for (DIVOldInfo div : oldDivInfo) {

            if (div.getPeriodicity().equalsIgnoreCase("Trimestral")) {
                div.setChoosePerd(div.getTrimester());
            }

            if (div.getPeriodicity().equalsIgnoreCase("Mensal")) {
                div.setChoosePerd(div.getMonth());
            }

            div.setTimestamp(div.getTimestamp().toString().split("\\s+")[0]);

            if (div.getLastchange() != null) {
                div.setLastchange(div.getLastchange().toString().split("\\s+")[0]);
            }


            Object oldDIVInfo[] = new Object[]{div.getPk_dose(), div.getId_dsmt(), div.getChoosePerd(),
                div.getYear(), div.getHp007(), div.getHp10(), div.getTimestamp(), div.getComments(), div.getLastchange()};

            model.addRow(oldDIVInfo);


        }

        clearNotesDIVTables();
        DbPkIDs.setRowSelectedOldDIV(-1);

        addOldDIVInfoTableListeners(divTableModel.getTableOldDIVinfo());


    }

    private void addOldDIVInfoTableListeners(final JTable table) {

        final TableModel tableModel = table.getModel();

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getSource() == table.getSelectionModel() && e.getFirstIndex() >= 0) {

                    int rowSelected = table.getSelectedRow();

                    String pk_dose = (String) tableModel.getValueAt(table.getSelectedRow(), 0);

                    if (rowSelected != DbPkIDs.getRowSelectedOldDIV()) {
                        // Display the selected item
                        selectOldDIVtable(pk_dose);
                        DbPkIDs.setRowSelectedOldDIV(rowSelected);
                    }

                }
            }
        });

    }

    private void selectOldDIVtable(String pk_dose) {

        notesDiv = divdao.getDIVNotes(pk_dose);

        if (!(notesDiv.size() > 0)) {
            removeNotesDIVTables();
            return;
        }

        model = setNotesDIVTableModel("newTable");

        for (DIVnotes div : notesDiv) {

            if (div.getLastchange() != null) {
                div.setLastchange(div.getLastchange().toString().split("\\s+")[0]);
            }

            Object notesDIV[] = new Object[]{div.getNote(), div.getStatus(), div.getAlert_level(), div.getLastchange()};

            model.addRow(notesDIV);

        }


    }

    /*
     * 
     *  sOME CALLS
     *  
     */
    private DefaultTableModel setNewDIVTableModel(String type) {


        System.out.println("  --- Set MOdel SET TABLE ---- > " + type);

        divTableModel.setSettingsNewDIVinfoTable(type);
        model = divTableModel.getModelTableNewDIVinfo();

        return model;

    }

    private void clearNewDIVTables() {

        divTableModel.setSettingsNewDIVinfoTable("newTable");

    }

    /*
     * 
     * 
     */
    private DefaultTableModel setOldDIVTableModel(String type) {

        divTableModel.setSettingsOldDIVinfoTable(type);
        model = divTableModel.getModelTableOldDIVinfo();
        return model;

    }

    private void clearOldDIVTables() {

        divTableModel.setSettingsOldDIVinfoTable("newTable");
        divTableModel.setSettingsOldDIVinfoTable("removeTable");

    }

    private DefaultTableModel setNotesDIVTableModel(String type) {

        divTableModel.setSettingsNotesDIVinfoTable(type);
        model = divTableModel.getModelTableNotesDIVinfo();
        return model;

    }

    private void clearNotesDIVTables() {

        divTableModel.setSettingsNotesDIVinfoTable("newTable");
        divTableModel.setSettingsNotesDIVinfoTable("removeTable");

    }

    private void removeNewDIVTables() {

        divTableModel.setSettingsNewDIVinfoTable("removeTable");
        DbPkIDs.setRowSelectedNewDIV(-1);

    }

    private void removeOldDIVTables() {

        divTableModel.setSettingsOldDIVinfoTable("removeTable");
        DbPkIDs.setRowSelectedOldDIV(-1);

    }

    private void removeNotesDIVTables() {

        divTableModel.setSettingsNotesDIVinfoTable("removeTable");

    }

    public void removeDIVTables() {

        removeNewDIVTables();
        removeOldDIVTables();
        removeNotesDIVTables();

    }
}
