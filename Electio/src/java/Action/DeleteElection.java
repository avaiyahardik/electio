/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Action;

import DAO.DBDAOImplementation;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author darshit
 */
public class DeleteElection implements Controller.Action{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        String view="listElections.jsp";
        long id=Integer.parseInt(req.getParameter("id"));
        String err=null;
        String title="View Elections";
        if (id == 0) {
            err = "Session expired, please login again";
        } else {
        try {
            DBDAOImplementation obj=DBDAOImplementation.getInstance();
            obj.deleteElection(id);
        } catch (SQLException ex) {
            Logger.getLogger(DeleteElection.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        req.setAttribute("err", err);
        req.setAttribute("title", title);
        
        return view;
        
    }
    
}
