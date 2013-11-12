/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.dao;

import dosindchuc.entities.Worker;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ir
 */
public class WorkerDaoTest {
    
  
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
		WorkerDao dao = new WorkerDao();
//		dao.inserir(paciente);
         //      dao = 
		
		List<Worker> list = dao.listAllFromAllWorkers();
               
                System.out.println(Arrays.toString(list.toArray()));
                
              //  Worker worker = new Worker();  
                
              //  dao.select();
	
                
	}

}
