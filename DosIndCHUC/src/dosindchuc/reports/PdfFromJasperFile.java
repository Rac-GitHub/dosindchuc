/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.reports;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import dosindchuc.model.dao.Help.DaoConnections;

// How To Invoke Ireport From Java Application


/**
 *
 * @author ir
 */
public class PdfFromJasperFile {
    
   private static HashMap params;
   private DaoConnections daoConnection;

   
    public PdfFromJasperFile() {
       daoConnection = new DaoConnections();
    }
    
      
   public void report1 () throws JRException, IOException {

       daoConnection = new DaoConnections();
        // Create arguments    // Map 
 
       params = new HashMap();
       
       params.put("department", "Img");
       
 
       Connection conn = daoConnection.getConnection();
       
    JasperPrint jasperPrint = JasperFillManager.fillReport ("/home/ir/NetBeansProjects/DosIndCHUC/src/reports/report1.jasper",  params ,conn);
    JasperExportManager.exportReportToPdfFile(jasperPrint, "sample.pdf");

  }
    
}
