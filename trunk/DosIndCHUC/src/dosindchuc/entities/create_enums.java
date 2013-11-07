/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.entities;

/**
 *
 * @author ir
 */
public class create_enums {
    
    public enum staff_category {
         Médico 
        , Físico
        , Técnico
        , Secretário
        , Enfermeiro
        , Auxiliar 
    }
    
    public enum staff_sex { M , F }
    
    
    public enum staff_department {
         Radioterapia 
        , Imagiologia
        , Ortopedia
        , BOC
        , MedicinaNuclear
    }
    
    public enum status { Activo , Inactivo }
    
    
    public enum dsmt_type { Body , Anel }
    
    
    public enum dsmt_periodicity { Anual , Trimestral }
    
    
    public enum dsmt_supplier {

        MedicalConsult("Medical Consult"),
        Fluencia("Fluência"),
        ITN("ITN");

        private String displayName;

        dsmt_supplier(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() { return displayName; }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }
    }
    
    public enum note_status { Open , Close }
    
}
