/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.entities;

import dosindchuc.model.entities.Help.SetEnums;

/**
 *
 * @author ir
 */
public class AlertNotes {
 
    
    private String pk_notes;
    private SetEnums.note_alertlevel notesLevel;
    private String notesType;
    private String notesMec;
    private String notesName;
    private String notesDept;
    private String notesNote;
    private SetEnums.note_status notesStatus;
    private String notesDate;

    
    
    public AlertNotes() {
    }

    
    
    public String getPk_notes() {
        return pk_notes;
    }

    public void setPk_notes(String pk_notes) {
        this.pk_notes = pk_notes;
    }

    
    public SetEnums.note_alertlevel getNotesLevel() {
        return notesLevel;
    }

    public void setNotesLevel(SetEnums.note_alertlevel notesLevel) {
        this.notesLevel = notesLevel;
    }

    public String getNotesType() {
        return notesType;
    }

    public void setNotesType(String notesType) {
        this.notesType = notesType;
    }

    public String getNotesMec() {
        return notesMec;
    }

    public void setNotesMec(String notesMec) {
        this.notesMec = notesMec;
    }

    public String getNotesName() {
        return notesName;
    }

    public void setNotesName(String notesName) {
        this.notesName = notesName;
    }

    public String getNotesDept() {
        return notesDept;
    }

    public void setNotesDept(String notesDept) {
        this.notesDept = notesDept;
    }

    public String getNotesNote() {
        return notesNote;
    }

    public void setNotesNote(String notesNote) {
        this.notesNote = notesNote;
    }

    public SetEnums.note_status getNotesStatus() {
        return notesStatus;
    }

    public void setNotesStatus(SetEnums.note_status notesStatus) {
        this.notesStatus = notesStatus;
    }

    public String getNotesDate() {
        return notesDate;
    }

    public void setNotesDate(String notesDate) {
        this.notesDate = notesDate;
    }
    
    
    
    
    
}
