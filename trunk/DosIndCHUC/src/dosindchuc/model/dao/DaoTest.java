/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.model.entities.Dose_info;
import dosindchuc.model.entities.Dose_notes;
import dosindchuc.model.entities.Dosimeter;
import dosindchuc.model.entities.Dosimeter_notes;
import dosindchuc.model.entities.Worker;
import dosindchuc.model.entities.Help.create_enums;
import dosindchuc.model.entities.Help.help_entities;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author ir
 */
public class DaoTest {
    
  
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		Paciente paciente = new Paciente("Katatau3", "3284973423", "90485098", SexoType.M);
//		paciente.setCriacao(new Date());
//		Endereco end = new Endereco("End do Katatau3"
//									,"Cid do Katatau3"
//									,"Bairro do Katatau3"
////									,"CepKata"  );
//		paciente.setEndereco(end);
//		
//		Contato contato = new Contato();
//		contato.setCelular("1293483983");
//		contato.setTelefone("1293483983");
//		contato.setEmail("ercarval@gmail.com");
//		
//		paciente.setContato(contato);
//		
            
                // workers
		WorkerDao daow = new WorkerDao();
//		dao.inserir(paciente);
         //      dao = 
		
	//	List<Worker> listw = daow.listWorkers(10);
               
         //       System.out.println(Arrays.toString(listw.toArray()));
                
                
                // Dosimeter
                
                DosimeterDao daod = new DosimeterDao();
                
 //               List<Dosimeter> listd = daod.listAllFromAllDosimeters();
 //               System.out.println(Arrays.toString(listd.toArray()));
                
                // Dose values
                
               Dose_infoDao dao_dinfo = new Dose_infoDao();
//                
//               List<Dose_info> listdose = dao_dinfo.listAllFromAllDose();
//               System.out.println(Arrays.toString(listdose.toArray()));
//                
//               
//               System.out.println(create_enums.worker_department.Img.displayName());
//               System.out.println(create_enums.Trimester.Mensal.displayName());
//               
                
               //Worker worker = new Worker();
               Worker prof;
                
              //  dao.select();
         prof = new Worker("28229", "Paulo Rachinhas", "rac", "19743876", "Portuguese", "194346929", 
         "1969-06-08", create_enums.worker_sex.M, create_enums.worker_category.Médico, 
         create_enums.worker_department.RT, "", "teste update Worker", "2013-14-15",
                 create_enums.status.Activo, "2013-14-16");
//	
//         
//         prof = daow.insert(prof);
//         
         System.out.println(prof);
               int pkid = 241;
//               prof = daow.update(prof, pkid);
//         
//               
//               System.out.println(prof);
               
               List<Worker> listw = daow.listWorkers(pkid);
               System.out.println(Arrays.toString(listw.toArray()));
         
               
               Dosimeter dsmt;
               dsmt= new Dosimeter(pkid, "1151", "Label", create_enums.dsmt_type.CI, create_enums.dsmt_periodicity.Trimestral, 
                       create_enums.dsmt_supplier.Fluencia, null, null, create_enums.status.Activo, null);
               
             // dsmt = daod.insert(dsmt);
               
             // System.out.println(dsmt);
               
//               int dsmt_id=0;
               List<Dosimeter> listd = daod.listDosimeters(0,pkid);
//               
               System.out.println(Arrays.toString(listd.toArray()));
               
               
               //Dose
               
         //      Dose_infoDao dao_dinfo = new Dose_infoDao();
               
               Dose_info dose = new Dose_info(235, pkid, "2013", create_enums.Trimester.NoDef, create_enums.month.Jan, 0.23f, 0.487f, "minha dose", "2013-11-14");
               
  //             dose = dao_dinfo.insert(dose);
               
               System.out.println(dose);
               
//                
               List<Dose_info> listdose = dao_dinfo.listDose(10,10);
               System.out.println(Arrays.toString(listdose.toArray()));
    
               
               // dose note --- 
               
               Dose_notesDao dao_dnotes = new Dose_notesDao();
               
               Dose_notes dnotes = new Dose_notes(2400, "foi resol vido probs com a leitura", "2013-11-15 12:13:10", create_enums.note_status.C, "2013-11-15 12:13:10", create_enums.note_alertlevel.N);
               
        //       dnotes = dao_dnotes.update(dnotes,1);
               
               System.out.println(dnotes);
               
               
               // dsmt notes
               
               Dosimeter_notesDao dao_dsmtnotes = new Dosimeter_notesDao();
               
               Dosimeter_notes dsmtnotes = new Dosimeter_notes(235, "foi resol vido probs com a leitura", "2013-11-15 12:13:10", create_enums.note_status.C, "2013-11-15 12:13:10", create_enums.note_alertlevel.C);
               
               //dsmtnotes = dao_dsmtnotes.insert(dsmtnotes);
             //  dsmtnotes = dao_dsmtnotes.update(dsmtnotes,1);
               
               System.out.println(dsmtnotes);
               
               // delete patient
               
               daow.delete(241);
               
         
	}

}
