/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.entities.Help;

/**
 *
 * @author ir
 */
public class SetEnums {

    public enum worker_category {

        Médico, Físico, Técnico, Secretário, Enfermeiro, Auxiliar, NULL
    }

    public enum worker_sex {

        M, F
    }

    public enum worker_department {

        RT("Radioterapia"),
        Img("Imagiologia"),
        Orto("Ortopedia"),
        BOC("Bloco Operatório"),
        MN("Medicina Nuclear"),
        NULL("Not defined");
        
        private String displayName;

        worker_department(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() {
            return displayName;
        }
    }

    
    public enum worker_status {

        Activo, 
        Inactivo
        
    }
    
    public enum Trimester {

        P(1),
        S(2),
        T(3),
        Q(4),
        NULL(5);
        private final int displayName;

        private Trimester(int displayName) {
            this.displayName = displayName;
        }

        public int displayName() {
            return this.displayName;
        }
    }

    public enum month {

        Jan, Fev, Mar, Abr, Mai, Jun, Jul, Ago, Set, Out, Nov, Dez, NULL
    }

    public enum dsmt_type {

        CI("Corpo Inteiro"),
        Anel("Anel");
        private String displayName;

        dsmt_type(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() {
            return displayName;
        }
    }

    public enum dsmt_periodicity {

        Mensal, Trimestral
    }

    public enum dsmt_supplier {

        MedicalConsult("Medical Consult"),
        Fluencia("Fluência"),
        ITN("ITN"),
        DPR("DPR"),
        NULL("Not defined");
        
        private String displayName;

        dsmt_supplier(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() {
            return displayName;
        }
    }

    
    public enum dsmt_status {

        Pedido,
        Chegou,
        Activo,
        Inactivo,
        Enviado,
        NaoEnviado,
        Recebido,
        NaoRecebido,
        Perdido,
        Recuperado,
        PedCancelamento,
        Cancelado
   
    }
    
    
    public enum note_status {

        O("Open"),
        C("Close");
        private String displayName;

        note_status(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() {
            return displayName;
        }
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

        public String displayName() {
            return displayName;
        }
    }
}
