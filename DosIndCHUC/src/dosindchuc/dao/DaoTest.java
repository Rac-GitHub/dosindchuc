/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.dao;

import dosindchuc.entities.Dose_info;
import dosindchuc.entities.Dosimeter;
import dosindchuc.entities.Worker;
import dosindchuc.entities.create_enums;
import dosindchuc.entities.help_entities;
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
		
		List<Worker> listw = daow.listAllFromAllWorkers();
               
                System.out.println(Arrays.toString(listw.toArray()));
                
                
                // Dosimeter
                
                DosimeterDao daod = new DosimeterDao();
                
                List<Dosimeter> listd = daod.listAllFromAllDosimeters();
                System.out.println(Arrays.toString(listd.toArray()));
                
                // Dose values
                
               Dose_infoDao dao_dinfo = new Dose_infoDao();
                
               List<Dose_info> listdose = dao_dinfo.listAllFromAllDose();
               System.out.println(Arrays.toString(listdose.toArray()));
                
               
               System.out.println(create_enums.worker_department.Img.displayName());
               System.out.println(create_enums.Trimester.Mensal.displayName());
               
                
               //Worker worker = new Worker();
               Worker prof;
                
              //  dao.select();
         prof = new Worker("18181", "Paulo rachinhas", "rac", "19876", "Portuguese", "1929929", 
         "1969-06-08", create_enums.worker_sex.M, create_enums.worker_category.Físico, 
         create_enums.worker_department.RT, "", "teste inserir worker", "2013-14-15",
                 create_enums.status.Activo, "2013-14-16");
	
         
         prof = daow.insert(prof);
         
         System.out.println(prof);
         
         
	}

}