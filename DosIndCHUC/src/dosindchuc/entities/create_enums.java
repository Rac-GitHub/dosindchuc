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
        
        RT("Radioterapia"),
        IM("Imagiologia"),
        Orto("Ortopedia"),
        BOC("Bloco Operatório"),
        MN("Medicina Nuclear");
        
        private String displayName;

        staff_department(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() { return displayName; }

        // Optionally and/or additionally, toString.
  //      @Override public String toString() { return displayName; }
        
    }
  
    
    public enum Trimester {
        
        Primeiro(1),
        Segundo(2),
        Terceiro(3),
        Quarto(4);
  
        private final int value;

        private Trimester(final int newValue) {
            value = newValue;
        }

        public int getValue() { return value; }
        
    }
    
    
    
    public enum status { 
        
        A("Activo"),
        I("Inactivo");
        
        private String displayName;

        status(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() { return displayName; }

        // Optionally and/or additionally, toString.
 //       @Override public String toString() { return displayName; }
        
    }
    
    
    
    public enum dsmt_type { 
        
        Body("Corpo Inteiro"),
        Anel("Anel");
    
        private String displayName;

        dsmt_type(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() { return displayName; }

        // Optionally and/or additionally, toString.
  //      @Override public String toString() { return displayName; }
    }
        
    
    
    
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
   //     @Override public String toString() { return displayName; }
    }
    
    
    
    
    public enum note_status { 
        
        O("Open"),
        C("Close");
        
        private String displayName;

        note_status(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() { return displayName; }

        // Optionally and/or additionally, toString.
 //       @Override public String toString() { return displayName; }
    
    }
    
    
    
    
    public enum note_alertlevel { 
        
        N("Normal"),
        PI("Pouco Importante"),
        I("Importante"),
        MI("Muito Importante"),
        C("Crítica");
        
        private String displayName;

        note_alertlevel(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() { return displayName; }

        // Optionally and/or additionally, toString.
 //       @Override public String toString() { return displayName; }
    
    }
    
    
    
}
