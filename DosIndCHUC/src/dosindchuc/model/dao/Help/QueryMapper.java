/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dosindchuc.model.dao.Help;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ir
 */
public interface QueryMapper<T> {

    /**
     *
     * @param rset
     * @return
     * @throws SQLException
     */
    List<T> mapping(ResultSet rset) ;
}
