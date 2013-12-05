/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao;

import dosindchuc.UI.controller.MainActionListener;
import dosindchuc.UI.swing.MainFrm;
import dosindchuc.model.dao.Help.DaoHelper;
import dosindchuc.model.entities.Dose_info;
import dosindchuc.model.entities.Dose_notes;
import dosindchuc.model.entities.Dosimeter;
import dosindchuc.model.entities.Dosimeter_notes;
import dosindchuc.model.entities.Worker;
import dosindchuc.model.entities.Help.SetEnums;
import dosindchuc.model.entities.Help.DateAndTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author ir
 */
public class DaoTest {
    
   private DaoHelper daoHelper;
	/**
	 * @param args
	 */
   
   private MainFrm frm;
   
   
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
		
	//	List<Worker> listw = daow.getWorkersInfo(10);
               
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
//               System.out.println(SetEnums.worker_department.Img.displayName());
//               System.out.println(SetEnums.Trimester.Mensal.displayName());
//               
                
               //Worker worker = new Worker();
/*               Worker prof;
                
              //  dao.select();
         prof = new Worker("28229", "Paulo Rachinhas", "rac", "19743876", "Portuguese", "194346929", 
         "1969-06-08", SetEnums.worker_sex.M, SetEnums.worker_category.Médico, 
         SetEnums.worker_department.RT, "", "teste update Worker", "2013-14-15",
                 SetEnums.status.Activo, "2013-14-16");
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
               
               List<Worker> listw = daow.getWorkersInfo(pkid);
               System.out.println(Arrays.toString(listw.toArray()));
         
               
               Dosimeter dsmt;
               dsmt= new Dosimeter(pkid, "1151", "Label", SetEnums.dsmt_type.CI, SetEnums.dsmt_periodicity.Trimestral, 
                       SetEnums.dsmt_supplier.Fluencia, null, null, SetEnums.status.Activo, null);
               
             // dsmt = daod.insert(dsmt);
               
             // System.out.println(dsmt);
               
//               int dsmt_id=0;
               List<Dosimeter> listd = daod.getDosimetersInfo(0,pkid);
//               
               System.out.println(Arrays.toString(listd.toArray()));
               
               
               //Dose
               
         //      Dose_infoDao dao_dinfo = new Dose_infoDao();
               
////               Dose_info dose = new Dose_info(235, pkid, "2013", SetEnums.Trimester.NoDef, SetEnums.month.Jan, 0.23f, 0.487f, "minha dose", "2013-11-14");
               
  //             dose = dao_dinfo.insert(dose);
               
 ///              System.out.println(dose);
               
//                
               List<Dose_info> listdose = dao_dinfo.getDoseInfo(10,10);
               System.out.println(Arrays.toString(listdose.toArray()));   */
    
               
               DaoHelper daoHelper = new DaoHelper();
               
                Object [][] userss = daoHelper.executeSelectivePreparedQuery("users", "name, username, password ", null);
                
                int i = 0;
                for(int j=0; j<=userss.length-1;j++){
                 System.out.println(userss[i][j]);
              }
             i++; 
                
             System.out.println(userss[0][1]);
             
                
             UsersDao usersdao = new UsersDao();
             
    //            String name = usersdao.login("'rac1'", "'2'");
             
    //            System.out.println(name);
                
             
                
                
                
          //      System.out.println(userss.);
               
               
               // dose note --- 
               
//               Dose_notesDao dao_dnotes = new Dose_notesDao();
               
//               Dose_notes dnotes = new Dose_notes(2400, "foi resol vido probs com a leitura", "2013-11-15 12:13:10", SetEnums.note_status.C, "2013-11-15 12:13:10", SetEnums.note_alertlevel.N);
               
        //       dnotes = dao_dnotes.update(dnotes,1);
               
 //              System.out.println(dnotes);
               
               
               // dsmt notes
               
/*               Dosimeter_notesDao dao_dsmtnotes = new Dosimeter_notesDao();
               
               Dosimeter_notes dsmtnotes = new Dosimeter_notes(235, "foi resol vido probs com a leitura", "2013-11-15 12:13:10", SetEnums.note_status.C, "2013-11-15 12:13:10", SetEnums.note_alertlevel.C);
               
               //dsmtnotes = dao_dsmtnotes.insert(dsmtnotes);
             //  dsmtnotes = dao_dsmtnotes.update(dsmtnotes,1);
               
               System.out.println(dsmtnotes); */
               
               // delete patient
               
      //         daow.delete(241);
               
         
       //       MainActionListener mainAction = new MainActionListener(frm);
                
                
                
                
	}

}
